package pl.checkers.gameLogic.piece;

public enum Color {
    WHITE , BLACK;

    VectorYaxis directionOfMovementAlongYAxis;

    Color() {
    }

    public void setDirectionOfMovementAlongYAxis(VectorYaxis directionOfMovementAlongYAxis) {
        this.directionOfMovementAlongYAxis = directionOfMovementAlongYAxis;
    }

    public VectorYaxis getDirectionOfMovementAlongYAxis() {
        return directionOfMovementAlongYAxis;
    }
}
