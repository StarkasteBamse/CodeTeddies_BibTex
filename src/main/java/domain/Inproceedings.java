package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logic.Validator;

public class Inproceedings implements Reference {

    private ArrayList<String> requiredFields;
    // private HashMap<String, String> fields;
    private HashMap<String, Field> fieldObjects;

    public Inproceedings() {
        this.requiredFields = new ArrayList<>();
        // this.fields = new HashMap<>();
        initRequiredFields();

        this.fieldObjects = new HashMap<>();
    }

    public void setAuthor(String author) {
        setField("author", author);
    }

    public void setTitle(String title) {
        setField("title", title);
    }

    public void setYear(String year) {
        setField("year", year);
    }

    public void setBookTitle(String bookTitle) {
        setField("booktitle", bookTitle);
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

    @Override
    public void initRequiredFields() {
        this.requiredFields.add("author");
        this.requiredFields.add("title");
        this.requiredFields.add("booktitle");
        this.requiredFields.add("year");
    }

    @Override
    public void setField(String field, String value) {
        // this.fields.put(field, value);
        Field newField = getFieldType(field);
        if ((newField != null) && (value != null)) {
            newField.setValue(value);
            this.fieldObjects.put(field, newField);
        }
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
        return "inproceedings";
    }

    private Field getFieldType(String field) {
        switch (field) {
            case "author":
                return new FieldAuthor();
            case "title":
                return new FieldTitle();
            case "booktitle":
                return new FieldBookTitle();
            case "year":
                return new FieldYear();
            default:
                return null;
        }
    }
}
