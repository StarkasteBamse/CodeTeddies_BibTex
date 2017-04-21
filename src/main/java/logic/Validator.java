/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    
    //CHECKSTYLE:OFF
    public List<String> stringFields;
    public List<String> numericFields;
    public final String regexString    = "^[a-zA-Z0-9!@#$%&*(),.:;_+=|<>?{}\\s\\[\\]~-]*$";
    public final String regexNumeric      = "[0-9]+";
    // public final String regexMonth     = "";
    //CHECKSTYLE:ON
    
    public Validator() {
        initStringFields();
        initNumericFields();
    }
    
    public boolean checkValue(String regex, String value) {
        return value.matches(regex);
    }
    
    public boolean checkInput(String inputType, String inputValue) {
        if (inputValue.length() == 0) {
            return false;
        }
        
        if (stringFields.contains(inputType)) {
            return checkValue(this.regexString, inputValue);
        } else if (numericFields.contains(inputType)) {
            return checkValue(this.regexNumeric, inputValue);
        } else {
            return false;
        }
    }

    private void initStringFields() {
        this.stringFields = new ArrayList<>();
        stringFields.add("author");
        stringFields.add("booktitle");
        stringFields.add("journal");
        stringFields.add("title");
        stringFields.add("publisher");
    }

    private void initNumericFields() {
        this.numericFields = new ArrayList<>();
        numericFields.add("year");
        numericFields.add("volume");
    }
    
}
