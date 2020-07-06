package pl.checkers.gameLogic.game.movements;

import pl.checkers.gameLogic.board.Position;

public class Movement {

    private Position from;
    private Position to;
    private Position opponentsPositionInBattleMove;
    private TypeOfMovement typeOfMovement;
    private boolean currentPlayerHasNextBattleMove = false;

    public Movement() {

    }


    public Position getFrom() {
        return from;
    }

    public void setFrom(Position from) {
        this.from = from;
    }

    public Position getTo() {
        return to;
    }

    public void setTo(Position to) {
        this.to = to;
    }

    public Position getOpponentsPositionInBattleMove() {
        return opponentsPositionInBattleMove;
    }

    public void setOpponentsPositionInBattleMove(Position opponentsPositionInBattleMove) {
        this.opponentsPositionInBattleMove = opponentsPositionInBattleMove;
    }

    public TypeOfMovement getTypeOfMovement() {
        return typeOfMovement;
    }

    public void setTypeOfMovement(TypeOfMovement typeOfMovement) {
        this.typeOfMovement = typeOfMovement;
    }

    public boolean isCurrentPlayerHasNextBattleMove() {
        return currentPlayerHasNextBattleMove;
    }

    public void currentPlayerHasNextBattleMove() {
        currentPlayerHasNextBattleMove = true;
    }
}
