package com.sl.happylife.greetercloud.service;

/**
 * @author suxin
 */
public interface WebSocketFacade {

    void register(String uniquelyIdentifies);

    void sendMessage(String uniquelyIdentifies, String message);
}
