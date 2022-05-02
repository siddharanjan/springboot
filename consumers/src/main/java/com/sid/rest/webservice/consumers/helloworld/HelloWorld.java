package com.sid.rest.webservice.consumers.helloworld;

public class HelloWorld {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HelloWorld(String message) {
        this.message = message;
    }
}
