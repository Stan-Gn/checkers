package pl.checkers.websocket;

import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.player.Player;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;

public class MessageHandler {
    GameRoomsHandler gameRooms;
    private final CheckersWebsocketServer checkersWebsocketServer;

    public MessageHandler(CheckersWebsocketServer checkersWebsocketServer) {
        this.checkersWebsocketServer = checkersWebsocketServer;
    }

    public void handle(String message) {
        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();
            String action = getActionFromJsonObject(jsonMessage);

            switch (action) {
                case "addUsername":
                    addUser(jsonMessage);
                    break;

                case "addToRoom":
                    addUserToRoom(jsonMessage);
                    break;

                case "move":
                    setPlayerPositionsToMakeMove(jsonMessage);
                    break;
            }

        }
    }

    private void addUser(JsonObject jsonMessage) {
        gameRooms = GameRoomsHandler.getInstance();
        String username =  jsonMessage.getString("username");
        createNewRoomWithUser(username);
    }

    private void addUserToRoom(JsonObject jsonMessage) {
        gameRooms = GameRoomsHandler.getInstance();
        String username = jsonMessage.getString("username");
        String partnerUsername = jsonMessage.getString("partnerUsername");

        Player player = new Player(username, checkersWebsocketServer.getSession());
        GameRoom myRoom = gameRooms.findRoomByWaitingFirstPlayerUsername(partnerUsername);

        checkersWebsocketServer.setPlayer(player);
        checkersWebsocketServer.setMyRoom(myRoom);

        if (isMyRoomExist(myRoom)) {
            myRoom.putPlayer(player);
        }else {
            createNewRoomWithUser(username);
            player.infoForPlayer("No selected player in the database, apparently left the room");
        }
    }

    private boolean isMyRoomExist(GameRoom myRoom) {
        return myRoom != null;
    }

    private void setPlayerPositionsToMakeMove(JsonObject jsonMessage) {
        Position positionFrom = getPositionFromMessage("positionFrom", jsonMessage);
        Position positionTo = getPositionFromMessage("positionTo", jsonMessage);

        checkersWebsocketServer.getPlayer().setPositionFrom(positionFrom);
        checkersWebsocketServer.getPlayer().setPositionTo(positionTo);
    }

    private String getActionFromJsonObject(JsonObject jsonMessage) {
        return jsonMessage.getString("action");
    }

    private Position getPositionFromMessage(String position, JsonObject jsonMessage) {
        String positionString = jsonMessage.getString(position);
        String[] positionCoordinates = positionString.split("");
        return Position.getPosition(
                Integer.parseInt(positionCoordinates[1]),
                Integer.parseInt(positionCoordinates[0])
        );
    }

    private void createNewRoomWithUser(String username) {
        Player player = new Player(username, checkersWebsocketServer.getSession());
        GameRoom myRoom = new GameRoom();
        myRoom.putPlayer(player);
        checkersWebsocketServer.setPlayer(player);
        checkersWebsocketServer.setMyRoom(myRoom);
        gameRooms.addRoom(myRoom);
    }
}
