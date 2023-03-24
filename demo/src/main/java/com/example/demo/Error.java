package com.example.demo;

public class Error {

    private String message;
    private static final String MESSAGE = "The puzzle is not valid.";


    public Error() {
        this.message = MESSAGE;
    }

    public String getError() {
        return message;
    }
}
