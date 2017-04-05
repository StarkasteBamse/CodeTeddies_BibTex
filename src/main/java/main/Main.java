package main;

import io.ConsoleIO;

public class Main {
    ConsoleIO io = new ConsoleIO();

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        ConsoleIO io = new ConsoleIO();

        // TODO: Ignore me from test reports
        System.out.println(testMe("Moi t. CodeTeddies"));

        Article a = new Article();

        io.println("BibTex an article!");
        io.print("Author: ");
        a.setAuthor(io.readLine());
        io.print("Title: ");
        a.setTitle(io.readLine());
        io.print("Journal: ");
        a.setJournal(io.readLine());
        io.print("Year: ");
        a.setYear(Integer.parseInt(io.readLine()));
        io.print("Volume: ");
        a.setVolume(Integer.parseInt(io.readLine()));

    }



    public static String testMe(String lol) {
        return lol;
    }

}
