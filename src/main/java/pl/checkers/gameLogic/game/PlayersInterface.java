package pl.checkers.gameLogic.game;


import pl.checkers.gameLogic.piece.Piece;
import pl.checkers.gameLogic.player.AbstractPlayer;

public interface PlayersInterface {
    AbstractPlayer getCurrentPlayer();

    void sendMessageToAllPlayers(String message);

    void sendBoardToPlayers(Piece[][] clonedBoard);

    void initSettings(Piece[][] clonedBoard);
}
