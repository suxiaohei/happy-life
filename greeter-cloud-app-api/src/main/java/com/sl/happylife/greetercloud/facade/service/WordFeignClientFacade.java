package com.sl.happylife.greetercloud.facade.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * SayHi 对外提供服务的接口
 *
 * @author suxin
 */
@FeignClient("greeter-cloud-app")
public interface WordFeignClientFacade {

    /**
     * Hi
     *
     * @param name 名字
     * @return 结果
     */
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String sayHi(@RequestParam("name") String name);
}
