package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logic.Validator;

public class Inproceedings implements Reference {

    private ArrayList<String> requiredFields;
    private ArrayList<String> optionalFields;
    private HashMap<String, String> fields;

    public Inproceedings() {
        this.optionalFields = new ArrayList<>();
        this.requiredFields = new ArrayList<>();
        this.fields = new HashMap<>();
        initRequiredFields();
        initOptionalFields();

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

    private void initRequiredFields() {
        this.requiredFields.add("author");
        this.requiredFields.add("title");
        this.requiredFields.add("booktitle");
        this.requiredFields.add("year");
    }
    
    private void initOptionalFields() {
        optionalFields.add("editor");
        optionalFields.add("volume");
        optionalFields.add("series");
        optionalFields.add("pages");
        optionalFields.add("address");
        optionalFields.add("month");
        optionalFields.add("organization");
        optionalFields.add("publisher");
        optionalFields.add("note");
        optionalFields.add("key");
    }

    @Override
    public boolean hasRequiredFields() {
        for (String field : this.requiredFields) {
            if (!this.fields.containsKey(field)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setField(String field, String value) {
        if (requiredFields.contains(field) || optionalFields.contains(field)) {
            this.fields.put(field, value);
        }
    }

    @Override
    public String getField(String field) {
        if (this.fields.get(field) == null) {
            return null;
        } else {
            return this.fields.get(field);
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

    @Override
    public HashMap<String, String> getFieldsMap() {
        return this.fields;
    }

    @Override
    public List<String> getOptionalFields() {
        return optionalFields;
    }
}
