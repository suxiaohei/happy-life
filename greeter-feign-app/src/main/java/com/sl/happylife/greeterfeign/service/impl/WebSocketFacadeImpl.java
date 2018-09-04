package com.sl.happylife.greeterfeign.service.impl;

import com.sl.happylife.greetercloud.facade.service.WebSocketFacade;
import com.sl.happylife.greeterfeign.biz.WebSocketBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * webSocket 对外接口实现类
 *
 * @author suxin
 */
@Service
public class WebSocketFacadeImpl implements WebSocketFacade {

    private WebSocketBiz webSocketBiz;

    @Override
    public boolean sendMessage(String identifies, String message) {

        return webSocketBiz.sendMessageToUser(identifies, message);
    }

    @Autowired
    public void setWebSocketBiz(WebSocketBiz webSocketBiz) {
        this.webSocketBiz = webSocketBiz;
    }
}
