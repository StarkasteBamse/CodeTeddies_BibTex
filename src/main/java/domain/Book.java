package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logic.Validator;

public class Book implements Reference {

    private ArrayList<String> requiredFields;
    private ArrayList<String> optionalFields;
    private HashMap<String, String> fields;

    public Book() {
        this.optionalFields = new ArrayList<>();
        this.requiredFields = new ArrayList<>();
        this.fields = new HashMap<>();
        initRequiredFields();
        initOptionalFields();
    }
//CHECKSTYLE:OFF

    public void setAuthor(String author) {
        setField("author", author);
    }
    
    public void setTitle(String title) {
        setField("title", title);
    }
    
    public void setYear(String year) {
        setField("year", year);
    }
    
    public void setPublisher(String publisher) {
        setField("publisher", publisher);
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
//CHECKSTYLE:ON
    private void initRequiredFields() {
        requiredFields.add("author");
        requiredFields.add("title");
        requiredFields.add("publisher");
        requiredFields.add("year");
    }
    
    private void initOptionalFields() {
        optionalFields.add("number");
        optionalFields.add("series");
        optionalFields.add("address");
        optionalFields.add("edition");
        optionalFields.add("month");
        optionalFields.add("note");
        optionalFields.add("key");
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
        } else return this.fields.get(field);
    }
    
    @Override
    public List<String> getRequiredFields() {
        return this.requiredFields;
    }

    @Override
    public String toString() {
        return "book";
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

