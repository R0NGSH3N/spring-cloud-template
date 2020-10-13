package com.r0ngsh3n.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("producer-helloworld-service")
public interface HelloWorldClient {

    @GetMapping("/test/greeting")
    String getGreeting();

}
