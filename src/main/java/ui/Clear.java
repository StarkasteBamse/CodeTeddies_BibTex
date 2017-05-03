
package ui;

import io.IO;
import logic.App;


public class Clear implements Command {
    private IO io;
    private App app;
    
    public Clear(IO io, App app) {
        this.io = io;
        this.app = app;
    }
    
    @Override
    public void run() {
        app.clearMemory();
        io.println("MY MEMORIES ARE GONE!");
    }
}
