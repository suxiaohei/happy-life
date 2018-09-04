package com.sl.happylife.greeterfeign.configures;

import com.sl.happylife.greeterfeign.handler.SocketHandler;
import com.sl.happylife.greeterfeign.interceptor.WebSocketInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * @author suxin
 */
@Configuration
@EnableWebSocket
public class SimpleWebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(this.socketHandler(), "/socketHandler")
                .addInterceptors(this.getWebSocketInterceptor());
    }

    @Bean
    public WebSocketHandler socketHandler() {
        return new SocketHandler();
    }


    @Bean
    public HandshakeInterceptor getWebSocketInterceptor() {
        return new WebSocketInterceptor();
    }

}
