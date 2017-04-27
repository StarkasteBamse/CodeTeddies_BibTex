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
import ui.TextUi;
import wrapper.Wrapper;

public class App {

    private IO io;
    private TextUi ui;
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
        this.ui = new TextUi(io);
        this.supportedRefs = ui.initSupportedReferences();
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
//CHECKSTYLE:OFF
    public void run() {
        OUTER:
        while (true) {
            ui.printCommands();
            String command = io.readLine().toLowerCase();
            io.println("");
            if (equalsCommand(command)) {
                break OUTER;
            }
        }

        if (this.testMode) {
            this.dao.clearDatabase();
        }
        //close();
    }

    public boolean equalsCommand(String command) {
        switch (command) {
            case "quit":
                ui.printView(command);
                return true;
            case "add":
                ui.printView("whichType");
                for (Integer i : supportedRefs.keySet()) {
                    io.println("\t" + i + ". " + supportedRefs.get(i));
                }
                ui.printView("giveNumber");
                String refType = io.readLine();
                io.println("");
                
                if (refType.matches("[123]")) {
                    addReference(refType);
                } else {
                    ui.printView("invalidReferenceType");
                }
                break;
//          case "save":
//                ui.printView("referencesSaved");
//              break;
            case "load":
                ArrayList<Reference> temp = fetchDatabase();
                this.references = temp;
                ui.printView("loadDb");
                break;
            case "clear":
                this.references = new ArrayList<>();
                ui.printView("memoryClear");
                break;
            case "delete":
                this.dao.clearDatabase();
                ui.printView("dbClear");
                break;
            case "print":
                if (references.isEmpty()) {
                    ui.printView("emptyMemory");
                } else {
                    printRef(references, wrp, io);
                }
                break;
            case "file":
                if (references.isEmpty()) {
                    ui.printView("noReferencesToExport");
                } else {
                    exportRef(references, wrp, io, readFileName());
                }
                break;
            default:
                ui.printView("typeCommandAgain");
                break;
        }
        return false;
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
            ui.printView("enterFilename");
            fileName = io.readLine();
            if (fileName.contains(".bib")
                    && fileName.length() > ".bib".length()) {
                break;
            }
            io.println("");
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
            IO io, String fileName) {
        String bib = "";
        for (Reference reference : rList) {
            bib += wrp.wrap(reference) + n + n;
        }
        if (!io.writeFile(fileName, bib)) {
            ui.printView("exportError");
            io.println(fileName);
        }
    }

    private void addRefToList(Reference reference, IO io, ArrayList rList) {
        if (validator.checkRequiredFields(reference)) {
            rList.add(reference);
            dao.add(reference);
            io.println("New " + reference + " added successfully");
        } else {
            ui.printView("enterFilename");
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
        boolean isValidReference = false;
        if (allRequiredFields) {
            isValidReference = inputFields(reference, false);
        } // Add parse input for optional fields
        if (isValidReference) { //needed check for optional fields
            addRefToList(reference, io, references);
        } else {
            ui.printView("formatError");
        }
    }
//CHECKSTYLE:OFF
    private boolean inputFields(Reference reference, boolean required) {
        List<String> fields;
        reference.setID(dao.getNewId());
        
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
//CHECKSTYLE:ON

}
