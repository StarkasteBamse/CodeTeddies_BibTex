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
import java.util.HashMap;
import java.util.List;
import javax.swing.text.StyledEditorKit;
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
    private HashMap<Integer, String> supportedRefs;

    public App(IO io, DAO dao) {
        this.io = io;
        this.dao = dao;
        this.wrp = new Wrapper();
        this.references = new ArrayList<>();
        this.numerics = new ArrayList<>();
        this.validator = new Validator();
        this.supportedRefs = initSupRefs();
        this.testMode = true;
    }

    public App() {
        this(new ConsoleIO(), new ReferenceDAO(false));
        this.testMode = false;
    }
//CHECKSTYLE:OFF
    private HashMap initSupRefs() {
        HashMap<Integer, String> supportedRefs = new HashMap<>();
        supportedRefs.put(1, "Article");
        supportedRefs.put(2, "Book");
        supportedRefs.put(3, "Inproceedings");
        return supportedRefs;
    }
//CHECKSTYLE:ON
    private ArrayList<Reference> fetchDatabase() {
        ArrayList<Reference> fReferences = new ArrayList<>();
        fReferences.addAll(dao.getAll());
        return fReferences;
    }
//CHECKSTYLE:OFF
    public void run() {
        while (true) {
            io.print("Commands: " + n
                    + "(add) Add reference" + n
                    // + "(save) Save references to database" + n
                    // at the moment (add)automaticly saves new reference to 
                    // database, need dublicate regocnition for (save) to work
                    + "(load) Load references from database" + n
                    + "(clear) Clear memory, Warning deletes all "
                    + "data from memory" + n
                    + "(delete) Delete database, Warning deletes all data" + n
                    + "(print) Print to screen references in" + n
                    + "(file) Export references in to a file" + n
                    + "(quit) Stop using this program" + n
                    + "Command: ");
            String command = io.readLine().toLowerCase();
            io.println("");

            if (command.equals("quit")) {
                io.println("See you next time, bye byeh...");
                break;
            } else if (command.equals("add")) {
                io.println("");
                io.println("Which reference type?");
                for (Integer i : supportedRefs.keySet()) {
                    io.println("\t" + i + ". " + supportedRefs.get(i));
                }
                io.print("Give a number of reference: ");
                String refType = io.readLine();
                io.println("");
                if (refType.matches("[123]")) { //lainaukset
                    addReference(refType);
                } else {
                    io.println("Invalid reference type");
                }

//            } else if (command.equals("save")) {
//                io.println("oho!");
            } else if (command.equals("load")) {
//              Dumps now database over the data in memory, needs refactorin for
//              dublicates recognition!
                ArrayList<Reference> temp = fetchDatabase();
                this.references = temp;
                io.println("References loaded to memory!");
            } else if (command.equals("clear")) {
                this.references = new ArrayList<>();
                io.println("MY MEMORIES ARE GONE!");
            } else if (command.equals("delete")) {
                this.dao.clearDatabase();
                io.println("DATABASE IS NOW GONE!");
            } else if (command.equals("print")) {
                if (references.isEmpty()) {
                    io.println("No articles in memory");
                } else {
                    printRef(references, wrp, io);
                }
            } else if (command.equals("file")) {
                if (references.isEmpty()) {
                    io.println("No articles in memory, you don't want an "
                            + "empty .bib file!");
                } else {
                    exportRef(references, wrp, io, new FileWriterIO(), readFileName());
                }
            } else {
                io.println("Please type in a command from list!");
            }
        }

        if (this.testMode) {
            this.dao.clearDatabase();
        }
        //close();
    }

//    private void close() {
//        if (references.isEmpty()) {
//            io.println("No articles in memory");
//        } else {
//            printRef(references, wrp, io);
//            exportRef(references, wrp, io, new FileWriterIO(), readFileName());
//        }
//        if (this.testMode) {
//            this.dao.clearDatabase();
//        }
//    }
//CHECKSTYLE:ON
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
        boolean allRequiredFields = inputFields(reference, true);
        boolean isThisGoodReference = false;
        if (allRequiredFields) {
            isThisGoodReference = inputFields(reference, false);
        } // Add parse input for optional fields
        if (isThisGoodReference) { //needed check for optional fields
            addRefToList(reference, io, references);
        } else {
            io.println("Not proper format!");
        }
    }

    private boolean inputFields(Reference reference, boolean required) {
        List<String> fields;
        if (required) {
            fields = reference.getRequiredFields();
        } else {
            fields = reference.getOptionalFields();
        }
        for (String inputField : fields) {
            String inputLine;
            String reqOrOpt;
            if (required) {
                reqOrOpt = "(required)";
            } else {
                reqOrOpt = "(optional)";
            }
            io.print(inputField + " " + reqOrOpt + ": ");
            inputLine = io.readLine();
<<<<<<< HEAD
            
=======
>>>>>>> master
            if (!validator.checkInput(inputField, inputLine, required)) {
                io.println("");
                io.println("Invalid " + inputField);
                return false;
            }
            if (inputLine.isEmpty()) {
                io.println("");
                continue;
            }
            reference.setField(inputField, inputLine);
            io.println("");
        }
        return true;
    }

}
