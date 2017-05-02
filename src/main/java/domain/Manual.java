package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import logic.Validator;

public class Manual implements Reference {

    private ArrayList<String> requiredFields;
    private ArrayList<String> optionalFields;
    private HashMap<String, String> fields;
    private String id;

    public Manual() {
        this.optionalFields = new ArrayList<>();
        this.requiredFields = new ArrayList<>();
        this.fields = new HashMap<>();
        initRequiredFields();
        initOptionalFields();
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

    private void initRequiredFields() {
        requiredFields.add("title");
    }

    private void initOptionalFields() {
        optionalFields.add("author");
        optionalFields.add("organization");
        optionalFields.add("address");
        optionalFields.add("edition");
        optionalFields.add("month");
        optionalFields.add("year");
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
        return "manual";
    }

    @Override
    public HashMap<String, String> getFieldsMap() {
        return this.fields;
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
            hash = 11 * hash + Objects.hashCode(this.getField(fieldValue));
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
        final Manual other = (Manual) obj;

        for (String requiredField : requiredFields) {
            String thisValue = this.getField(requiredField).toLowerCase();
            String otherValue = other.getField(requiredField).toLowerCase();

            if (!Objects.equals(thisValue, otherValue)) {
                return false;
            }
        }
        return true;
    }




}
