package com.r0ngsh3n.springcloud.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class HelloWorldService {

    @GetMapping
    public String getUsersByName() {
        return "Hello World";
    }

}
