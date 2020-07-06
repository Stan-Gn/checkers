package pl.checkers.gameLogic.game;

import pl.checkers.gameLogic.piece.Color;
import pl.checkers.gameLogic.piece.Piece;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.player.Player;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Players implements PlayersInterface {
    private final List<AbstractPlayer> players = new ArrayList<>();
    private int auxiliaryNumber = 0;
    private AbstractPlayer currentPlayer;

    public Players() {

    }

    @Override
    public void sendMessageToAllPlayers(String message) {
        for (AbstractPlayer p : players) {
            p.infoForPlayer(message);
        }
    }

    @Override
    public void sendBoardToPlayers(Piece[][] clonedBoard) {
        for (AbstractPlayer p : players) {
            p.sendBoard(clonedBoard);
        }
    }

    public AbstractPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public void initSettings(Piece[][] clonedBoard) {
        initColors();
        sendBoardToPlayers(clonedBoard);
        initOrder();
        initInfo();
    }

    public void setNextPlayerAsCurrentPlayer() {
        currentPlayer.setActivity(false);
        currentPlayer = players.get(getNextIndex());
        currentPlayer.setActivity(true);

    }

    private int getNextIndex() {
        return auxiliaryNumber++ % 2;
    }

    public void initColors() {
        List<Color> colors = new ArrayList<>(Arrays.asList(Color.values()));
        if (players.size() == Color.values().length)
            for (int i = 0; i < players.size(); i++) {
                players.get(i).setColor(colors.get(i));
            }

    }

    public void addPlayer(AbstractPlayer player) {
        synchronized (players) {
            players.add(player);
        }
    }

    public int size() {
        return players.size();
    }

    public AbstractPlayer getFirstPlayer() {
        return players.get(0);
    }

    public void remove(Player player) {
        players.remove(player);
    }

    public List<String> getListOfPlayersUsername() {
        return players.stream().map(player -> player.getName()).collect(Collectors.toList());
    }

    public Player getPlayerOfColor(Color color) {
        return (Player) players.stream().filter(p -> p.getColor().equals(color)).findFirst().get();
    }

    public void sendInfoToPlayersAboutActivity() {
        for (AbstractPlayer p : players) {
            p.infoForPlayer(p.isActive() ? "Your turn" : "Not your turn");
        }
    }

    private void initInfo() {
        for (AbstractPlayer p : players) {
            p.infoForPlayer("Start game " +
                    p.getName() + " - you play with " + p.getColor() + " pieces. " + (p.isActive() ? "Your turn. " : "Not your turn. "));
        }
    }

    private void initOrder() {
        if (players.size() == 2) {
            Random random = new Random();
            auxiliaryNumber = random.nextInt(players.size());
            currentPlayer = players.get(getNextIndex());
            currentPlayer.setActivity(true);
        }
    }
}
