package pl.checkers.gameLogic.piece;

public enum VectorYaxis {
    positiveDirectionOfMovementAlongYAxis(1), negativeDirectionOfMovementAlongYAxis(-1);

    final int directionOfMovementAlongYAxis;

    VectorYaxis(int direction) {
        this.directionOfMovementAlongYAxis = direction;
    }

    public int getValue() {
        return directionOfMovementAlongYAxis;
    }
}
