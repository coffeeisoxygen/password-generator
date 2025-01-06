package com.kafka.password.model;

public class InputModel {

    private String passwordText;
    private int passwordLength;

    //constructor
    public InputModel(String passwordText, int passwordLength) {
        this.passwordText = passwordText;
        this.passwordLength = passwordLength;
    }

    // validation of not null
    public boolean isValid() {
        return passwordText != null && passwordLength > 0;
    }

    //getters
    public String getPasswordText() {
        return passwordText;
    }

    public int getPasswordLength() {
        return passwordLength;
    }

    //setters
    public void setPasswordText(String passwordText) {
        this.passwordText = passwordText;
    }

    public void setPasswordLength(int passwordLength) {
        this.passwordLength = passwordLength;
    }

}
