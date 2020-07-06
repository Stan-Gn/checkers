package pl.checkers.gameLogic.exceptions;


import pl.checkers.gameLogic.player.AbstractPlayer;

public class InvalidMoveException extends RuntimeException {
    public InvalidMoveException(AbstractPlayer currentPlayer, String msg) {
        super(currentPlayer.getName() + ": " + msg);
    }

}
