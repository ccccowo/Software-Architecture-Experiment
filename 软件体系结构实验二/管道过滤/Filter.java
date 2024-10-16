package 软件体系结构实验二.管道过滤;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class Filter {
    protected Pipe input;
    protected Pipe output;

    public Filter(Pipe input, Pipe output) {
        this.input = input;
        this.output = output;
    }

    protected abstract void transform() throws IOException;
}
