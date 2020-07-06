package pl.checkers.gameLogic.validation.Init;


import org.junit.Test;
import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.game.initialization.InitialBoardSettingInterface;
import pl.checkers.gameLogic.validation.preChecking.CheckingIfPositionIsEmpty;
import pl.checkers.gameLogic.validation.Init.InitBoardWithNullToTest;
import pl.checkers.gameLogic.validation.Init.InitWholeBoardWithWhitePiecesToTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class CheckingIfPositionIsEmptyTest {
    private BoardGame boardGame;
    private InitialBoardSettingInterface testInit;


    @Test
    public void falseWhenPositionIsNotEmpty() {
        testInit = new InitWholeBoardWithWhitePiecesToTest();
        boardGame = new BoardGame(testInit);

        boolean result = CheckingIfPositionIsEmpty.getResult(Position.getPosition(1,2),boardGame);

        assertFalse(result);
    }
    @Test
    public void trueWhenPositionIsEmpty() {
        testInit = new InitBoardWithNullToTest();
        boardGame = new BoardGame(testInit);

        boolean result = CheckingIfPositionIsEmpty.getResult(Position.getPosition(5,4),boardGame);

        assertTrue(result);
    }
}