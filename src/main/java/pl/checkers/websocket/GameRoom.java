package pl.checkers.websocket;

import pl.checkers.gameLogic.game.Game;

import pl.checkers.gameLogic.game.Players;
import pl.checkers.gameLogic.player.Player;
import java.util.List;


public class GameRoom {
    private final Players players;
    public Status status;
    private Game game;

    enum Status {
        WAITING, GAME
    }

    public GameRoom() {
        status = Status.WAITING;
        players = new Players();
    }

    public void putPlayer(Player player) {
        addPlayerToGameInRoom(player);

        if (isTwoPlayersInRoom()) {
            startGame();
        } else {
            players.sendMessageToAllPlayers("You are a waiting player. Wait for your opponent");
        }


    }

    private void startGame() {
        Thread thread = new Thread(() -> iniGame());
        thread.start();
    }

    private boolean isTwoPlayersInRoom() {
        return players.size() == 2;
    }

    private void addPlayerToGameInRoom(Player player) {
        players.addPlayer(player);
    }

    private void iniGame() {
        changeStatus();
        game = new Game(players);
        game.run();

    }

    public void changeStatus() {
        if (status == Status.GAME)
            status = Status.WAITING;
        else
            status = Status.GAME;

    }


    public String getFirstPlayerName() {
        return players.getFirstPlayer().getName();
    }

    public int numberOfPlayersInRoom() {
        return players.size();
    }

    public void removePlayer(Player player) {
        players.remove(player);
        if (players.size() == 1) {
            players.getFirstPlayer().infoForPlayer("Your opponent has left the game room");
            changeStatus();
        }
    }

    public List<String> getListOfPlayersUsername() {
        return players.getListOfPlayersUsername();
    }
}
