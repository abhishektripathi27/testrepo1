package com.example.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.Greeting;

@RestController
//@Configuration
@RequestMapping(value="/publish", produces= {MediaType.APPLICATION_JSON_VALUE})
public class PublishMessageController {
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="firstName"+" lastName", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(), "Hello "+name);
//                            String.format(template, name));
    }

}
