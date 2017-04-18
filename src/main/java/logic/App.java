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

    public void run() {
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
                        addReference("3");
                        ;
                        break;
                    default:
                        break;
                }
            }
        }
        printRef(references, wrp, io);
        exportRef(references, wrp, io, new FileWriterIO(), "sigproc.bib");
    }

    private void printRef(ArrayList<Reference> rList, Wrapper wrp, IO io) {
        if (references.isEmpty()) {
            io.println("No articles in memory");
        }
        for (Reference reference : rList) {
            String bib = wrp.wrap(reference);
            io.println(bib);
        }
    }

    private void exportRef(ArrayList<Reference> rList, Wrapper wrp, 
                           IO io, IO fileIo, String fileName) {
        if (references.isEmpty()) {
            io.println("No articles in memory");
        } else {
            String bib = "";
            for (Reference reference : rList) {
                bib += wrp.wrap(reference) + "\n\n";
            }
            if (!fileIo.writeFile(fileName, bib)) {
                io.println("Error occurred while "
                                    + "exporting file: " + fileName);
            }
        }
    }

    private boolean scandeja(String s) {
        if (s.matches("^[a-zA-Z0-9!@#$%&*()_+=|<>?{}\\[\\]~-]*$")) {
            return false;
        }
        return true;
    }

    private void addRefToList(Reference reference, IO io, ArrayList rList) {
        if (reference.hasRequiredFields()) {
            rList.add(reference);
            io.println("New " + reference + " added successfully");
        } else {
            io.println("Not proper format!");
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
                break;
        }
        io.println("BibTex an " + reference + "!");
        inputFields(reference);
        addRefToList(reference, io, references);
    }

    private void inputFields(Reference reference) {
        for (String inputField : reference.getRequiredFields()) {
            io.print(inputField + ": ");
            String inputLine = io.readLine();
            if (scandeja(inputLine)) {
                io.println("");
                io.println("Invalid " + inputField);
                continue;
            }
            reference.setField(inputField, inputLine);

            io.println("");
        }
    }

}
