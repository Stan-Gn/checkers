package pl.checkers.gameLogic.validation.Init;



import org.junit.Test;
import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.validation.preChecking.CheckingIfPositionIsOnBoard;

import static org.junit.Assert.*;

public class CheckingIfPositionIsOnBoardTest {
    private int boardMaxNumber = BoardGame.SIZE;
    private int boardMinNumber = 0;


    @Test
    public void trueIfPositionIsOnBoard() {
        int positionXonBoard = boardMaxNumber - 1;
        int positionYonBoard = boardMinNumber + 1;
        Position positionOnBoard = Position.getPosition(positionXonBoard, positionYonBoard);

        boolean result = CheckingIfPositionIsOnBoard.getResult(positionOnBoard);

        assertTrue(result);
    }

    @Test
    public void falseIfPositionIsNotOnBoard() {
        int positionXonBoard = boardMaxNumber + 5;
        int positionYonBoard = boardMinNumber + 1;
        Position positionOnBoard = Position.getPosition(positionXonBoard, positionYonBoard);

        boolean result = CheckingIfPositionIsOnBoard.getResult(positionOnBoard);

        assertFalse(result);
    }
}