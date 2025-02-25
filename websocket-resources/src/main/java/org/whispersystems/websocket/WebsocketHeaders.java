package org.ghostprotocol.websocket;

/**
 * Class containing constants and shared logic for headers used in websocket upgrade requests.
 */
public class WebsocketHeaders {
  public final static String X_GHOST_RECEIVE_STORIES = "X-Ghost-Receive-Stories";
  public static final String X_GHOST_WEBSOCKET_TIMEOUT_HEADER = "X-Ghost-Websocket-Timeout";

  public static boolean parseReceiveStoriesHeader(String s) {
    return "true".equals(s);
  }
}
