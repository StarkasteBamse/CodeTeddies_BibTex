/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author mrreflex
 */
public class FileWriterIO implements IO {

    private FileWriter filewriter;
    
    @Override
    public void print(String toPrint) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void println(String toPrint) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean writeFile(String filename, String toFile) {
        try {
            File file = new File(filename);
            filewriter = new FileWriter(file, false);
            filewriter.write(toFile);
            filewriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readLine() {
        throw new UnsupportedOperationException("Not supported.");
    }
    
}
