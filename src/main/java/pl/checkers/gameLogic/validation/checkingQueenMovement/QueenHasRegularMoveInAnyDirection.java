package pl.checkers.gameLogic.validation.checkingQueenMovement;

import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.OffsetVector;
import pl.checkers.gameLogic.validation.PieceHasRegularMoveInAnyDirectionInterface;
import pl.checkers.gameLogic.validation.preChecking.CheckingIfPositionIsEmpty;
import pl.checkers.gameLogic.validation.preChecking.CheckingIfPositionIsOnBoard;

import java.util.LinkedList;
import java.util.List;

public class QueenHasRegularMoveInAnyDirection implements PieceHasRegularMoveInAnyDirectionInterface {
    @Override
    public boolean getResult(BoardGame boardGame, Position position, AbstractPlayer currentPlayer) {
        List<OffsetVector> offsetVectors = new LinkedList<>();
        offsetVectors.add(new OffsetVector(1, 1));
        offsetVectors.add(new OffsetVector(-1, 1));
        offsetVectors.add(new OffsetVector(1, -1));
        offsetVectors.add(new OffsetVector(-1, -1));

        for (OffsetVector offsetVector : offsetVectors) {
            int offsetOnAxesByNumberOfPosition = 1;
            Position hypotheticalToPositionTo = getHypotheticalPositionTo(position, offsetVector.getXAxisMotionVector(), offsetVector.getYAxisMotionVector(), offsetOnAxesByNumberOfPosition);
            if (CheckingIfPositionIsOnBoard.getResult(hypotheticalToPositionTo) && CheckingIfPositionIsEmpty.getResult(hypotheticalToPositionTo, boardGame)) {
                return true;
            }
        }

        return false;
    }

    private static Position getHypotheticalPositionTo(Position from, int xAxisMotionVector, int yAxisMotionVector, int offsetOnAxesByNumberOfPosition) {

        return Position.getPosition(from.getX() + (offsetOnAxesByNumberOfPosition * xAxisMotionVector),
                from.getY() + (offsetOnAxesByNumberOfPosition * yAxisMotionVector));
    }
}
