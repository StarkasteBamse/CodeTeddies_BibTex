package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logic.Validator;

public class Article implements Reference {

    private ArrayList<String> requiredFields;
    private ArrayList<Field> requiredFieldObjects;
    // private HashMap<String, String> fields;
    private HashMap<String, Field> fieldObjects;

    public Article() {
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

    public void setJournal(String journal, Validator validator) {
        setField("journal", journal, validator);
    }

    public void setVolume(String volume, Validator validator) {
        setField("volume", volume, validator);
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
        requiredFields.add("journal");
        requiredFields.add("year");
        requiredFields.add("volume");
    }

    @Override
    public boolean setField(String field, String value, Validator validator) {
        // this.fields.put(field, value);
        if (value == null) {
            return false;
        }
        Field newField = getFieldType(field);
        if (newField.setValue(value, validator) && newField != null) {
            this.fieldObjects.put(field, newField);
            return true;
        }

        return false;
    }

    /* Prototype for setField with validator passed */
    public boolean setFieldObject(String field, String value,
            Validator validator) {
        Field newField = getFieldType(field);
        if (newField.setValue(value, validator)) {
            this.fieldObjects.put(field, newField);
            return true;
        }
        return false;
    }

    @Override
    public String getField(String field) {
        if (this.fieldObjects.get(field) == null) {
            return null;
        } else {
            return this.fieldObjects.get(field).getValue();
        }
    }

    @Override
    public List<String> getRequiredFields() {
        return this.requiredFields;
    }

    @Override
    public String toString() {
        return "article";
    }

    private Field getFieldType(String field) {
        switch (field) {
            case "author":
                return new FieldAuthor();
            case "title":
                return new FieldTitle();
            case "journal":
                return new FieldJournal();
            case "year":
                return new FieldYear();
            case "volume":
                return new FieldVolume();
            default:
                return null;
        }
    }

}
