package org.ghostprotocol.service.websocket;

import org.eclipse.jetty.websocket.server.WebSocketServerFactory;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.ServletContext;
import javax.websocket.server.ServerEndpointConfig;

public class WebSocketConfigurator extends ServerEndpointConfig.Configurator {
    private final WebSocketConfiguration config;

    public WebSocketConfigurator(WebSocketConfiguration config) {
        this.config = config;
    }

    public void configureWebSocketFactory(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(config.getIdleTimeout());
        factory.getPolicy().setMaxTextMessageSize(config.getMaxTextMessageSize());
        factory.getPolicy().setMaxBinaryMessageSize(config.getMaxBinaryMessageSize());
        factory.setMaxConnections(config.getMaxConnections());
    }

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, 
                              javax.websocket.server.HandshakeRequest request,
                              javax.websocket.HandshakeResponse response) {
        ServletContext context = (ServletContext) request.getHttpSession().getServletContext();
        WebSocketServerFactory factory = (WebSocketServerFactory) context.getAttribute(WebSocketServerFactory.class.getName());
        configureWebSocketFactory(factory);
    }
}
