package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Article implements Reference {

    private ArrayList<String> requiredFields;
    private HashMap<String, String> fields;

    public Article() {
        this.requiredFields = new ArrayList<>();
        this.fields = new HashMap<>();
        initRequiredFields();
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
    public List<String> getRequiredFields() {
        return this.requiredFields;
    }

    @Override
    public String toString() {
        return "article";
    }
    
    
}
