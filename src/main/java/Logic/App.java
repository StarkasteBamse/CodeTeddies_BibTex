package Logic;

import domain.Article;
import domain.Reference;
import io.ConsoleIO;
import java.util.ArrayList;
import wrapper.Wrapper;

public class App {
    
    private ConsoleIO io;
    private Wrapper wrp;
    private ArrayList<Reference> references;
    
    public App() {
        this.io = new ConsoleIO();
        this.wrp = new Wrapper();
        this.references = new ArrayList<>();
    }

    public void run() {
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

    public void printArticles(ArrayList<Article> articles, Wrapper wrp, ConsoleIO io) {
        for (Article article : articles) {
            String bib = wrp.wrap(article);
            io.println(bib);
        }
    }

    public void checkRequiredFields(Article a, ConsoleIO io,
            ArrayList articles) {
        if (a.hasRequiredFields()) {
            articles.add(a);
            io.println("New article added succesfully");
        }
    }

    public void volume(ConsoleIO io, Article a) throws NumberFormatException {
        io.print("Volume: ");
        String volume = io.readLine();
        a.setVolume(volume);
        io.println("");
    }

    public void year(ConsoleIO io, Article a) throws NumberFormatException {
        io.print("Year: ");
        String year = io.readLine();
        a.setYear(year);
        io.println("");
    }

    public void journal(ConsoleIO io, Article a) {
        io.print("Journal: ");
        String journal = io.readLine();
        if (scandeja(journal)) {
            io.println("Invalid journal name");
        } else {
            a.setJournal(journal);
        }
        io.println("");
    }

    public void title(ConsoleIO io, Article a) {
        io.print("Title: ");
        String title = io.readLine();
        if (scandeja(title)) {
            io.println("Invalid title name");
        } else {
            a.setTitle(title);
        }
        io.println("");
    }

    public void author(ConsoleIO io, Article a) {
        io.print("Author: ");
        String author = io.readLine();
        if (scandeja(author)) {
            io.println("Invalid author name");
        } else {
            a.setAuthor(author);
        }
        io.println("");
    }

    public boolean scandeja(String s) {
        if (s.matches("^[a-zA-Z0-9!@#$%&*()_+=|<>?{}\\[\\]~-]*$")) {
            return false;
        }
        return true;
    }
//CHECKSTYLE:ON
}
