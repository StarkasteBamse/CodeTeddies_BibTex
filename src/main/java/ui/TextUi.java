
package ui;

import domain.Reference;
import io.IO;
import java.util.ArrayList;
import java.util.HashMap;
import logic.App;

public class TextUi {
    private IO io;
    private String n;
    private App app;
    private HashMap<String,Runnable> printViewMap;
    
    public TextUi(IO io, App app) {
        this.app = app;
        this.io = io;
        this.printViewMap = initializePrintViews();
        this.n = System.getProperty("line.separator");
    }
    
    public void readCommandPrompt() {
        OUTER:
        while (true) {
            printCommands();
            String command = io.readLine().toLowerCase();
            io.println("");
            if (equalsCommand(command)) {
                break OUTER;
            }
        }
    }
//CHECKSTYLE:OFF  
    public HashMap<String,Runnable> initializePrintViews() {
        HashMap<String,Runnable> printViews = new HashMap<>();
        printViews.put("quit", () -> io.println("See you next time, "
                    + "bye byeh..."));
        printViews.put("whichType", () -> {
                io.println("");
                io.println("Which reference type?");
                });
        printViews.put("giveNumber", () -> io.print("Give a number of reference: "));
        printViews.put("invalidReferenceType", () -> io.println("Invalid reference type"));
        printViews.put("referencesSaved", () -> io.println("References saved." + n));
        printViews.put("loadDb", () -> io.println("References loaded to memory!"));
        printViews.put("memoryClear", () -> io.println("MY MEMORIES ARE GONE!"));
        printViews.put("dbClear", () -> io.println("DATABASE IS NOW GONE!"));
        printViews.put("emptyMemory", () -> io.println("No articles in memory"));
        printViews.put("noReferencesToExport", () -> io.println("No articles in memory, you don't want an empty .bib file!"));
        printViews.put("typeCommandAgain", () -> io.println("Please type in a command from list!"));
        printViews.put("enterFilename", () -> io.print("Enter filename (.bib-format): "));
        printViews.put("formatError", () -> io.println("Not proper format!"));
        printViews.put("exportError", () -> io.println("Error occurred while exporting file: "));
        return printViews;
    }
//CHECKSTYLE:ON
    
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
    
//CHECKSTYLE:OFF    
    public boolean equalsCommand(String command) {
        switch (command) {
            case "quit":
                printView(command);
                return true;
            case "add":
                printView("whichType");
                for (Integer i : app.getSupportedRefs().keySet()) {
                    io.println("\t" + i + ". " + app.getSupportedRefs().get(i));
                }
                printView("giveNumber");
                String referenceType = io.readLine();
                io.println("");
                
                if (referenceType.matches("[12345]")) {
                    int referenceCode = Integer.parseInt(referenceType);
                    if (!app.addReference(referenceCode)) {
                        printView("formatError");
                    } else {
                        io.println("New " + app.getSupportedRefs()
                                        .get(referenceCode).toString()
                                        + " added successfully");
                    }
                } else {
                    printView("invalidReferenceType");
                }
                break;
//            case "save":
//                printView("referencesSaved");
//                break;
            case "load":
                app.loadDatabase();
                printView("loadDb");
                break;
            case "clear":
                app.clearMemory();
                printView("memoryClear");
                break;
            case "delete":
                app.clearDatabase();
                printView("dbClear");
                break;
            case "print":
                if (app.isMemoryEmpty()) {
                    printView("emptyMemory");
                } else {
                    app.printReference();
                }
                break;
            case "file":
                if (app.isMemoryEmpty()) {
                    printView("noReferencesToExport");
                } else {
                    String fileName = readFileName();
                    if (!app.exportReference(fileName)) {
                        printView("exportError");
                        io.println(fileName);
                    }
                }
                break;
            default:
                printView("typeCommandAgain");
                break;
        }
        return false;
    }
//CHECKSTYLE:ON
    
    public String readFileName() {
        String fileName = "";
        while (true) {
            printView("enterFilename");
            fileName = io.readLine();
            if (fileName.contains(".bib")
                    && fileName.length() > ".bib".length()) {
                break;
            }
            io.println("");
        }
        return fileName;
    }
}
