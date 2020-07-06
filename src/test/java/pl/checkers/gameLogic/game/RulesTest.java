package pl.checkers.gameLogic.game;

import org.junit.Before;
import org.junit.Test;
import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.piece.Color;
import pl.checkers.gameLogic.piece.Piece;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.Init.InitializationWithIndividualFields;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.checkers.gameLogic.piece.Piece.PAWN_WHITE;

public class RulesTest {
    private Map<Position, Piece> initFieldsMap;
    private InitializationWithIndividualFields boardInit;
    private BoardGame boardGame;
    private AbstractPlayer currentPlayer = mock(AbstractPlayer.class);
    private Rules rules;
    private Players players = mock(Players.class);


    @Before
    public void setUp() throws Exception {
        this.initFieldsMap = new HashMap<>();
        this.boardInit = new InitializationWithIndividualFields(initFieldsMap);
        when(currentPlayer.getColor()).thenReturn(Color.WHITE);
    }

    @Test
    public void doubleBattleMove() throws CloneNotSupportedException {
        Position positionWhitePawn = Position.getPosition(2, 1);
        Position positionBlackPawn = Position.getPosition(3, 2);
        Position positionSecondBlackPawn = Position.getPosition(5, 4);

        initFieldsMap.put(positionWhitePawn, PAWN_WHITE);
        initFieldsMap.put(positionBlackPawn, Piece.PAWN_BLACK);
        initFieldsMap.put(positionSecondBlackPawn, Piece.PAWN_BLACK);

        boardGame = new BoardGame(boardInit);
        rules = new Rules(boardGame,players);

        Position positionFrom = positionWhitePawn;
        Position positionTo = Position.getPosition(4, 3);


        boolean result = rules.checkMove(positionFrom, positionTo, currentPlayer);

        assertTrue(result);
        assertTrue(rules.nextMoveToCurrentPlayer());

    }


}