package pl.checkers.gameLogic.validation;


import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.game.movements.MovementListToMake;
import pl.checkers.gameLogic.game.movements.Movement;
import pl.checkers.gameLogic.piece.Piece;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.checkingQueenMovement.QueenMove;

public class MoveValidation {
    final BoardGame boardGame;

    public MoveValidation(BoardGame boardGame) {
        this.boardGame = boardGame;
    }

    public boolean validate(Position from, Position to, AbstractPlayer currentPlayer, MovementListToMake listOfMovesToMake, Movement move) {
        Piece piece = boardGame.getPiece(from);

        PieceMoveValidationInterface pieceMoveValidation = null;

        switch (piece.getFigure()) {
            case PAWN:
                pieceMoveValidation = new PawnMove();
                break;

            case QUEEN:
                pieceMoveValidation = new QueenMove();
                break;

        }
        return pieceMoveValidation.getResult(from, to, boardGame, currentPlayer, listOfMovesToMake, move);

    }
}
