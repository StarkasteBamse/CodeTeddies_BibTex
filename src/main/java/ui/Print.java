
package ui;

import io.IO;
import logic.App;


public class Print implements Command {
    private IO io;
    private App app;
    
    public Print(IO io, App app) {
        this.io = io;
        this.app = app;
    }
    
    @Override
    public void run() {
        if (app.isMemoryEmpty()) {
            io.println("No articles in memory");
        } else {
            app.printReference();
        }
    }
}
