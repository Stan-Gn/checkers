package pl.checkers.gameLogic.validation;


import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.piece.Figure;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.checkingPawnMovement.PawnHasBattleMoveInAnyDirection;
import pl.checkers.gameLogic.validation.checkingQueenMovement.QueenHasBattleMoveInAnyDirection;

public class PieceHasBattleMoveInAnyDirection implements PieceHasBattleMoveInAnyDirectionInterface {
    private PieceHasBattleMoveInAnyDirectionInterface pieceHasBattleMoveInAnyDirection;

    public PieceHasBattleMoveInAnyDirection() {
    }

    public boolean getResult(BoardGame boardGame, Position position, AbstractPlayer currentPlayer) {

        Figure figure = boardGame.getPiece(position).getFigure();

        switch (figure) {
            case PAWN:
                pieceHasBattleMoveInAnyDirection = new PawnHasBattleMoveInAnyDirection();
                break;
            case QUEEN:
                pieceHasBattleMoveInAnyDirection = new QueenHasBattleMoveInAnyDirection();
                break;
        }

        return pieceHasBattleMoveInAnyDirection.getResult(boardGame,position,currentPlayer);
    }
}
