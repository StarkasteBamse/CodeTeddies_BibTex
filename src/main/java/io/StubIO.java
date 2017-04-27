package io;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class StubIO implements IO {

    private List<String> lines;
    private int i;
    private ArrayList<String> prints;

    public StubIO(List<String> values) {
        this.lines = values;
        prints = new ArrayList<>();
    }

    @Override
    public void print(String toPrint) {
        prints.add(toPrint);
    }

    public ArrayList<String> getPrints() {
        return prints;
    }

    @Override
    public String readLine() {
        if (i < lines.size()) {
            return lines.get(i++);
        }
        return "";
    }

    public String readLine(String prompt) {
        print(prompt);
        if (i < lines.size()) {
            return lines.get(i++);
        }
        return "";
    }

    @Override
    public void println(String toPrint) {
        prints.add(toPrint);
    }

    @Override
    public boolean writeFile(String filename, String toFile) {
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream(filename), "UTF-8"));
            out.write(toFile);
            out.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
