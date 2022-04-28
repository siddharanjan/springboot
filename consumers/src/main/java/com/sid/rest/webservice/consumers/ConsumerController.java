package com.sid.rest.webservice.consumers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
     return "Hello World";
    }

    @GetMapping(path="hello-world-bean")
    public HelloWorld helloWorldBean() {
        return new HelloWorld("Hello world");
    }
}
