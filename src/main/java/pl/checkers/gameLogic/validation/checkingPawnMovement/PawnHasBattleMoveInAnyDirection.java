package pl.checkers.gameLogic.validation.checkingPawnMovement;

import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.PieceHasBattleMoveInAnyDirectionInterface;
import pl.checkers.gameLogic.validation.preChecking.CheckingIfPositionIsOnBoard;

import java.util.LinkedList;
import java.util.List;


public class PawnHasBattleMoveInAnyDirection implements PieceHasBattleMoveInAnyDirectionInterface {
    @Override
    public boolean getResult(BoardGame boardGame, Position position, AbstractPlayer currentPlayer) {


        List<Position> hypotheticalToPositions = new LinkedList<>();
        hypotheticalToPositions.add(getPositionTo(position, 1, 1));
        hypotheticalToPositions.add(getPositionTo(position, -1, 1));
        hypotheticalToPositions.add(getPositionTo(position, 1, -1));
        hypotheticalToPositions.add(getPositionTo(position, -1, -1));

        for (Position hypotheticalPosition : hypotheticalToPositions) {
            if (CheckingIfPositionIsOnBoard.getResult(hypotheticalPosition) &&
                    PawnBattleMoveValidation.getResult(position, hypotheticalPosition, boardGame, currentPlayer)) {
                return true;
            }
        }

        return false;
    }

    private static Position getPositionTo(Position position, int xAxisMotionVector, int yAxisMotionVector) {
        int numberFieldsOfMove = PawnBattleMoveValidation.NUMBER_OF_FIELDS_IN_BATTLE_MOVE;

        return Position.getPosition(position.getX() + (numberFieldsOfMove * xAxisMotionVector),
                position.getY() + (numberFieldsOfMove * yAxisMotionVector));
    }

}
