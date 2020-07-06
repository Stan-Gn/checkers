package pl.checkers.gameLogic.player;

import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.piece.Color;
import pl.checkers.gameLogic.piece.Piece;

public abstract class AbstractPlayer {
    private Color color;
    private String name;
    protected boolean activity = false;

    public AbstractPlayer(String name) {
        this.name = name;
    }

    public abstract Position getPositionFrom() ;

    public abstract Position getPositionTo();
    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isActive() {
        return activity;
    }

    public abstract void setActivity(boolean activity) ;

    public abstract void infoForPlayer(String message);

    public abstract void sendBoard(Piece[][] clonedBoard);

}
