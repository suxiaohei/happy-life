package com.sl.happylife.greeterfeign.controller;

import com.sl.happylife.greeterfeign.domain.dto.Greeting;
import com.sl.happylife.greeterfeign.domain.dto.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebSocketController {

    @RequestMapping(path = "/websocket/index", method = RequestMethod.GET)
    public String toWebSocketPage() {

        return "/websocket/index";
    }

    @RequestMapping(path = "/websocket/toUser", method = RequestMethod.GET)
    public String toWebSocketUserPage() {

        return "/websocket/toUser";
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        // simulated delay
        Thread.sleep(1000);
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
//
//    @MessageMapping("/hello")
//    public Greeting hello(HelloMessage message) {
//
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }
//
//    @SendTo("/topic/greetings")
//    public Greeting toMessage() {
//
//        return new Greeting("Hello !");
//    }

}
