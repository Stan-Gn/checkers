package pl.checkers.gameLogic.validation.preChecking;


import pl.checkers.gameLogic.exceptions.InvalidMoveException;
import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.player.AbstractPlayer;

public class PreChecking {
    private final BoardGame boardGame;

    public PreChecking(BoardGame boardGame) {
        this.boardGame = boardGame;
    }

    public boolean getResult(Position from, Position to, AbstractPlayer currentPlayer) {
        if (!CheckingIfPositionIsOnBoard.getResult(from))
            throw new InvalidMoveException(currentPlayer, "You marked an incorrect pawn start-position");
        if (!CheckingIfPositionIsOnBoard.getResult(to))
            throw new InvalidMoveException(currentPlayer, "You marked an incorrect pawn start-position");
        if (CheckingIfPositionIsEmpty.getResult(from, boardGame))
            throw new InvalidMoveException(currentPlayer, "You have marked the empty start-position");
        if (!CheckingIfPositionIsEmpty.getResult(to, boardGame))
            throw new InvalidMoveException(currentPlayer, "You have marked the not empty end-position");
        if (!CheckingIfPieceInPositionBelongsCurrentPlayer.getResult(currentPlayer, boardGame, from))
            throw new InvalidMoveException(currentPlayer, "You marked not your piece");
        return true;
    }
}
