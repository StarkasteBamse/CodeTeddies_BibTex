/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import logic.Validator;

class FieldBookTitle implements Field {
    
    private final String fieldName = "booktitle";
    private String fieldValue;

    public FieldBookTitle() {
    }

    @Override
    public boolean setValue(String value, Validator validator) {
        if (validator.checkValue(validator.regexString, value)) {
            fieldValue = value;
            return true;
        }

        return false;
    }

    @Override
    public String getValue() {
        return fieldValue;
    }

    @Override
    public String toString() {
        return fieldName + " " + fieldValue;
    }
    
}
