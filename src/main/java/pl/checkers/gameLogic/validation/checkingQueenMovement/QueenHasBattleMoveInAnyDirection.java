package pl.checkers.gameLogic.validation.checkingQueenMovement;


import pl.checkers.gameLogic.exceptions.InvalidMoveException;
import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.OffsetVector;
import pl.checkers.gameLogic.validation.PieceHasBattleMoveInAnyDirectionInterface;
import pl.checkers.gameLogic.validation.preChecking.CheckingIfPositionIsEmpty;
import pl.checkers.gameLogic.validation.preChecking.CheckingIfPositionIsOnBoard;

import java.util.LinkedList;
import java.util.List;

public class QueenHasBattleMoveInAnyDirection implements PieceHasBattleMoveInAnyDirectionInterface {

    @Override
    public boolean getResult(BoardGame boardGame, Position position, AbstractPlayer currentPlayer) {
        CheckingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositions checkingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositions = new CheckingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositions();

        List<OffsetVector> offsetVectors = new LinkedList<>();
        offsetVectors.add(new OffsetVector(1, 1));
        offsetVectors.add(new OffsetVector(-1, 1));
        offsetVectors.add(new OffsetVector(1, -1));
        offsetVectors.add(new OffsetVector(-1, -1));

        for (OffsetVector offsetVector : offsetVectors) {
            int offsetOnAxesByNumberOfPosition = 0;
            Position hypotheticalToPositionTo = getHypotheticalPositionTo(position, offsetVector.getXAxisMotionVector(), offsetVector.getYAxisMotionVector(), offsetOnAxesByNumberOfPosition);

            boolean occurredException = false;

            while (CheckingIfPositionIsOnBoard.getResult(hypotheticalToPositionTo) && !occurredException) {
                try {
                    if (CheckingIfPositionIsEmpty.getResult(hypotheticalToPositionTo, boardGame) &&
                            checkingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositions.getResult(position, hypotheticalToPositionTo, boardGame, currentPlayer))
                        return true;
                } catch (InvalidMoveException e) {
                    occurredException = true;
                }

                offsetOnAxesByNumberOfPosition++;
                hypotheticalToPositionTo = getHypotheticalPositionTo(position, offsetVector.getXAxisMotionVector(), offsetVector.getYAxisMotionVector(), offsetOnAxesByNumberOfPosition);
            }
        }


        return false;
    }

    private static Position getHypotheticalPositionTo(Position from, int xAxisMotionVector, int yAxisMotionVector, int offsetOnAxesByNumberOfPosition) {
        int startOffsetFromPositionFrom = 2;

        return Position.getPosition(from.getX() + ((offsetOnAxesByNumberOfPosition + startOffsetFromPositionFrom) * xAxisMotionVector),
                from.getY() + ((offsetOnAxesByNumberOfPosition + startOffsetFromPositionFrom) * yAxisMotionVector));
    }
}

