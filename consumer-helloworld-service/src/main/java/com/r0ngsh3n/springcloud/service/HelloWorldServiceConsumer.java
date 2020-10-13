package com.r0ngsh3n.springcloud.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("test-consumer")
public class HelloWorldServiceConsumer {

    private final RestTemplate restTemplate;
    private final HelloWorldClient helloWorldClient;

    public HelloWorldServiceConsumer(RestTemplate restTemplate, HelloWorldClient helloWorldClient){
        super();
        this.restTemplate = restTemplate;
        this.helloWorldClient = helloWorldClient;
    }

    @GetMapping("/withRestTemplate")
    public String testHelloWorldService(){
        String answer = this.restTemplate.getForObject("http://producer-helloworld-service/test/greeting", String.class);
        return getAnswer(answer);
    }

    @GetMapping("/withFeign")
    public String testHelloWorldServiceFeign(){
        String answer = this.helloWorldClient.getGreeting();
        return getAnswer(answer);
    }

    private String getAnswer(String answer){
        if("Hello World".equals(answer)){
            return "Successful called producer-helloworld-service";
        }else{
            return "Failed to call producer-helloworld-service";
        }
    }
}
