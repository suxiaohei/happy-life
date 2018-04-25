package com.sl.happylife.greetercloud.handler;

import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author suxin
 */
public class SocketHandler extends TextWebSocketHandler {

    /**
     * 在线用户列表
     */
    private static final ConcurrentHashMap<String, WebSocketSession> USER_MAP;

    /**
     * 用户标识
     */
    private static final String CLIENT_ID = "uniquelyIdentifies";

    static {
        USER_MAP = new ConcurrentHashMap<>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("成功建立连接");
        String uniquelyIdentifies = getClientId(session);
        if (uniquelyIdentifies != null) {
            USER_MAP.put(uniquelyIdentifies, session);
            session.sendMessage(new TextMessage("成功建立socket连接"));
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {

        WebSocketMessage sendMsg = new TextMessage("server:" + message);
        try {
            session.sendMessage(sendMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送信息给指定用户
     *
     * @param clientId 用户id
     * @param message  消息体
     * @return 发送状态
     */
    public boolean sendMessageToUser(String clientId, String message) {
        if (USER_MAP.get(clientId) == null) {
            return false;
        }
        WebSocketSession session = USER_MAP.get(clientId);
        System.out.println("sendMessage:" + session);
        if (!session.isOpen()) {
            return false;
        }
        try {
            session.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        System.out.println("连接出错");

        String uniquelyIdentifies = this.getClientId(session);
        if (!StringUtils.isEmpty(uniquelyIdentifies)) {
            USER_MAP.remove(uniquelyIdentifies);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.println("连接已关闭：" + status);

        String uniquelyIdentifies = this.getClientId(session);
        if (!StringUtils.isEmpty(uniquelyIdentifies)) {
            USER_MAP.remove(uniquelyIdentifies);
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
            return (String) session.getAttributes().get(CLIENT_ID);
        } catch (Exception e) {
            return null;
        }
    }
}
