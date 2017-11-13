package com.edt.websocket.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DemoWsHandler implements WebSocketHandler {
    private static final List<WebSocketSession> sessionList;//这个会出现性能问题，最好用Map来存储，key用userid
    private static Logger logger = LogManager.getLogger(DemoWsHandler.class);

    static{
        sessionList = new ArrayList<WebSocketSession>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        sessionList.add(session);
        logger.info(sessionList.size());
        System.out.println("connect to the websocket success......");
    }

    @Override
    public void handleMessage(WebSocketSession wss, WebSocketMessage<?> wsm) throws Exception {
        TextMessage returnMessage = new TextMessage(wsm.getPayload()
                + " received at server");
        System.out.println(wss.getHandshakeHeaders().getFirst("Cookie"));
        wss.sendMessage(returnMessage);
    }

    @Override
    public void handleTransportError(WebSocketSession wss, Throwable thrwbl) throws Exception {
        if(wss.isOpen()){
            wss.close();
        }
        System.out.println("websocket connection closed......");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession wss, CloseStatus cs) throws Exception {
        System.out.println("websocket connection closed......");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    /**
     * 给某个用户发送消息
     *
     * @param message
     */
    public void sendMessageToUAll(String  message) {
        for (WebSocketSession session : sessionList) {
            if(session.isOpen()){
                try {
                    session.sendMessage(new TextMessage(message));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
