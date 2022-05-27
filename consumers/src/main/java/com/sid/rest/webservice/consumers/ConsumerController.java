package com.sid.rest.webservice.consumers;

import com.sid.rest.webservice.consumers.helloworld.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class ConsumerController {

    @Autowired
    private MessageSource messageSource;
    
   RestTemplate restTemplate = new RestTemplate();
   
   private static final String GET_ALL_USERS_API = "http://localhost:8081/api/hello-world";
    
    

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "hello-world-bean")
    public HelloWorld helloWorldBean() {
        return new HelloWorld("Hello world");
    }

    @GetMapping(path = "hello-world-bean/path-variable/{name}")
    public HelloWorld helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorld(String.format("Hello world, %s", name));
    }

    @GetMapping(path = "hello-world-internationalized")
    public String helloWorldInternationalized() {
        return messageSource.getMessage
                ("good.morning.message", null, "Default Message",
                        LocaleContextHolder.getLocale());
    }

    
    @GetMapping(path="calling-another-service")
    public String callingHelloWorld() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<String> response =   restTemplate.exchange(GET_ALL_USERS_API, HttpMethod.GET, entity, String.class);
		return response.getBody();
		
    }
}
