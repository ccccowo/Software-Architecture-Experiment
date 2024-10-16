package 软件体系结构实验二.开发软件;



import 软件体系结构实验二.主程序子程序.Alphabetizer;
import 软件体系结构实验二.主程序子程序.Input;
import 软件体系结构实验二.主程序子程序.Output;
import 软件体系结构实验二.主程序子程序.Shift;
import 软件体系结构实验二.事件系统.KWICSubject;
import 软件体系结构实验二.管道过滤.Pipe;

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
        architectureComboBox = new JComboBox<>(new String[]{"主程序-子程序", "面向对象", "事件系统", "管道-过滤器"});
        topPanel.add(new JLabel("选择体系结构风格:"));
        topPanel.add(architectureComboBox);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);
        centerPanel.add(new JScrollPane(resultTextArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        inputTextField = new JTextField(20);
        bottomPanel.add(new JLabel("输入文件路径:"));
        bottomPanel.add(inputTextField);
        processButton = new JButton("处理文件");
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
        if (selectedArchitecture == "主程序-子程序") {
            Input input1 = new Input();
            input1.input(inputFilePath);
            Shift shift = new Shift(input1.getLineTxt());
            shift.shift();
            Alphabetizer alphabetizer = new Alphabetizer(shift.getKwicList());
            alphabetizer.sort();
            Output output = new Output(alphabetizer.getKwicList());
            output.output(inputFilePath);
        } else if (selectedArchitecture == "面向对象") {
            软件体系结构实验二.面向对象.Input input2 = new 软件体系结构实验二.面向对象.Input();
            input2.input(inputFilePath);
            软件体系结构实验二.面向对象.Shift shift = new 软件体系结构实验二.面向对象.Shift(input2.getLineTxt());
            shift.shift();
            软件体系结构实验二.面向对象.Alphabetizer alphabetizer = new 软件体系结构实验二.面向对象.Alphabetizer(shift.getKwicList());
            alphabetizer.sort();
            软件体系结构实验二.面向对象.Output output = new 软件体系结构实验二.面向对象.Output(alphabetizer.getKwicList());
            output.output(inputFilePath);
        } else if (selectedArchitecture == "事件系统") {
            //创建主题
            KWICSubject kwicSubject = new KWICSubject();
            //创建观察者
            软件体系结构实验二.事件系统.Input input = new 软件体系结构实验二.事件系统.Input(inputFilePath);
            软件体系结构实验二.事件系统.Shift shift = new 软件体系结构实验二.事件系统.Shift(input.getLineTxt());
            软件体系结构实验二.事件系统.Alphabetizer alphabetizer = new 软件体系结构实验二.事件系统.Alphabetizer(shift.getKwicList());
            软件体系结构实验二.事件系统.Output output = new 软件体系结构实验二.事件系统.Output(alphabetizer.getKwicList(), inputFilePath);

            // 将观察者加入主题
            kwicSubject.addObserver(input);
            kwicSubject.addObserver((软件体系结构实验二.事件系统.Input) shift);
            kwicSubject.addObserver(alphabetizer);
            kwicSubject.addObserver(output);
            // 逐步调用各个观察者
            kwicSubject.startKWIC();
        } else if (selectedArchitecture == "管道-过滤器") {
            File inFile = new File(inputFilePath);
            File outFile = new File(inputFilePath);
            Pipe pipe1 = new Pipe();
            Pipe pipe2 = new Pipe();
            Pipe pipe3 = new Pipe();
            软件体系结构实验二.管道过滤.Input input = new 软件体系结构实验二.管道过滤.Input(inFile, pipe1);
            软件体系结构实验二.管道过滤.Shift shift = new 软件体系结构实验二.管道过滤.Shift(pipe1, pipe2);
            软件体系结构实验二.管道过滤.Alphabetizer alphabetizer = new 软件体系结构实验二.管道过滤.Alphabetizer(pipe2, pipe3);
            软件体系结构实验二.管道过滤.Output output = new 软件体系结构实验二.管道过滤.Output(outFile, pipe3);
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