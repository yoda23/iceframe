package com.edt.websocket.handler;

import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DemoWsHandler implements WebSocketHandler {
    private final  static List<WebSocketSession> sessionList;

    static{
        sessionList = new ArrayList<>();
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("connect to the websocket success......");
        session.sendMessage(new TextMessage("Server:connected OK!"));
        sessionList.add(session);
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


    public void sendText(String text){
        for (WebSocketSession session: sessionList) {
            if(session.isOpen()){
                try {
                    session.sendMessage(new TextMessage(text));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
