package main;

import io.ConsoleIO;
import java.util.ArrayList;
import wrapper.Wrapper;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConsoleIO io = new ConsoleIO();
        Wrapper wrp = new Wrapper();
        ArrayList<Article> articles = new ArrayList<>();
//CHECKSTYLE:OFF
        while (true) {
            io.print("Add? (y/n)");
            if (io.readLine().equals("n")) {
                io.println("");
                break;
            } else {
                Article a = new Article();
                io.println("");
                io.println("BibTex an article!");
                author(io, a);
                title(io, a);
                journal(io, a);
                year(io, a);
                volume(io, a);
                checkRequiredFields(a, io, articles);
            }
        }

        printArticles(articles, wrp, io);
    }

    public static void printArticles(ArrayList<Article> articles, Wrapper wrp, ConsoleIO io) {
        for (Article article : articles) {
            String bib = wrp.wrap(article);
            io.println(bib);
        }
    }

    public static void checkRequiredFields(Article a, ConsoleIO io,
            ArrayList articles) {
        if (a.hasRequiredFields()) {
            articles.add(a);
            io.println("New article added succesfully");
        }
    }

    public static void volume(ConsoleIO io, Article a) throws NumberFormatException {
        io.print("Volume: ");
        String volume = io.readLine();
        int i = 0;
        try {
            i = Integer.parseInt(volume);
        } catch (NumberFormatException e) {
            i = 0;
        }

        a.setVolume(i);
        io.println("");
    }

    public static void year(ConsoleIO io, Article a) throws NumberFormatException {
        io.print("Year: ");
        String year = io.readLine();
        int i = 0;
        try {
            i = Integer.parseInt(year);
        } catch (NumberFormatException e) {
            i = 0;
        }

        a.setYear(i);
        io.println("");
    }

    public static void journal(ConsoleIO io, Article a) {
        io.print("Journal: ");
        String journal = io.readLine();
        if (scandeja(journal)) {
            io.println("Invalid journal name");
        } else {
            a.setJournal(journal);
        }
        io.println("");
    }

    public static void title(ConsoleIO io, Article a) {
        io.print("Title: ");
        String title = io.readLine();
        if (scandeja(title)) {
            io.println("Invalid title name");
        } else {
            a.setTitle(title);
        }
        io.println("");
    }

    public static void author(ConsoleIO io, Article a) {
        io.print("Author: ");
        String author = io.readLine();
        if (scandeja(author)) {
            io.println("Invalid author name");
        } else {
            a.setAuthor(author);
        }
        io.println("");
    }

    public static boolean scandeja(String s) {
        if (s.matches("^[a-zA-Z0-9!@#$%&*()_+=|<>?{}\\[\\]~-]*$")) {
            return false;
        }
        return true;
    }
//CHECKSTYLE:ON
}
