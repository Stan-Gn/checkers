package pl.checkers.gameLogic.validation.checkingQueenMovement;


import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;

public class CheckingIfThereIsNoPieceBetweenTwoPositions {
    public static boolean getResult(Position from, Position to, BoardGame boardGame) {

        int vectorOfMovingAlongXAxis = to.getX() - from.getX() > 0 ? 1 : -1;
        int vectorOfMovingAlongYAxis = to.getY() - from.getY() > 0 ? 1 : -1;

        int nextPosition = 0;

        Position checkedPosition;

        do {
            nextPosition++;
            checkedPosition = Position.getPosition(from.getX() + (nextPosition * vectorOfMovingAlongXAxis),
                    from.getY() + (nextPosition * vectorOfMovingAlongYAxis));
            if (boardGame.getPiece(checkedPosition) != null)
                return false;
        } while (!(checkedPosition.getX() == to.getX() && checkedPosition.getY() == to.getY()));

        return true;
    }
}
