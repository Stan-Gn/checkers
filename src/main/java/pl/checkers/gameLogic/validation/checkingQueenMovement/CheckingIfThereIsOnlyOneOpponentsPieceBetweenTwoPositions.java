package pl.checkers.gameLogic.validation.checkingQueenMovement;


import pl.checkers.gameLogic.exceptions.InvalidMoveException;
import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.preChecking.CheckingIfPositionIsEmpty;

public class CheckingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositions {
    private Position opponentsPiecePosition = null;

    public boolean getResult(Position from, Position to, BoardGame boardGame, AbstractPlayer currentPlayer) {

        int vectorOfMovingAlongXAxis = to.getX() - from.getX() > 0 ? 1 : -1;
        int vectorOfMovingAlongYAxis = to.getY() - from.getY() > 0 ? 1 : -1;
        int nextPosition = 1;
        Position checkedPosition;
        int numberOfOpponentsPieces = 0;

        checkedPosition = Position.getPosition(from.getX() + (nextPosition * vectorOfMovingAlongXAxis),
                from.getY() + (nextPosition * vectorOfMovingAlongYAxis));

        do {

            if (!CheckingIfPositionIsEmpty.getResult(checkedPosition, boardGame)) {
                if (boardGame.getPiece(checkedPosition).getColor().equals(currentPlayer.getColor()))
                    throw new InvalidMoveException(currentPlayer, "Incorrect movement, on the move line is your piece");
                else {
                    numberOfOpponentsPieces++;
                    if (opponentsPiecePosition == null)
                        opponentsPiecePosition = checkedPosition;
                }
            }
            nextPosition++;
            checkedPosition = Position.getPosition(from.getX() + (nextPosition * vectorOfMovingAlongXAxis),
                    from.getY() + (nextPosition * vectorOfMovingAlongYAxis));

        } while (!(checkedPosition.getX() == to.getX() && checkedPosition.getY() == to.getY()));

        if (numberOfOpponentsPieces == 1)
            return true;
        else if (numberOfOpponentsPieces == 0) {
            return false;
        } else
            throw new InvalidMoveException(currentPlayer, "Incorrect movement, there are more than one opponents pieces on the move line");
    }

    public Position getOpponentsPiecePosition() {
        return opponentsPiecePosition;
    }
}
