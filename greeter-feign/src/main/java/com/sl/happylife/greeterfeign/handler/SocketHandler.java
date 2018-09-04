package com.sl.happylife.greeterfeign.handler;

import com.alibaba.fastjson.JSONObject;
import com.sl.happylife.greetercloud.facade.service.WebSocketFacade;
import com.sl.happylife.greeterfeign.biz.WebSocketBiz;
import com.sl.happylife.greeterfeign.enums.SocketMsgCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author suxin
 */
public class SocketHandler extends TextWebSocketHandler {

    private Logger logger = LoggerFactory.getLogger(SocketHandler.class);

    private WebSocketBiz webSocketBiz;
    private WebSocketFacade webSocketFacade;

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        //获取唯一标示
        String identifies = this.getClientId(webSocketSession);
        logger.info("identifies is {}", identifies);

        //注册
        webSocketBiz.register(identifies, webSocketSession);

        //发送成功结果
        webSocketFacade.sendMessage(identifies, "成功建立连接");
    }

    @Override
    public void handleTextMessage(WebSocketSession webSocketSession, TextMessage textMessage) {

        String payLoad = textMessage.getPayload();
        logger.info("接受的消息 {} ", payLoad);

        JSONObject msgJson = JSONObject.parseObject(payLoad);
        if (ObjectUtils.isEmpty(msgJson)) {
            System.out.println("msgJson 为null");
            return;
        }

        //获取消息
        SocketMsgCode socketMsgCode = SocketMsgCode.getSocketMsgCodeByMsgCode(
                msgJson.getInteger("msg_code"));
        if (ObjectUtils.isEmpty(socketMsgCode)) {
            logger.error("msg_code 不识别", socketMsgCode);
            return;
        }

        switch (socketMsgCode) {
            case INIT:
                break;
            case OTHER:
                break;
            default:
        }
    }


    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable exception) throws Exception {
        if (webSocketSession.isOpen()) {
            webSocketSession.close();
        }

        String identifies = this.getClientId(webSocketSession);
        logger.error(" {} 连接出错", identifies);

        if (!StringUtils.isEmpty(identifies)) {
            webSocketBiz.remove(identifies);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status) {
        logger.info("连接已关闭, status is {}", status);

        String identifies = this.getClientId(webSocketSession);
        if (!StringUtils.isEmpty(identifies)) {
            webSocketBiz.remove(identifies);
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 获取用户标识
     *
     * @param session session
     * @return 用户标志
     */
    private String getClientId(WebSocketSession session) {
        try {
            return (String) session.getAttributes().get(WebSocketBiz.CLIENT_ID);
        } catch (Exception e) {
            return null;
        }
    }

    @Autowired
    public void setWebSocketBiz(WebSocketBiz webSocketBiz) {
        this.webSocketBiz = webSocketBiz;
    }

    @Autowired
    public void setWebSocketFacade(WebSocketFacade webSocketFacade) {
        this.webSocketFacade = webSocketFacade;
    }
}
