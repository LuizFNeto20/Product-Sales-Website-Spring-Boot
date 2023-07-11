package com.productsaleswebsitespringboot.model;

public enum Status {
    delivered(1),
    inProgress(2),
    sent(3),
    cancelled(4);

    private int code;


    private Status(int code) { 
        this.code = code;
    }


    public int getCode() {
        return code;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public static Status valueOf(int code) {
        for (Status value : Status.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid code");
    }
}
