package com.sl.happylife.greetercloud.handler;

import com.alibaba.fastjson.JSONObject;
import com.sl.happylife.greetercloud.biz.WebSocketBiz;
import com.sl.happylife.greetercloud.enums.SocketMsgCode;
import com.sl.happylife.greetercloud.service.WebSocketFacade;
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

    @Autowired
    private WebSocketBiz webSocketBiz;

    @Autowired
    private WebSocketFacade webSocketFacade;

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        System.out.println("成功建立连接");

        //获取唯一标示
        String identifies = this.getClientId(webSocketSession);

        //注册
        webSocketBiz.register(identifies, webSocketSession);

        webSocketFacade.sendMessage(identifies, "测试发送");
    }

    @Override
    public void handleTextMessage(WebSocketSession webSocketSession, TextMessage textMessage) {

        String payLoad = textMessage.getPayload();
        JSONObject msgJson = JSONObject.parseObject(payLoad);
        if (ObjectUtils.isEmpty(msgJson)) {
            System.out.println("msgJson 为null");
            return;
        }

        //获取消息
        SocketMsgCode socketMsgCode = SocketMsgCode.getSocketMsgCodeByMsgCode(
                msgJson.getInteger("msg_code"));
        if (ObjectUtils.isEmpty(socketMsgCode)) {
            System.out.println("msg_code 不识别");
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
        System.out.println("连接出错");

        String identifies = this.getClientId(webSocketSession);
        if (!StringUtils.isEmpty(identifies)) {
            webSocketBiz.remove(identifies);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status) {
        System.out.println("连接已关闭：" + status);

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
}
