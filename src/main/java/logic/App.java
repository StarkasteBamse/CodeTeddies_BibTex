package logic;

import domain.Article;
import domain.Reference;
import domain.Inproceedings;
import domain.Book;
import io.*;
import java.util.ArrayList;
import wrapper.Wrapper;

public class App {

    private IO io;
    private Wrapper wrp;
    private ArrayList<Reference> references;

    public App(IO io) {
        this.io = io;
        this.wrp = new Wrapper();
        this.references = new ArrayList<>();        
    }
       
    public App() {
        this(new ConsoleIO());        
    }

    //CHECKSTYLE:OFF
    public void run() {
//        ConsoleIO io = new ConsoleIO();
//        Wrapper wrp = new Wrapper();
        ArrayList<Article> articles = new ArrayList<>();
        while (true) {
            io.print("Add? (y/n)");
            if (io.readLine().equals("n")) {
                io.println("");
                break;
            } else {
                io.println("");
                io.println("Which reference type?");
                io.println("\t1. Article\n\t2. Book\n\t3. Inproceedings");
                switch (io.readLine()) {
                    case "1":
                        addReference("1");
                        break;
                    case "2":
                        addReference("2");
                        break;
                    case "3":
                        addReference("3");;
                        break;
                    default:
                        break;
                }
            }
        }

        printReferences(references, wrp, io);
    }
        
    public void printReferences(ArrayList<Reference> refList, Wrapper wrp, IO io) {
        if (references.isEmpty()) {
            io.println("No articles in memory");
        } 
        for (Reference reference : refList) {
            // String bib = wrp.wrap(reference); // vaatii wrapperin refaktoroinnin
            // io.println(bib);
        }
    }

    public boolean scandeja(String s) {
        if (s.matches("^[a-zA-Z0-9!@#$%&*()_+=|<>?{}\\[\\]~-]*$")) {
            return false;
        }
        return true;
    }

    public void addReferenceToList(Reference reference, IO io, ArrayList refList) {
        if (reference.hasRequiredFields()) {
            refList.add(reference);
            io.println("New " + reference + " added succesfully");
        } else {
            System.out.println("Not proper format!");
        }
    }
    
    private void addReference(String i) {
        Reference reference = null;
        
        switch (i) {
            case "1":
                reference = new Article();
                break;
            case "2":
                reference = new Book();
                break;
            case "3":
                reference = new Inproceedings();
                break;
            default:
                System.out.println("Something went wrong!");
                break;
        }
        io.println("BibTex an " + reference + "!");
        inputFields(reference);
        addReferenceToList(reference, io, references);
    }

    private void inputFields(Reference reference) {
        for (String inputField : reference.getRequiredFields()) {
            io.print(inputField + ": ");
            String inputLine = io.readLine();
            reference.setField(inputField, inputLine);
            io.println("");
        }
    }
//CHECKSTYLE:ON
}
