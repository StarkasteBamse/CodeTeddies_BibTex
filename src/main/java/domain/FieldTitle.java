/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import logic.Validator;

public class FieldTitle implements Field {

    private String fieldValue;
    private Validator validator;
    // private final String regex = "";

    public FieldTitle(Validator validator) {
        this.validator = validator;
    }

    @Override
    public boolean setValue(String value) {
        if (validator.checkValue(validator.regexString, value)) {
            this.fieldValue = value;
            return true;
        }
        return false;
    }

    @Override
    public String getValue() {
        return fieldValue;
    }

}
