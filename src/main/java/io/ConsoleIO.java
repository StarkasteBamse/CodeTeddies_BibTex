
package io;

import java.util.Scanner;

public class ConsoleIO implements IO{
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String toPrint) {
        System.out.print(toPrint);
    }

    @Override
    public void println(String toPrint) {
        System.out.println(toPrint);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    public String readLine(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    @Override
    public boolean writeFile(String filename, String toFile) {
        throw new UnsupportedOperationException("Not supported."); 
    }
    
}
