package domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Inproceedings extends Reference {
    
    public String author;
    public String title;
    public String booktitle;
    public String year;

    private ArrayList<String> requiredFields;
    private HashMap<String, String> fields;

    public Inproceedings() {
        this.requiredFields = new ArrayList<>();
        this.fields = new HashMap<>();
        initRequiredFields();
    }

//CHECKSTYLE:OFF
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBookTitle(String BookTitle) {
        this.booktitle = BookTitle;
    }

    public void setYear(String year) {
        this.year = year;
    }

    // @Override
    public boolean hasRequiredFields() {
        if (this.author == null) {
            return false;
        } else if (this.title == null) {
            return false;
        } else if (this.booktitle == null) {
            return false;
        } else if (this.year == null) {
            return false;
        } else {
            return true;
        }
    }
//CHECKSTYLE:ON

    @Override
    void initRequiredFields() {
        this.requiredFields.add("author");
        this.requiredFields.add("title");
        this.requiredFields.add("booktitle");
        this.requiredFields.add("year");
    }

}