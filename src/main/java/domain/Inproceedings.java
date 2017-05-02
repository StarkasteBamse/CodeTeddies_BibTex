package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import logic.Validator;

public class Inproceedings implements Reference {

    private ArrayList<String> requiredFields;
    private ArrayList<String> optionalFields;
    private HashMap<String, String> fields;
    private String id;

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

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public void setID(String id) {
        this.id = id;
    }
//CHECKSTYLE:OFF 

    @Override
    public int hashCode() {
        int hash = 3;
        for (String requiredField : requiredFields) {
            String fieldValue = this.getField(requiredField).toLowerCase();
            hash = 11 * hash + Objects.hashCode(fieldValue);
        }
        return hash;
    }
//CHECKSTYLE:ON

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Inproceedings other = (Inproceedings) obj;

        for (String requiredField : requiredFields) {
            String thisValue = this.getField(requiredField).toLowerCase();
            String otherValue = other.getField(requiredField).toLowerCase();

            if (!Objects.equals(thisValue, otherValue)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeField(String field) {
        if (fields.containsKey(field)) {
            fields.remove(field);
            return true;
        }
        return false;

    }

}
