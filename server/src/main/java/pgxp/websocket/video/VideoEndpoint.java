/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgxp.websocket.video;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/{room}")
public class VideoEndpoint {

    private final Logger log = Logger.getLogger(getClass().getName());

    @OnOpen
    public void open(final Session session, @PathParam("room") final String room) {
        log.info("session openend and bound to room: " + room);
        session.getUserProperties().put("room", room);
    }

    @OnMessage
    public void onMessage(final Session session, final String message) {
        String room = (String) session.getUserProperties().get("room");
        try {
            for (Session s : session.getOpenSessions()) {
                if (s.isOpen() && room.equals(s.getUserProperties().get("room"))) {
                    s.getBasicRemote().sendObject(message);
                }
            }
        } catch (IOException | EncodeException e) {
            log.log(Level.WARNING, "onMessage failed", e);
        }
    }

    @OnClose
    public void onClose(final Session session, @PathParam("room") final String room) {
        log.info("session closed and unbound to room: " + room);
        session.getUserProperties().remove(room);
    }

    @OnError
    public void onError(Throwable t) {
    }
}
