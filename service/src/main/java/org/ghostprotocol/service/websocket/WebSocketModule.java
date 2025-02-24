package org.ghostprotocol.service.websocket;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.ghostprotocol.service.screenshare.ScreenShareService;

public class WebSocketModule extends AbstractModule {
    @Override
    protected void configure() {
        // Bind any required dependencies
    }

    @Provides
    @Singleton
    public WebSocketConnectionFactory provideWebSocketConnectionFactory(
            ScreenShareService screenShareService,
            WebSocketConfiguration configuration) {
        return new WebSocketConnectionFactory(screenShareService, configuration);
    }

    @Provides
    @Singleton
    public WebSocketConfigurator provideWebSocketConfigurator(WebSocketConfiguration configuration) {
        return new WebSocketConfigurator(configuration);
    }
}
