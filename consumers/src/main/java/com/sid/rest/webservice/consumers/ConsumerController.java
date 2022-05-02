package com.sid.rest.webservice.consumers;

import com.sid.rest.webservice.consumers.helloworld.HelloWorld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(path="hello-world-bean/path-variable/{name}")
    public HelloWorld helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorld(String.format("Hello world, %s", name));
    }

}
