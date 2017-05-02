package domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import logic.Validator;

public interface Reference {

    void setField(String field, String value);
    String getField(String field);
    String getID();
    void setID(String id);
    List<String> getRequiredFields();
    boolean hasRequiredFields();
    List<String> getOptionalFields();
    HashMap<String, String> getFieldsMap();


}
