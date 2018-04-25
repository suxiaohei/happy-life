package com.sl.happylife.greetercloud.service.impl;

import com.sl.happylife.greetercloud.handler.SocketHandler;
import com.sl.happylife.greetercloud.service.WebSocketFacade;
import org.springframework.beans.factory.annotation.Autowired;

public class WebSocketFacadeImpl implements WebSocketFacade {

    @Autowired
    private SocketHandler socketHandler;

    @Override
    public void register(String uniquelyIdentifies) {

    }

    @Override
    public void sendMessage(String uniquelyIdentifies, String message) {

    }
}
