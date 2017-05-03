
package ui;

import io.IO;
import logic.App;


public class Quit implements Command {
    private IO io;
    private App app;
    
    public Quit(IO io, App app) {
        this.io = io;
        this.app = app;
    }
    
    @Override
    public void run() {
        io.println("See you next time!");
    }
}
