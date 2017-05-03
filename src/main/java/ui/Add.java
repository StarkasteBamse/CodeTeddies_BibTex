
package ui;

import io.IO;
import logic.App;


public class Add implements Command {
    
    private IO io;
    private App app;
    
    public Add(IO io, App app) {
        this.io = io;
        this.app = app;
    }
    
    @Override
    public void run() {
        io.println("");
        io.println("Which reference type?");
        for (Integer i : app.getSupportedRefs().keySet()) {
            io.println("\t" + i + ". " + app.getSupportedRefs().get(i));
        }
        io.print("Give a number of reference: ");
        String referenceType = io.readLine();
        io.println("");

        if (referenceType.matches("[12345]")) {
            int referenceCode = Integer.parseInt(referenceType);
            if (!app.addReference(referenceCode)) {
                io.println("Not proper format!");
            } else {
                io.println("New " + app.getSupportedRefs()
                        .get(referenceCode).toString()
                        + " added successfully");
            }
        } else {
            io.println("Invalid reference type");
        }
    }
}
