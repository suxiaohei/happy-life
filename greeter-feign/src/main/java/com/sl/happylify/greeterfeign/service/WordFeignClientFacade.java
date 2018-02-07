package com.sl.happylify.greeterfeign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("greeter-cloud-app")
public interface WordFeignClientFacade {

    @RequestMapping(value = "/hi" , method = RequestMethod.GET)
    String sayHi(@RequestParam("name") String name);
}
