package pl.checkers.websocket;
import pl.checkers.gameLogic.player.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@ServerEndpoint("/actions")
public class CheckersWebsocketServer {
    private final GameRoomsHandler gameRooms = GameRoomsHandler.getInstance();
    private final MessageHandler messageHandler;
    private GameRoom myRoom;
    private Player player;
    private Session session;

    public CheckersWebsocketServer() {
        this.messageHandler = new MessageHandler(this);
    }

    @OnOpen
    public void open(Session session) {
        this.session = session;
    }

    @OnClose
    public void close() {
        if (ifNumberOfPlayersInRoomIsEqualTwo()) {
            removePlayerFromRoom();
        } else {
            removeRoomFromListOfGameRooms();
        }
    }

    private void removeRoomFromListOfGameRooms() {
        gameRooms.removeRoom(myRoom);
    }

    private void removePlayerFromRoom() {
        myRoom.removePlayer(player);
    }

    private boolean ifNumberOfPlayersInRoomIsEqualTwo() {
        return myRoom.numberOfPlayersInRoom() == 2;
    }

    @OnError
    public void onError(Throwable err) {
        removePlayerFromRoom();
        Logger.getLogger(CheckersWebsocketServer.class.getName()).log(Level.SEVERE, null, err);
    }

    @OnMessage
    public void handleMessage(String msg) {
        messageHandler.handle(msg);
    }


    public void setMyRoom(GameRoom myRoom) {
        this.myRoom = myRoom;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Session getSession() {
        return session;
    }
}
