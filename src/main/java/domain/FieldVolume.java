/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import logic.Validator;

public class FieldVolume implements Field {

    private final String fieldName = "volume";
    private String fieldValue;
    // private Validator validator;

    public FieldVolume() {
        // this.validator = validator;
    }

    @Override
    public boolean setValue(String value, Validator validator) {
        if (validator.checkValue(validator.regexNumeric, value)) {
            this.fieldValue = value;
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
