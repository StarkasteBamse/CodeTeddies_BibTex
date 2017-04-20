/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

public class Validator {
    
    //CHECKSTYLE:OFF
    public final String regexString    = "^[a-zA-Z0-9!@#$%&*(),.:;_+=|<>?{}\\s\\[\\]~-]*$";
    public final String regexNumeric      = "[0-9]+";
    // public final String regexMonth     = "";
    //CHECKSTYLE:ON
    
    public boolean checkTitle() {
        return true;
    }
    
    public boolean checkAuthor() {
        return true;
    }
    
    public boolean checkEditor() {
        return true;
    }
    
    public boolean checkPublisher() {
        return true;
    }
    
    public boolean checkBookTitle() {
        return true;
    }
    
    public boolean checkValue(String regex, String value) {
        return value.matches(regex);
    }
    
}
