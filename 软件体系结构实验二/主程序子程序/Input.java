package 软件体系结构实验二.主程序子程序;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Input {
    private ArrayList<String> lineTxt = new ArrayList<String>();

    public ArrayList<String> getLineTxt() {
        return lineTxt;
    }

    public void input(String fileName) {
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

