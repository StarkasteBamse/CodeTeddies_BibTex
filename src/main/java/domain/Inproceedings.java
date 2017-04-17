package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inproceedings implements Reference {
    private ArrayList<String> requiredFields;
    private HashMap<String, String> fields;

    public Inproceedings() {
        this.requiredFields = new ArrayList<>();
        this.fields = new HashMap<>();
        initRequiredFields();
    }
//CHECKSTYLE:OFF

    @Override
    public boolean hasRequiredFields() {
        for (String field : this.requiredFields) {
            if (this.fields.get(field) == null) {
                return false;
            }
        }
        return true;
    }
//CHECKSTYLE:ON

   @Override
    public void initRequiredFields() {
        this.requiredFields.add("author");
        this.requiredFields.add("title");
        this.requiredFields.add("booktitle");
        this.requiredFields.add("year");
    }

    @Override
    public void setField(String field, String value) {
        this.fields.put(field, value);
    }

    @Override
    public String getField(String field) {
        if (this.fields.get(field) == null) {
            return null;
        } else return this.fields.get(field);
    }
    
    @Override
    public List getRequiredFields() {
        return this.requiredFields;
    }
}