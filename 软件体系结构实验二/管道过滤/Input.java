package 软件体系结构实验二.管道过滤;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Input extends Filter {
    private File file;

    public Input(File file, Pipe output) {
        super(null, output);
        this.file = file;
    }

    @Override
    public void transform() throws IOException {
        BufferedReader inputFile = null;
        try {
            inputFile = new BufferedReader(new FileReader(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String line;
        try {
            while ((line = inputFile.readLine()) != null) {
                output.writerLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        output.closeWriter();
    }
}

