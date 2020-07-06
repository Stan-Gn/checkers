package pl.checkers.websocket;

import java.util.*;
import java.util.stream.Collectors;

public class GameRoomsHandler {

    private final List<GameRoom> gameRooms = new ArrayList<>();

    private static GameRoomsHandler INSTANCE;

    private GameRoomsHandler() {

    }

    public static GameRoomsHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameRoomsHandler();
        }
        return INSTANCE;
    }

    public void addRoom(GameRoom playersPair) {
        synchronized (gameRooms) {
            gameRooms.add(playersPair);
        }
    }

    public synchronized void removeRoom(GameRoom gameRoom) {
        synchronized (gameRooms) {
            gameRooms.remove(gameRoom);
        }
    }

    public synchronized boolean isUsernameAlreadyExist(String name) {
        synchronized (gameRooms) {
            for (GameRoom gr : gameRooms) {
                List<String> usernameList = gr.getListOfPlayersUsername();
                if (usernameList.contains(name))
                    return true;
            }
        }
        return false;
    }

    public synchronized List<String> getListOfUsernameWaitingPlayers() {
        List<String> listOfUsernameWaitingPlayers;
        synchronized (gameRooms) {
            listOfUsernameWaitingPlayers = gameRooms.stream()
                    .filter(gameRoom -> gameRoom.status.equals(GameRoom.Status.WAITING))
                    .map(gameRoom -> gameRoom.getFirstPlayerName())
                    .collect(Collectors.toList());

        }
        return listOfUsernameWaitingPlayers;
    }

    public GameRoom findRoomByWaitingFirstPlayerUsername(String username) {
        GameRoom gr;
        synchronized (gameRooms) {
            gr = gameRooms.stream()
                    .filter(gameRoom -> gameRoom.status.equals(GameRoom.Status.WAITING))
                    .filter(gameRoom -> gameRoom.getFirstPlayerName().equals(username))
                    .findFirst().get();
        }
        return gr;
    }

    public synchronized List<String> getUsernameListInactivePlayers() {
        List<String>list;
        synchronized (gameRooms) {
            list = gameRooms.stream()
                    .filter(gameRoom -> gameRoom.status.equals(GameRoom.Status.GAME))
                    .flatMap(gameRoom -> gameRoom.getListOfPlayersUsername().stream())
                    .collect(Collectors.toList());
        }

        return list;
    }
}
