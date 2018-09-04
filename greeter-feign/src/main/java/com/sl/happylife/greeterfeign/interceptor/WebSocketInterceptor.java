package com.sl.happylife.greeterfeign.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

import static com.sl.happylife.greeterfeign.biz.WebSocketBiz.CLIENT_ID;

/**
 * webSocket 拦截器
 *
 * @author suxin
 */
public class WebSocketInterceptor implements HandshakeInterceptor {


    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler,
                                   Map<String, Object> map) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
//            Map parameterMap = serverHttpRequest.getServletRequest().getParameterMap();
            String identifies = serverHttpRequest.getServletRequest().getParameter(CLIENT_ID);
            if (!StringUtils.isEmpty(identifies)) {
                map.put(CLIENT_ID, identifies);
            }

        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                               WebSocketHandler webSocketHandler, Exception e) {

    }
}
