package com.r0ngsh3n.springcloud.service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
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
    @CircuitBreaker(name="producer-helloworld-service", fallbackMethod = "fallback")
    @Bulkhead(name = "producer-helloworld-service", type = Bulkhead.Type.SEMAPHORE, fallbackMethod = "fallbackBulkhead")
    @RateLimiter(name = "producer-helloworld-service", fallbackMethod = "fallbackForRatelimit")
    @Retry(name = "get", fallbackMethod = "fallbackRetry")
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

    private String fallbackBulkhead(){
        return null;
    }

    private String fallback(){
        return getAnswer("");
    }
}
