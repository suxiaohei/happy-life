package com.sl.happylife.greeterfeign.controller;

import com.sl.happylife.greetercloud.facade.service.WordFeignClientFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Cloud Feign 对外消费接口
 *
 * @author suxin
 */
@RestController
public class GreeterFeignController {

    private WordFeignClientFacade wordFeignClientFacade;

    @RequestMapping(value = "/say_hi", method = RequestMethod.GET)
    public String sayHi(@RequestParam String name) {
        return wordFeignClientFacade.sayHi(name);
    }

    @Autowired
    public void setWordFeignClientFacade(WordFeignClientFacade wordFeignClientFacade) {
        this.wordFeignClientFacade = wordFeignClientFacade;
    }
}
