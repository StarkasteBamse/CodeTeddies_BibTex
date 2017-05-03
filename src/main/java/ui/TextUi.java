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
    private HashMap<String, Command> commandMap;

    public TextUi(IO io, App app) {
        this.app = app;
        this.io = io;
        this.commandMap = initializeCommands();
        this.n = System.getProperty("line.separator");
    }

    public void readCommandPrompt() {
        OUTER:
        while (true) {
            printCommands();
            String command = io.readLine().toLowerCase();
            io.println("");
            if (command.matches("quit")) {
                commandMap.get(command).run();
                break OUTER;
            } else {
                runCommand(command);
            }
        }
    }
    
    public void runCommand(String command) {
        if (commandMap.containsKey(command)) {
            commandMap.get(command).run();
        } else {
            commandMap.get("invalid").run();
        }
    }

    public HashMap<String, Command> initializeCommands() {
        HashMap<String, Command> commands = new HashMap<>();
        commands.put("add", new Add(io, app));
        commands.put("delete", new Delete(io, app));
        commands.put("edit", new Edit(io, app));
        commands.put("load", new Load(io, app));
        commands.put("clear", new Clear(io, app));
        commands.put("deletedb", new DeleteDB(io, app));
        commands.put("print", new Print(io, app));
        commands.put("file", new File(io, app));
        commands.put("invalid", new Invalid(io, app));
        commands.put("quit", new Quit(io, app));
        return commands;
    }

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
}
