
package ui;

import io.IO;
import logic.App;


public class Invalid implements Command {
    private IO io;
    private App app;
    
    public Invalid(IO io, App app) {
        this.io = io;
        this.app = app;
    }
    
    @Override
    public void run() {
        io.println("Please type in a command from list!");
    }
}
