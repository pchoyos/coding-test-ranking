package com.idealista.infrastructure.utils;

public enum ResponseCode {

    // OK CODE  -------------------l---------------
    OK_CODE(200, "OK"),

    // HTTP ESTANDAR
    ILLEGAL_ARGUMENT_EXCEPTION(400, "Scores have not been calculated yet"),

    // DEV ERRORS (>500)
    NOT_EXPECTED_EXCEPTION(500, "NOT EXPECTED EXCEPTION");


    private int code;
    private String description;

    ResponseCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode(){
        return this.code;
    }
    public String getDescription() { return description; }
}
