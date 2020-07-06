package pl.checkers.gameLogic.validation.checkingQueenMovement;

import pl.checkers.gameLogic.exceptions.InvalidMoveException;
import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.game.movements.MovementListToMake;
import pl.checkers.gameLogic.game.movements.Movement;
import static pl.checkers.gameLogic.game.movements.TypeOfMovement.*;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.PieceMoveValidationInterface;

public class QueenMove implements PieceMoveValidationInterface {
    public boolean getResult(Position from, Position to, BoardGame boardGame, AbstractPlayer currentPlayer, MovementListToMake listOfMovesToMake, Movement move) {
        CheckingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositions checkingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositions = new CheckingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositions();

        if (isQueenMovingDiagonally(from, to)) {
            if (CheckingIfThereIsNoPieceBetweenTwoPositions.getResult(from, to, boardGame)) {
                setRegularMoveToMovesToMake(from, to, listOfMovesToMake, move);
                return true;
            }

            if (checkingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositions.getResult(from, to, boardGame, currentPlayer)) {
                setBattleMoveToMovesToMake(from, to, listOfMovesToMake, move, checkingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositions);
                return true;
            }
        } else throw new InvalidMoveException(currentPlayer, "Illegal Queen move");

        return false;
    }

    private void setBattleMoveToMovesToMake(Position from, Position to, MovementListToMake listOfMovesToMake, Movement move, CheckingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositions checkingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositions) {
        Position opponentsPiecePosition = checkingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositions.getOpponentsPiecePosition();
        move.setFrom(from);
        move.setTo(to);
        move.setTypeOfMovement(BATTLE_MOVE);
        move.setOpponentsPositionInBattleMove(opponentsPiecePosition);
        listOfMovesToMake.addMove(move);
    }

    private void setRegularMoveToMovesToMake(Position from, Position to, MovementListToMake listOfMovesToMake, Movement move) {
        move.setFrom(from);
        move.setTo(to);
        move.setTypeOfMovement(REGULAR_MOVE);
        listOfMovesToMake.addMove(move);
    }

    private boolean isQueenMovingDiagonally(Position from, Position to) {
        return Math.abs(to.getY() - from.getY()) == Math.abs(to.getX() - from.getX());
    }

}
