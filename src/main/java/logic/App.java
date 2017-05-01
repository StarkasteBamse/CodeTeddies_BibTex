package logic;

import database.DAO;
import database.ReferenceDAO;
import domain.Article;
import domain.Reference;
import domain.Inproceedings;
import domain.Book;
import domain.Manual;
import domain.PhdThesis;
import io.*;
import ui.TextUi;
import wrapper.Wrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.swing.text.StyledEditorKit;

public class App {

    private IO io;
    private DAO dao;
    private Wrapper wrp;
    private ArrayList<Reference> references;
    private String n = System.getProperty("line.separator");
    private ArrayList<String> numerics;
    private Validator validator;
    private boolean testMode;
    private HashMap<Integer, Reference> supportedRefs;

    public App(IO io, DAO dao) {
        this.io = io;
        this.dao = dao;
        this.wrp = new Wrapper();
        this.references = new ArrayList<>();
        this.numerics = new ArrayList<>();
        this.validator = new Validator();     
        this.supportedRefs = initSupportedReferences();
        this.testMode = true;
        
    }
//CHECKSTYLE:OFF    
    public HashMap initSupportedReferences() {
        HashMap<Integer, Reference> supportedRefs = new HashMap<>();
        supportedRefs.put(1, new Article());
        supportedRefs.put(2, new Book());
        supportedRefs.put(3, new Inproceedings());
        supportedRefs.put(4, new PhdThesis());
        supportedRefs.put(5, new Manual());
        
        return supportedRefs;
    }
//CHECKSTYLE:ON
    
    public App() {
        this(new ConsoleIO(), new ReferenceDAO(false));
        this.testMode = false;
    }

    public void run() {
        TextUi ui = new TextUi(io, this);
        ui.readCommandPrompt();
        if (this.testMode) {
            this.dao.clearDatabase();
        }
    }

    public void loadDatabase() {
        references = new ArrayList<>(dao.getAll());
    }
    
    public boolean isMemoryEmpty() {
        return references.isEmpty();
    }
    
    public void clearMemory() {
        this.references = new ArrayList<>();
    }

    public void clearDatabase() {
        this.dao.clearDatabase();
    }
    
    public void printReference() {
        
        for (Reference reference : references) {
            String bib = wrp.wrap(reference);
            io.println(bib);
        }
    }

    public boolean exportReference(String fileName) {
        String bib = "";
        for (Reference reference : references) {
            bib += wrp.wrap(reference) + n + n;
        }
        if (!io.writeFile(fileName, bib)) {
            return false;
        }
        return true;
    }

    private void addReferenceToList(Reference reference) {
        
        if (validator.checkRequiredFields(reference)) {
            this.references.add(reference);
            dao.add(reference);
        } else {
            io.print("");
        }
    }

    public boolean addReference(int referenceType) {
        Reference reference = supportedRefs.get(referenceType);
        io.println("BibTex an " + reference + "!");
        
        boolean allRequiredFields = inputFields(reference, true);
        boolean isValidReference = false;
        
        if (allRequiredFields) {
            isValidReference = inputFields(reference, false);
        }
        
        // Add parse input for optional fields
        if (isValidReference) { //needed check for optional fields
            addReferenceToList(reference);
            return true;
        } else {
            return false;
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

    public HashMap<Integer, Reference> getSupportedRefs() {
        return supportedRefs;
    }

    public ArrayList<Reference> getReferences() {
        return references;
    }
}
