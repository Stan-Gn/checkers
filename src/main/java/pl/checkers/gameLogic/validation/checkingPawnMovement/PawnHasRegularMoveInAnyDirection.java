package pl.checkers.gameLogic.validation.checkingPawnMovement;

import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.PieceHasRegularMoveInAnyDirectionInterface;
import pl.checkers.gameLogic.validation.preChecking.CheckingIfPositionIsEmpty;
import pl.checkers.gameLogic.validation.preChecking.CheckingIfPositionIsOnBoard;

import java.util.ArrayList;
import java.util.List;

public class PawnHasRegularMoveInAnyDirection implements PieceHasRegularMoveInAnyDirectionInterface {
    @Override
    public boolean getResult(BoardGame boardGame, Position position, AbstractPlayer currentPlayer) {
        int directionOfMovementAlongYAxis = currentPlayer.getColor().getDirectionOfMovementAlongYAxis().getValue();

        List<Position> positionList = new ArrayList<>();
        Position firstPositiveAlongXAxis = Position.getPosition(position.getX() + 1, position.getY() + (1 * directionOfMovementAlongYAxis));
        Position secondPositiveAlongXAxis = Position.getPosition(position.getX() - 1, position.getY() + (1 * directionOfMovementAlongYAxis));
        positionList.add(firstPositiveAlongXAxis);
        positionList.add(secondPositiveAlongXAxis);

        for (Position p : positionList) {
            if (CheckingIfPositionIsOnBoard.getResult(p) && CheckingIfPositionIsEmpty.getResult(p, boardGame)) {
                return true;
            }
        }
        return false;
    }
}
