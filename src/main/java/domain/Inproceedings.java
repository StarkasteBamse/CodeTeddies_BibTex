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
            if (!this.fields.containsKey(field)) {
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
        return "inproceedings";
    }
}