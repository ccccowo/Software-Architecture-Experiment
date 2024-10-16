package �����ϵ�ṹʵ���.�������;



import �����ϵ�ṹʵ���.�������ӳ���.Alphabetizer;
import �����ϵ�ṹʵ���.�������ӳ���.Input;
import �����ϵ�ṹʵ���.�������ӳ���.Output;
import �����ϵ�ṹʵ���.�������ӳ���.Shift;
import �����ϵ�ṹʵ���.�¼�ϵͳ.KWICSubject;
import �����ϵ�ṹʵ���.�ܵ�����.Pipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class KWICDemo extends JFrame {
    private JComboBox<String> architectureComboBox;
    private JTextArea resultTextArea;
    private JButton processButton;
    private JTextField inputTextField;

    public KWICDemo() {
        createUI();
    }

    private void createUI() {
        setTitle("KWIC Demo Software");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        architectureComboBox = new JComboBox<>(new String[]{"������-�ӳ���", "�������", "�¼�ϵͳ", "�ܵ�-������"});
        topPanel.add(new JLabel("ѡ����ϵ�ṹ���:"));
        topPanel.add(architectureComboBox);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);
        centerPanel.add(new JScrollPane(resultTextArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        inputTextField = new JTextField(20);
        bottomPanel.add(new JLabel("�����ļ�·��:"));
        bottomPanel.add(inputTextField);
        processButton = new JButton("�����ļ�");
        bottomPanel.add(processButton);
        centerPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    processFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void processFile() throws IOException {
        String selectedArchitecture = (String) architectureComboBox.getSelectedItem();
        String inputFilePath = inputTextField.getText();
        if (selectedArchitecture == "������-�ӳ���") {
            Input input1 = new Input();
            input1.input(inputFilePath);
            Shift shift = new Shift(input1.getLineTxt());
            shift.shift();
            Alphabetizer alphabetizer = new Alphabetizer(shift.getKwicList());
            alphabetizer.sort();
            Output output = new Output(alphabetizer.getKwicList());
            output.output(inputFilePath);
        } else if (selectedArchitecture == "�������") {
            �����ϵ�ṹʵ���.�������.Input input2 = new �����ϵ�ṹʵ���.�������.Input();
            input2.input(inputFilePath);
            �����ϵ�ṹʵ���.�������.Shift shift = new �����ϵ�ṹʵ���.�������.Shift(input2.getLineTxt());
            shift.shift();
            �����ϵ�ṹʵ���.�������.Alphabetizer alphabetizer = new �����ϵ�ṹʵ���.�������.Alphabetizer(shift.getKwicList());
            alphabetizer.sort();
            �����ϵ�ṹʵ���.�������.Output output = new �����ϵ�ṹʵ���.�������.Output(alphabetizer.getKwicList());
            output.output(inputFilePath);
        } else if (selectedArchitecture == "�¼�ϵͳ") {
            //��������
            KWICSubject kwicSubject = new KWICSubject();
            //�����۲���
            �����ϵ�ṹʵ���.�¼�ϵͳ.Input input = new �����ϵ�ṹʵ���.�¼�ϵͳ.Input(inputFilePath);
            �����ϵ�ṹʵ���.�¼�ϵͳ.Shift shift = new �����ϵ�ṹʵ���.�¼�ϵͳ.Shift(input.getLineTxt());
            �����ϵ�ṹʵ���.�¼�ϵͳ.Alphabetizer alphabetizer = new �����ϵ�ṹʵ���.�¼�ϵͳ.Alphabetizer(shift.getKwicList());
            �����ϵ�ṹʵ���.�¼�ϵͳ.Output output = new �����ϵ�ṹʵ���.�¼�ϵͳ.Output(alphabetizer.getKwicList(), inputFilePath);

            // ���۲��߼�������
            kwicSubject.addObserver(input);
            kwicSubject.addObserver((�����ϵ�ṹʵ���.�¼�ϵͳ.Input) shift);
            kwicSubject.addObserver(alphabetizer);
            kwicSubject.addObserver(output);
            // �𲽵��ø����۲���
            kwicSubject.startKWIC();
        } else if (selectedArchitecture == "�ܵ�-������") {
            File inFile = new File(inputFilePath);
            File outFile = new File(inputFilePath);
            Pipe pipe1 = new Pipe();
            Pipe pipe2 = new Pipe();
            Pipe pipe3 = new Pipe();
            �����ϵ�ṹʵ���.�ܵ�����.Input input = new �����ϵ�ṹʵ���.�ܵ�����.Input(inFile, pipe1);
            �����ϵ�ṹʵ���.�ܵ�����.Shift shift = new �����ϵ�ṹʵ���.�ܵ�����.Shift(pipe1, pipe2);
            �����ϵ�ṹʵ���.�ܵ�����.Alphabetizer alphabetizer = new �����ϵ�ṹʵ���.�ܵ�����.Alphabetizer(pipe2, pipe3);
            �����ϵ�ṹʵ���.�ܵ�����.Output output = new �����ϵ�ṹʵ���.�ܵ�����.Output(outFile, pipe3);
            input.transform();
            shift.transform();
            alphabetizer.transform();
            output.transform();
        }


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new KWICDemo().setVisible(true);
            }
        });
    }
}