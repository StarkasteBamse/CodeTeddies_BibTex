
package ui;

import io.IO;
import logic.App;


public class File implements Command {
    private IO io;
    private App app;
    
    public File(IO io, App app) {
        this.io = io;
        this.app = app;
    }
    
    @Override
    public void run() {
        if (app.isMemoryEmpty()) {
            io.println("No articles in memory, you don't want an empty .bib file!");
        } else {
            String fileName = readFileName();
            if (!app.exportReference(fileName)) {
                io.println("Error occurred while exporting file: ");
                io.println(fileName);
            }
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
            io.println("");
        }
        return fileName;
    }
}
