package pl.checkers.gameLogic.game;


import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.player.AbstractPlayer;

public interface RulesInterface {
    boolean checkMove(Position from, Position to, AbstractPlayer currentPlayer) throws CloneNotSupportedException;
    void makeMove(AbstractPlayer currentPlayer);
    boolean nextMoveToCurrentPlayer();
}
