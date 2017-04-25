package logic;

import database.DAO;
import database.ReferenceDAO;
import domain.Article;
import domain.Reference;
import domain.Inproceedings;
import domain.Book;
import io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import wrapper.Wrapper;

public class App {

    private IO io;
    private DAO dao;
    private Wrapper wrp;
    private ArrayList<Reference> references;
    private String n = System.getProperty("line.separator");
    private ArrayList<String> numerics;
    private Validator validator;
    private boolean testMode;

    public App(IO io, DAO dao) {
        this.io = io;
        this.dao = dao;
        this.wrp = new Wrapper();
        this.references = fetchDatabase();
        this.numerics = new ArrayList<>();
        this.validator = new Validator();
        this.testMode = true;
    }
    
    public App() {
        this(new ConsoleIO(), new ReferenceDAO(false));
        this.testMode = false;
    }
    
    private ArrayList<Reference> fetchDatabase() {
        ArrayList<Reference> fReferences = new ArrayList<>();
        fReferences.addAll(dao.getAll());
        return fReferences;
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
                } else {
                    io.println("Invalid reference type");
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
            exportRef(references, wrp, io, new FileWriterIO(), readFileName());
        }
        if (this.testMode) {
            this.dao.clearDatabase();
        }
    }

    public String readFileName() {
        String fileName = "";
        while (true) {
            io.print("Enter filename (.bib-format): ");
            fileName = io.readLine();
            if (fileName.contains(".bib")
                    && fileName.length() > ".bib".length()) {
                break;
            }
            System.out.println("");
        }
        return fileName;
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

    private void addRefToList(Reference reference, IO io, ArrayList rList) {
        if (validator.checkRequiredFields(reference)) {
            rList.add(reference);
            dao.add(reference);
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
        inputFields(reference, true);
        // inputFields(reference, false); // Add parse input for optional fields
        addRefToList(reference, io, references);
    }

    private void inputFields(Reference reference, boolean required) {
        List<String> fields;
        if (required) {
            fields = reference.getRequiredFields();
        } else {
            fields = reference.getOptionalFields();
        }
        for (String inputField : fields) {
            String inputLine;

            io.print(inputField + ": ");
            inputLine = io.readLine();

            if (!validator.checkInput(inputField, inputLine, true)) {
                io.println("");
                io.println("Invalid " + inputField);
                break;
            }

            reference.setField(inputField, inputLine);
            io.println("");
        }
    }
    
}
