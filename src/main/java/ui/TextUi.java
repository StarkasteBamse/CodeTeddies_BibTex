package ui;

import domain.Reference;
import io.IO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logic.App;

public class TextUi {

    private IO io;
    private String n;
    private App app;
    private HashMap<String, Runnable> printViewMap;

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

    public HashMap<String, Runnable> initializePrintViews() {
        HashMap<String, Runnable> printViews = new HashMap<>();
        printViews.put("quit", () -> io.println("See you next time!"));
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
                + "(delete) Deletes reference from memory and database" + n
                + "(edit) Edit fields of excisting reference" + n
                + "(load) Load references from database" + n
                + "(clear) Clear memory, Warning deletes all "
                + "data from memory" + n
                + "(deletedb) Delete database, Warning deletes all data" + n
                + "(print) Print to screen references in" + n
                + "(file) Export references into a file" + n
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
                new Quit(io, app).run();
//                printView(command);
                return true;
            case "add":
                new Add(io, app).run();
                //printAdd
                break;
//            case "save":
//                printView("referencesSaved");
//                break;
            case "delete":
                new Delete(io, app).run();
//                printDelete(command);
                break;

            case "edit":
                new Edit(io, app).run();
//                printEdit(command);
                break;
            case "load":
                new Load(io, app).run();
//                app.loadDatabase();
//                printView("loadDb");
                break;
            case "clear":
                new Clear(io, app).run();
//                app.clearMemory();
//                printView("memoryClear");
                break;
            case "deletedb":
                new DeleteDB(io, app).run();
//                app.clearDatabase();
//                printView("dbClear");
                break;
            case "print":
                new Print(io, app).run();
//                if (app.isMemoryEmpty()) {
//                    printView("emptyMemory");
//                } else {
//                    app.printReference();
//                }
                break;
            case "file":
                new File(io, app).run();
//                if (app.isMemoryEmpty()) {
//                    printView("noReferencesToExport");
//                } else {
//                    String fileName = readFileName();
//                    if (!app.exportReference(fileName)) {
//                        printView("exportError");
//                        io.println(fileName);
//                    }
//                }
                break;
            default:
                new Invalid(io, app).run();
//                printView("typeCommandAgain");
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

    private void printAdd(String command) {
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
    }

    //CHECKSTYLE:OFF
    private void printDelete(String command) {
        io.println("which reference do you want to delete");
        List<Reference> r1 = app.getReferences();

        for (int i = 0; i < r1.size(); i++) {
            io.println("[" + (i + 1) + "] " + r1.get(i).toString());
            HashMap<String, String> fieldsMap = r1.get(i).getFieldsMap();

            for (String fields : fieldsMap.keySet()) {
                io.println(fields + "\t:\t" + fieldsMap.get(fields));
            }
            io.println("");
        }
        io.print("give the number of the reference you want to delete (0 for cancel): ");

        int number = 0;
        try {
            number = Integer.parseInt(io.readLine());
        } catch (Exception e) {
            io.println("this ain't no number");
        }

        if (number != 0) {
            app.deleteReference(r1.get(number - 1));
            io.println("reference deleted!");
        }
    }

    private void printEdit(String command) {
        io.println("which reference do you want to edit");
        List<Reference> r2 = app.getReferences();

        for (int i = 0; i < r2.size(); i++) {
            io.println("[" + (i + 1) + "] " + r2.get(i).toString());
            HashMap<String, String> fieldsMap = r2.get(i).getFieldsMap();

            for (String fields : fieldsMap.keySet()) {
                io.println(fields + "\t:\t" + fieldsMap.get(fields));
            }
            io.println("");
        }
        io.print("give the number of the reference you want to edit (0 to cancel): ");

        int num = 0;
        try {
            num = Integer.parseInt(io.readLine());
        } catch (Exception e) {
            io.println("this ain't no number");
        }

        if (num == 0) {
            return;
        }

        Reference ref = r2.get(num - 1);
        io.println("FIELDS\t:\tVALUES");
        HashMap<String, String> fieldsMap = ref.getFieldsMap();
        //list fields with data
        for (String fields : fieldsMap.keySet()) {
            io.println(fields + "\t:\t" + fieldsMap.get(fields));
        }
        //list fields with no data
        for (String emptyField : ref.getOptionalFields()) {
            if (!fieldsMap.containsKey(emptyField)) {
                io.println(emptyField + "\t:");
            }
        }
        io.println("give the name of the field you want to edit (enter return): ");
        String field = io.readLine();
        if (field.equals("")) {
            return;
        }
        io.println("give a new value for the field");
        String value = io.readLine();

        if (!app.updateReference(ref, field, value)) {
            io.println("invalid input value for the field: " + field);
        }
        io.println("reference updated!");
    }
    //CHECKSTYLE:ON
}
