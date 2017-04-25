package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logic.Validator;

public class Article implements Reference {

    private ArrayList<String> requiredFields;
    private ArrayList<String> optionalFields;
    private HashMap<String, String> fields;

    public Article() {
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

    public void setJournal(String journal) {
        setField("journal", journal);
    }

    public void setVolume(String volume) {
        setField("volume", volume);
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

    @Override
    public void initRequiredFields() {
        requiredFields.add("author");
        requiredFields.add("title");
        requiredFields.add("journal");
        requiredFields.add("year");
        requiredFields.add("volume");
    }
    
    @Override
    public void initOptionalFields() {
        optionalFields.add("number");
        optionalFields.add("pages");
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
        } else {
            return this.fields.get(field);
        }
    }

    @Override
    public List<String> getRequiredFields() {
        return this.requiredFields;
    }
    
    public List<String> getOptionalFields() {
        return this.optionalFields;
    }

    @Override
    public String toString() {
        return "article";
    }

    @Override
    public HashMap<String, String> getFieldsMap() {
        return this.fields;
    }

}
