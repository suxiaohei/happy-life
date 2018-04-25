package com.sl.happylife.greetercloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author suxin
 */
@Controller
public class AppController {

    @RequestMapping(path = "/security/index", method = RequestMethod.GET)
    public String toSecurityPage() {

        return "/security/index";
    }

    @RequestMapping(path = "/websocket/index", method = RequestMethod.GET)
    public String toWebSocketPage() {

        return "/websocket/index";
    }

}
