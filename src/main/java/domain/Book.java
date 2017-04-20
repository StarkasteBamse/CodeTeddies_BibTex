package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logic.Validator;

public class Book implements Reference {

    private ArrayList<String> requiredFields;
    // private HashMap<String, String> fields;
    private HashMap<String, Field> fieldObjects;

    public Book() {
        this.requiredFields = new ArrayList<>();
        // this.fields = new HashMap<>();
        initRequiredFields();
        
        this.fieldObjects = new HashMap<>();
    }
//CHECKSTYLE:OFF

    public void setAuthor(String author, Validator validator) {
        setField("author", author, validator);
    }
    
    public void setTitle(String title, Validator validator) {
        setField("title", title, validator);
    }
    
    public void setYear(String year, Validator validator) {
        setField("year", year, validator);
    }
    
    public void setPublisher(String publisher, Validator validator) {
        setField("publisher", publisher, validator);
    }

    @Override
    public boolean hasRequiredFields() {
        for (String field : this.requiredFields) {
            if (!this.fieldObjects.containsKey(field)) {
                return false;
            }
        }
        return true;
    }
//CHECKSTYLE:ON

    @Override
    public void initRequiredFields() {
        requiredFields.add("author");
        requiredFields.add("title");
        requiredFields.add("publisher");
        requiredFields.add("year");
    }

    @Override
    public boolean setField(String field, String value, Validator validator) {
        // this.fields.put(field, value);
        if (value == null) return false;
        Field newField = getFieldType(field);
        if (newField.setValue(value, validator) && newField != null) {
            this.fieldObjects.put(field, newField);
            return true;
        }

        return false;
    }

    @Override
    public String getField(String field) {
        if (this.fieldObjects.get(field) == null) {
            return null;
        } else return this.fieldObjects.get(field).getValue();
    }
    
    @Override
    public List<String> getRequiredFields() {
        return this.requiredFields;
    }

    @Override
    public String toString() {
        return "book";
    }

    private Field getFieldType(String field) {
        switch (field) {
            case "author":
                return new FieldAuthor();
            case "title":
                return new FieldTitle();
            case "publisher":
                return new FieldPublisher();
            case "year":
                return new FieldYear();
            default:
                return null;
        }
    }
}

