package com.jchen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class NacosConsumerController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String name;

    @RequestMapping("/echo/app/name")
    public String echo(){
        //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
        ServiceInstance choose = loadBalancerClient.choose("nacos-provider");
        String url = String.format("http://%s:%s/echo/%s", choose.getHost(), choose.getPort(), name);
        System.out.println("url = " + url);
        return restTemplate.getForObject(url,String.class);
    }

}
