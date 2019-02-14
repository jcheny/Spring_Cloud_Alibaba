package com.jchen.controller;

import com.jchen.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NacosConsumerFeignController {

    @Autowired
    private EchoService echoService;

    @GetMapping("/echo/hi")
    public String echo(){
        return echoService.echo("Hi feign");
    }

}
