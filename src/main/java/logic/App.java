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
    private String n = System.getProperty("line.separator");
    private ArrayList<String> numerics;

    public App(IO io) {
        this.io = io;
        this.wrp = new Wrapper();
        this.references = new ArrayList<>();
        this.numerics = new ArrayList<>();
        setNumerics();
    }

    public App() {
        this(new ConsoleIO());
    }

    public void run() {
        while (true) {
            io.print("Add? (y/n)");
            String answer = io.readLine();
            if (answer.equals("n")) {
                io.println("");
                break;
            } else if (answer.equals("y")) {
                io.println("");
                io.println("Which reference type?");
                io.println("\t1. Article");
                io.println("\t2. Book");
                io.println("\t3. Inproceedings");
                String refType = io.readLine();
                if (refType.matches("[123]")) { //lainaukset
                    addReference(refType);
                }
            }
        }
        close();
    }

    private void close() {
        if (references.isEmpty()) {
            io.println("No articles in memory");
        } else {
            printRef(references, wrp, io);
            exportRef(references, wrp, io, new FileWriterIO(), "sigproc.bib");
        }
    }

    private void printRef(ArrayList<Reference> rList, Wrapper wrp, IO io) {
        for (Reference reference : rList) {
            String bib = wrp.wrap(reference);
            io.println(bib);
        }
    }

    private void exportRef(ArrayList<Reference> rList, Wrapper wrp,
            IO io, IO fileIo, String fileName) {
        String bib = "";
        for (Reference reference : rList) {
            bib += wrp.wrap(reference) + n + n;
        }
        if (!fileIo.writeFile(fileName, bib)) {
            io.println("Error occurred while "
                    + "exporting file: " + fileName);
        }
    }

    private boolean scandeja(String s) {
        if (s.matches("^[a-zA-Z0-9!@#$%&*()_+=|<>?{}\\s\\[\\]~-]*$")) {
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
//CHECKSTYLE:OFF
    private void inputFields(Reference reference) {
        for (String inputField : reference.getRequiredFields()) {
            String inputLine;

            boolean failed = false;
            io.print(inputField + ": ");
            inputLine = io.readLine();

            if (scandeja(inputLine)
                    || (numerics.contains(inputField)
                    && !inputLine.matches("[0-9]+"))
                    || inputLine.isEmpty()) {
                io.println("");
                io.println("Invalid " + inputField);
                failed = true;
                break;
            }

            if (failed) {
                break;
            }
            reference.setField(inputField, inputLine);
            io.println("");
        }
    }
//CHECKSTYLE:ON
    private void setNumerics() {
        numerics.add("year");
        numerics.add("volume");
    }

}
