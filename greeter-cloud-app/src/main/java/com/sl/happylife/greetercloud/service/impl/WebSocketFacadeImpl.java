package com.sl.happylife.greetercloud.service.impl;

import com.sl.happylife.greetercloud.biz.WebSocketBiz;
import com.sl.happylife.greetercloud.service.WebSocketFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebSocketFacadeImpl implements WebSocketFacade {

    @Autowired
    private WebSocketBiz webSocketBiz;

    @Override
    public boolean sendMessage(String identifies, String message) {

        return webSocketBiz.sendMessageToUser(identifies, message);
    }
}
