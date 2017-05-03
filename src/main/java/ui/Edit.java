
package ui;

import domain.Reference;
import io.IO;
import java.util.HashMap;
import java.util.List;
import logic.App;


public class Edit implements Command {
    private IO io;
    private App app;
    
    public Edit(IO io, App app) {
        this.io = io;
        this.app = app;
    }
    
    @Override
    public void run() {
        io.println("which reference do you want to edit");
        List<Reference> r2 = app.getReferences();
        listReferences(r2);
        io.print("give the number of the reference " +
                "you want to edit (0 to cancel): ");

        int num = 0;
        try {
            num = Integer.parseInt(io.readLine());
        } catch (Exception e) {
            io.println("this ain't no number");
        }

        if (num == 0) {
            return;
        }

        Reference ref = r2.get(num - 1);
        listFields(ref);
        updateField(ref);
    }
    
    public void listReferences(List<Reference> r2) {
        for (int i = 0; i < r2.size(); i++) {
            io.println("[" + (i + 1) + "] " + r2.get(i).toString());
            HashMap<String, String> fieldsMap = r2.get(i).getFieldsMap();

            for (String fields : fieldsMap.keySet()) {
                io.println(fields + "\t:\t" + fieldsMap.get(fields));
            }
            io.println("");
        }
    }
    
    public void listFields(Reference ref) {
        io.println("FIELDS\t:\tVALUES");
        HashMap<String, String> fieldsMap = ref.getFieldsMap();
        //list fields with data
        for (String fields : fieldsMap.keySet()) {
            io.println(fields + "\t:\t" + fieldsMap.get(fields));
        }
        //list fields with no data
        for (String emptyField : ref.getOptionalFields()) {
            if (!fieldsMap.containsKey(emptyField)) {
                io.println(emptyField + "\t:");
            }
        }
    }
    
    public void updateField(Reference ref) {
        io.println("give the name of the field " + 
                "you want to edit (enter return): ");
        String field = io.readLine();
        if (field.equals("")) {
            return;
        }
        io.println("give a new value for the field");
        String value = io.readLine();

        if (!app.updateReference(ref, field, value)) {
            io.println("invalid input value for the field: " + field);
        }
        io.println("reference updated!");
    }
}
