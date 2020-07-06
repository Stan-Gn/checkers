package pl.checkers.gameLogic.validation;

import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.piece.Figure;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.checkingPawnMovement.PawnHasRegularMoveInAnyDirection;
import pl.checkers.gameLogic.validation.checkingQueenMovement.QueenHasRegularMoveInAnyDirection;

public class PieceHasRegularMoveInAnyDirection implements PieceHasBattleMoveInAnyDirectionInterface {
    private PieceHasRegularMoveInAnyDirectionInterface pieceHasRegularMoveInAnyDirection;

    @Override
    public boolean getResult(BoardGame boardGame, Position position, AbstractPlayer currentPlayer) {

        Figure figure = boardGame.getPiece(position).getFigure();

        switch (figure) {
            case PAWN:
                pieceHasRegularMoveInAnyDirection = new PawnHasRegularMoveInAnyDirection();
                break;
            case QUEEN:
                pieceHasRegularMoveInAnyDirection = new QueenHasRegularMoveInAnyDirection();
                break;
        }

        return pieceHasRegularMoveInAnyDirection.getResult(boardGame,position,currentPlayer);

    }
}
