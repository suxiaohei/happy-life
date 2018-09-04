package com.sl.happylife.greetercloud.facade.service;

/**
 * webSocket 接口
 *
 * @author suxin
 */
public interface WebSocketFacade {

    /**
     * 发送消息
     *
     * @param identifies 唯一标示
     * @param message    消息体
     * @return 结果
     */
    boolean sendMessage(String identifies, String message);
}
