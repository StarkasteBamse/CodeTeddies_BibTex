
package ui;

import io.IO;
import java.util.HashMap;

public class TextUi {
    private IO io;
    private String n;
    private HashMap<String,Runnable> printViewMap;
    
    public TextUi(IO io) {
        this.io = io;
        this.printViewMap = initializePrintViews();
        this.n = System.getProperty("line.separator");
    }
    
    public HashMap initSupportedReferences() {
        HashMap<Integer, String> supportedRefs = new HashMap<>();
        supportedRefs.put(1, "Article");
        supportedRefs.put(2, "Book");
        supportedRefs.put(3, "Inproceedings");
        return supportedRefs;
    }
    
    public void printCommands() {
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
    }
    
    public void printView(String command) {
        this.printViewMap.get(command).run();
    }
    
    public HashMap<String,Runnable> initializePrintViews() {
        HashMap<String,Runnable> printViews = new HashMap<>();
        printViews.put("quit", () -> io.println("See you next time, bye byeh..."));
        printViews.put("whichType", () -> {
                io.println("");
                io.println("Which reference type?");
        });
        printViews.put("giveNumber", () 
            -> io.print("Give a number of reference: "));
        printViews.put("invalidReferenceType", () 
            -> io.println("Invalid reference type"));
        printViews.put("referencesSaved", () 
            -> io.println("References saved." + n));
        printViews.put("loadDb", () 
            -> io.println("References loaded to memory!"));
        printViews.put("memoryClear", () 
            -> io.println("MY MEMORIES ARE GONE!"));
        printViews.put("dbClear", () 
            -> io.println("DATABASE IS NOW GONE!"));
        printViews.put("emptyMemory", () 
            -> io.println("No articles in memory"));
        printViews.put("noReferencesToExport", () 
            -> io.println("No articles in memory, you don't "
                        + "want an empty .bib file!"));
        printViews.put("typeCommandAgain", () 
            -> io.println("Please type in a command from list!"));
        printViews.put("enterFilename", () 
            -> io.print("Enter filename (.bib-format): "));
        printViews.put("formatError", () 
            -> io.println("Not proper format!"));
        printViews.put("exportError", () 
            -> io.println("Error occurred while exporting file: "));
        return printViews;
    }
}
