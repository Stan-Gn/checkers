package pl.checkers.gameLogic.validation;


import pl.checkers.gameLogic.exceptions.InvalidMoveException;
import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.game.movements.MovementListToMake;
import pl.checkers.gameLogic.game.movements.Movement;
import pl.checkers.gameLogic.game.movements.TypeOfMovement;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.checkingPawnMovement.PawnBattleMoveValidation;

public class PawnMove implements PieceMoveValidationInterface {
    private final int numberOfFieldsInRegularMove = 1;


    public PawnMove() {

    }

    public boolean getResult(Position from, Position to, BoardGame boardGame, AbstractPlayer currentPlayer, MovementListToMake listOfMovesToMake, Movement move) {
        int currentPlayerVectorDirectionYAxis = currentPlayer.getColor().getDirectionOfMovementAlongYAxis().getValue();
        int reverseDirectionVector = -1;

        if ((Math.abs(to.getX() - from.getX()) == numberOfFieldsInRegularMove &&
                to.getY() - from.getY() == numberOfFieldsInRegularMove * currentPlayerVectorDirectionYAxis)) {
            move.setFrom(from);
            move.setTo(to);
            move.setTypeOfMovement(TypeOfMovement.REGULAR_MOVE);
            listOfMovesToMake.addMove(move);
            return true;
        }
        if (PawnBattleMoveValidation.getResult(from, to, boardGame, currentPlayer)) {
            move.setFrom(from);
            move.setTo(to);
            move.setTypeOfMovement(TypeOfMovement.BATTLE_MOVE);
            move.setOpponentsPositionInBattleMove(Position.getPosition((from.getX() + to.getX()) / 2, (from.getY() + to.getY()) / 2));
            listOfMovesToMake.addMove(move);
            return true;
        }

        if (to.getY() - from.getY() == currentPlayerVectorDirectionYAxis * reverseDirectionVector) {
            throw new InvalidMoveException(currentPlayer, "Incorrect piece movement direction");
        }

        if (!(Math.abs(to.getX() - from.getX()) == numberOfFieldsInRegularMove &&
                to.getY() - from.getY() == numberOfFieldsInRegularMove * currentPlayerVectorDirectionYAxis)) {
            throw new InvalidMoveException(currentPlayer, "Invalid movement - movement of fields by more than one field.");
        }

        return false;
    }

}
