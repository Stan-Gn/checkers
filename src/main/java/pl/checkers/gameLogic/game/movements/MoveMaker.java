package pl.checkers.gameLogic.game.movements;

import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.piece.Figure;
import pl.checkers.gameLogic.piece.Piece;

import java.util.Arrays;

public class MoveMaker {
    private final BoardGame boardGame;

    public MoveMaker(BoardGame boardGame) {
        this.boardGame = boardGame;
    }

    public void makeMoveFromMoveList(MovementListToMake moveListToMake) {
        for (Movement move : moveListToMake) {
            makeMove(move);
        }
        moveListToMake.clear();
    }

    public void makeMove(Movement move) {
        TypeOfMovement typeOfMovement = move.getTypeOfMovement();

        if (isRegularMovement(typeOfMovement)) {
            movePieceOnBoard(move);
        } else if (isBattleMovement(typeOfMovement)) {
            movePieceOnBoard(move);
            Position opponentsPosition = move.getOpponentsPositionInBattleMove();
            boardGame.removePiece(opponentsPosition);
        } else if (isRemovalPenal(typeOfMovement)) {
            boardGame.removePiece(move.getFrom());
        } else if (typeOfMovement.equals(TypeOfMovement.EXCHANGE_OF_PAWN_FOR_A_QUEEN)) {
            Piece piece = getQueenPlayersColor(move);
            boardGame.setPieceAtPosition(move.getFrom(),piece);
        }

    }

    private Piece getQueenPlayersColor(Movement move) {
        return Arrays.asList(Piece.values()).stream()
                        .filter(p -> p.getFigure().equals(Figure.QUEEN))
                        .filter(p-> p.getColor().equals(boardGame.getPiece(move.getFrom()).getColor()))
                        .findFirst().get();
    }

    private boolean isRemovalPenal(TypeOfMovement typeOfMovement) {
        return typeOfMovement.equals(TypeOfMovement.REMOVAL_PENAL);
    }

    private void movePieceOnBoard(Movement move) {
        boardGame.movePiece(move.getFrom(), move.getTo());
    }

    private boolean isBattleMovement(TypeOfMovement typeOfMovement) {
        return typeOfMovement.equals(TypeOfMovement.BATTLE_MOVE);
    }

    private boolean isRegularMovement(TypeOfMovement typeOfMovement) {
        return typeOfMovement.equals(TypeOfMovement.REGULAR_MOVE);
    }
}
