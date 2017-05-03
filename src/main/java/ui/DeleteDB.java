
package ui;

import io.IO;
import logic.App;


public class DeleteDB implements Command {
    private IO io;
    private App app;
    
    public DeleteDB(IO io, App app) {
        this.io = io;
        this.app = app;
    }
    
    @Override
    public void run() {
        app.clearDatabase();
        io.println("DATABASE IS NOW GONE!");
    }
}
