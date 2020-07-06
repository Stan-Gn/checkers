package pl.checkers.gameLogic.validation.preChecking;


import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;

public class CheckingIfPositionIsOnBoard {
    public static boolean getResult(Position position) {
        int x = position.getX();
        int y = position.getY();
        return coordinateOnBoard(x) && coordinateOnBoard(y);

    }

    private static boolean coordinateOnBoard(int c) {
        return c >= 0 && c < BoardGame.SIZE;
    }
}
