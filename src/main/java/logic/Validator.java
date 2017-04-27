/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.Reference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Validator {

    //CHECKSTYLE:OFF
    public List<String> stringFields;
    public List<String> numericFields;
    public final String regexString = "^.*$";
//    public final String regexString1 = "^[a-zA-Z0-9åäö!@#$%&*(),.:;_+=|<>?{}\\s\\[\\]~-]*$";
    public final String regexNumeric = "[0-9]+";
    // public final String regexMonth     = "";
    //CHECKSTYLE:ON

    public Validator() {
        initStringFields();
        initNumericFields();
    }

    public boolean checkValue(String regex, String value) {
        return value.matches(regex);
    }

    public boolean checkInput(String inputType, 
                              String inputValue,
                              boolean required) {
        if (required && inputValue.length() == 0) {
            return false;
        }

        if (inputValue.length() == 0) {
            return true;
        } else if (stringFields.contains(inputType)) {
            return checkValue(this.regexString, inputValue);
        } else if (numericFields.contains(inputType)) {
            return checkValue(this.regexNumeric, inputValue);
        } else {
            return false;
        }
    }

    public boolean checkRequiredFields(Reference reference) {
        for (String field : reference.getRequiredFields()) {
            if (!reference.getFieldsMap().containsKey(field)) {
                return false;
            }
        }

        return true;
    }

    private void initStringFields() {
        this.stringFields = new ArrayList<>();
        stringFields.add("address");
        stringFields.add("annote");
        stringFields.add("author");
        stringFields.add("booktitle");
        stringFields.add("crossref");
        stringFields.add("edition");
        stringFields.add("editor");
        stringFields.add("howpublished");
        stringFields.add("institution");
        stringFields.add("journal");
        stringFields.add("key");
        stringFields.add("month");
        stringFields.add("note");
        stringFields.add("organization");
        stringFields.add("school");
        stringFields.add("title");
        stringFields.add("publisher");
        stringFields.add("series");
        stringFields.add("type");
    }

    private void initNumericFields() {
        this.numericFields = new ArrayList<>();
        stringFields.add("chapter");
        stringFields.add("number");
        stringFields.add("pages");
        numericFields.add("year");
        numericFields.add("volume");
    }

}
