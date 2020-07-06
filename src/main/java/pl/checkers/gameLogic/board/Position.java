package pl.checkers.gameLogic.board;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode
public class Position {
    private final int x;
    private final int y;

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position getPosition(int x, int y) {
        return new Position(x,y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
