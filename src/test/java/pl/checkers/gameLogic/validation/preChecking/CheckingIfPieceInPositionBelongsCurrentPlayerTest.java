package pl.checkers.gameLogic.validation.preChecking;


import org.junit.Before;
import org.junit.Test;
import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.piece.Color;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.preChecking.CheckingIfPieceInPositionBelongsCurrentPlayer;
import pl.checkers.gameLogic.validation.Init.InitWholeBoardWithWhitePiecesToTest;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

public class CheckingIfPieceInPositionBelongsCurrentPlayerTest {
    private BoardGame boardGame;
    private Position position;
    private AbstractPlayer currentPlayer = mock(AbstractPlayer.class);

    @Before
    public void setUp() throws Exception {
        boardGame = new BoardGame(new InitWholeBoardWithWhitePiecesToTest());

    }

    @Test
    public void trueIfPlayerColorIsEqualToColorInPosition() {
        when(currentPlayer.getColor()).thenReturn(Color.WHITE);
        position = Position.getPosition(1,1);

        boolean result = CheckingIfPieceInPositionBelongsCurrentPlayer.getResult(currentPlayer,boardGame,position);

        assertTrue(result);
    }

}