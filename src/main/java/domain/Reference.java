package domain;


import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public abstract class Reference {

    /**
     * TODO: Superclass for references
     * Data structures for storing all the fields and their values conveniently
     * for easy access and control.
     * Should contain mock functions too.
     * Fill requiredFields in each Class Constructor separately with correct
     * values.
     */

    /*
    public ArrayList<String> requiredFields;
    public HashMap<String, String> fields;

    public Reference() {
        this.requiredFields = new ArrayList<>();
        this.fields = new HashMap<>();
    }

    public void setField(String field, String value) {
        this.fields.put(field, value);
    }

    public String getField(String field) {
        if (this.fields.get(field) == null) {
            return null;
        } else return this.fields.get(field);
    }

    public List getRequiredFields() {
        return this.requiredFields;
    }

    public boolean hasRequiredFields() {
        for (String s : this.requiredFields) {
            if (!this.fields.containsKey(s)) return false;
        }
        return true;
    }

    */
    abstract void initRequiredFields();
}
