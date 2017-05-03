
package ui;

import io.IO;
import logic.App;


public class Load implements Command {

    private IO io;
    private App app;
    
    public Load(IO io, App app) {
        this.io = io;
        this.app = app;
    }
    
    @Override
    public void run() {
        app.loadDatabase();
        io.println("References loaded to memory!");
    }

}
