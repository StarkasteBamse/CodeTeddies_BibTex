
package ui;

import domain.Reference;
import io.IO;
import java.util.HashMap;
import java.util.List;
import logic.App;


public class Delete implements Command {
    private IO io;
    private App app;
    
    public Delete(IO io, App app) {
        this.io = io;
        this.app = app;
    }
    
    @Override
    public void run() {
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
}
