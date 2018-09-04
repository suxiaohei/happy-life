package com.sl.happylife.greeterfeign.biz;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author suxin
 */
@Component
public class WebSocketBiz {

    public static final String CLIENT_ID = "identifies";

    /**
     * 在线用户列表
     */
    private static final ConcurrentHashMap<String, WebSocketSession> BIZ_MAP;

    static {
        BIZ_MAP = new ConcurrentHashMap<>();
    }

    /**
     * 注册
     *
     * @param identifies       唯一标示
     * @param webSocketSession webSocketSession
     */
    public void register(String identifies, WebSocketSession webSocketSession) {

        if (!StringUtils.isEmpty(identifies) && !ObjectUtils.isEmpty(webSocketSession)) {
            BIZ_MAP.put(identifies, webSocketSession);
        }

    }

    /**
     * 移除
     *
     * @param identifies 唯一标示
     */
    public void remove(String identifies) {

        if (!StringUtils.isEmpty(identifies)) {
            BIZ_MAP.remove(identifies);
        }

    }

    /**
     * 发送信息给指定用户
     *
     * @param identifies 唯一标识符
     * @param message    消息体
     * @return 发送状态
     */
    public boolean sendMessageToUser(String identifies, String message) {

        WebSocketSession webSocketSession = BIZ_MAP.get(identifies);
        if (ObjectUtils.isEmpty(webSocketSession)) {
            System.out.println("session 为null");
            return false;
        }

        if (!webSocketSession.isOpen()) {
            System.out.println("session 已关闭");
            return false;
        }

        try {
            webSocketSession.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
