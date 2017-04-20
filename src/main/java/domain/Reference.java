package domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import logic.Validator;

public interface Reference {

    boolean setField(String field, String value, Validator validator);
    String getField(String field);
    List<String> getRequiredFields();
    boolean hasRequiredFields();
    void initRequiredFields();

    
}
