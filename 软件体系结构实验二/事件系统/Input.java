package 软件体系结构实验二.事件系统;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Input extends Shift implements Observer {

    private ArrayList<String> lineTxt = new ArrayList<String>();

    public ArrayList<String> getLineTxt() {
        return lineTxt;
    }

    private String fileName;

    public Input(String fileName) {
        super();
        this.fileName = fileName;
    }

    @Override
    public void toDo() {
        BufferedReader inputFile = null;
        try {
            inputFile = new BufferedReader(new FileReader(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String line;
        try {
            while ((line = inputFile.readLine()) != null) {
                lineTxt.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
