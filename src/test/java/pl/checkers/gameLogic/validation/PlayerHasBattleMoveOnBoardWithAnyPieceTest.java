package pl.checkers.gameLogic.validation;

import org.junit.Before;
import org.junit.Test;
import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.game.initialization.InitialBoardSettingInterface;
import pl.checkers.gameLogic.game.initialization.InitializingCheckers;
import pl.checkers.gameLogic.piece.Color;
import pl.checkers.gameLogic.piece.Piece;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.Init.InitializationWithIndividualFields;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.*;

public class PlayerHasBattleMoveOnBoardWithAnyPieceTest { //
    private Map<Position,Piece> initBordPositionsMap;
    private InitialBoardSettingInterface initBoard;
    private BoardGame boardGame;
    private AbstractPlayer currentPlayer = mock(AbstractPlayer.class);


    @Before
    public void setUp() throws Exception {
        initBordPositionsMap = new HashMap();
        initBoard = new InitializationWithIndividualFields(initBordPositionsMap);
        when(currentPlayer.getColor()).thenReturn(Color.WHITE);
    }

    @Test
    public void ifPlayerHasBattleMoveOnPosition2_1ReturnListWithThisPosition() {

        Position whitePawnPositionWithBattleMove = Position.getPosition(2,1);
        Position blackPawnOpponentPosition = Position.getPosition(3,2);
        initBordPositionsMap.put(whitePawnPositionWithBattleMove, Piece.PAWN_WHITE);
        initBordPositionsMap.put(blackPawnOpponentPosition, Piece.PAWN_BLACK);

        boardGame = new BoardGame(initBoard);

        List<Position> list = PlayerHasBattleMoveOnBoardWithAnyPiece.getResult(boardGame,currentPlayer);

        assertThat(list).containsOnly(whitePawnPositionWithBattleMove);
    }

    @Test
    public void ifPlayerHasBattleMoveOnPosition2_1and4_5ReturnListWithThesePositions() {
        Position whitePawnPositionWithBattleMove = Position.getPosition(2,1);
        Position whitePawnSecondPositionWithBattleMove = Position.getPosition(4,5);
        Position blackPawnOpponentPosition = Position.getPosition(3,2);
        Position blackPawnSecondOpponentPosition = Position.getPosition(5,6);

        initBordPositionsMap.put(whitePawnPositionWithBattleMove, Piece.PAWN_WHITE);
        initBordPositionsMap.put(whitePawnSecondPositionWithBattleMove, Piece.PAWN_WHITE);
        initBordPositionsMap.put(blackPawnOpponentPosition, Piece.PAWN_BLACK);
        initBordPositionsMap.put(blackPawnSecondOpponentPosition, Piece.PAWN_BLACK);

        boardGame = new BoardGame(initBoard);

        List<Position> list = PlayerHasBattleMoveOnBoardWithAnyPiece.getResult(boardGame,currentPlayer);

        assertThat(list).containsOnly(whitePawnPositionWithBattleMove,whitePawnSecondPositionWithBattleMove);
    }

    @Test
    public void ifBoardIsInitializedWithInitialDefaultSettingThenReturnEmptyList() {
        initBoard = new InitializingCheckers();
        boardGame = new BoardGame(initBoard);

        List<Position> list = PlayerHasBattleMoveOnBoardWithAnyPiece.getResult(boardGame,currentPlayer);

        assertTrue(list.isEmpty());
    }





}