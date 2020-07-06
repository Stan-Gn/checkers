package pl.checkers.gameLogic.validation;

import org.junit.Before;
import org.junit.Test;
import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.game.initialization.InitialBoardSettingInterface;
import pl.checkers.gameLogic.piece.Color;
import pl.checkers.gameLogic.piece.Piece;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.Init.InitializationWithIndividualFields;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PieceHasBattleMoveInAnyDirectionTest {
    private PieceHasBattleMoveInAnyDirectionInterface pieceHasBattleMoveInAnyDirection = new PieceHasBattleMoveInAnyDirection();
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
    public void resultFalseIfOnBoardIsOnePawn() {
        Position whitePawnPosition = Position.getPosition(2, 1);
        initBordPositionsMap.put(whitePawnPosition, Piece.PAWN_WHITE);

        boardGame = new BoardGame(initBoard);

        assertFalse(pieceHasBattleMoveInAnyDirection.getResult(boardGame, whitePawnPosition, currentPlayer));
    }

    @Test
    public void falseIfOnPosition2_1isWhitePawnAndOnPosition3_2WhitePawn() {
        Position whitePawnPosition = Position.getPosition(2, 1);
        Position whitePawnSecondPosition = Position.getPosition(3, 2);
        initBordPositionsMap.put(whitePawnPosition, Piece.PAWN_WHITE);
        initBordPositionsMap.put(whitePawnSecondPosition, Piece.PAWN_WHITE);

        boardGame = new BoardGame(initBoard);

        assertFalse(pieceHasBattleMoveInAnyDirection.getResult(boardGame, whitePawnPosition, currentPlayer));
    }

    @Test
    public void falseIfOnPosition2_1isWhitePawnAndOnPosition4_3BlackPawn() {
        Position whitePawnPosition = Position.getPosition(2, 1);
        Position blackPawnPosition = Position.getPosition(4, 3);
        initBordPositionsMap.put(whitePawnPosition, Piece.PAWN_WHITE);
        initBordPositionsMap.put(blackPawnPosition, Piece.PAWN_BLACK);

        boardGame = new BoardGame(initBoard);

        assertFalse(pieceHasBattleMoveInAnyDirection.getResult(boardGame, whitePawnPosition, currentPlayer));
    }

    @Test
    public void trueIfOnPosition2_1isWhitePawnAndOnPosition3_2BlackPawn() {
        Position whitePawnPositionWithBattleMove = Position.getPosition(2, 1);
        Position blackPawnPosition = Position.getPosition(3, 2);
        initBordPositionsMap.put(whitePawnPositionWithBattleMove, Piece.PAWN_WHITE);
        initBordPositionsMap.put(blackPawnPosition, Piece.PAWN_BLACK);

        boardGame = new BoardGame(initBoard);

        assertTrue(pieceHasBattleMoveInAnyDirection.getResult(boardGame, whitePawnPositionWithBattleMove, currentPlayer));
    }
    @Test
    public void trueIfOnPosition3_2isWhitePawnAndOnPosition2_1BlackPawn() {
        Position whitePawnPositionWithBattleMove = Position.getPosition(3,2);
        Position blackPawnPosition = Position.getPosition(2,1);
        initBordPositionsMap.put(whitePawnPositionWithBattleMove, Piece.PAWN_WHITE);
        initBordPositionsMap.put(blackPawnPosition, Piece.PAWN_BLACK);

        boardGame = new BoardGame(initBoard);

        assertTrue(pieceHasBattleMoveInAnyDirection.getResult(boardGame, whitePawnPositionWithBattleMove, currentPlayer));
    }

    @Test
    public void resultFalseIfOnBoardIsOneQueen() {
        Position whiteQueenOnPosition = Position.getPosition(2, 1);
        initBordPositionsMap.put(whiteQueenOnPosition, Piece.QUEEN_WHITE);

        boardGame = new BoardGame(initBoard);

        assertFalse(pieceHasBattleMoveInAnyDirection.getResult(boardGame, whiteQueenOnPosition, currentPlayer));
    }

    @Test
    public void falseIfOnPosition3_2isWhiteQueenAndOnPosition2_1WhitePawn() {
        Position whitePawnPosition = Position.getPosition(2, 1);
        Position whiteQueenPosition = Position.getPosition(3, 2);
        initBordPositionsMap.put(whitePawnPosition, Piece.PAWN_WHITE);
        initBordPositionsMap.put(whiteQueenPosition, Piece.QUEEN_WHITE);

        boardGame = new BoardGame(initBoard);

        assertFalse(pieceHasBattleMoveInAnyDirection.getResult(boardGame, whitePawnPosition, currentPlayer));
    }
    @Test
    public void falseIfOnPosition4_3isWhiteQueenAndOnPosition2_1WhitePawn() {
        Position whitePawnPosition = Position.getPosition(2, 1);
        Position whiteQueenPosition = Position.getPosition(4,3);
        initBordPositionsMap.put(whitePawnPosition, Piece.PAWN_WHITE);
        initBordPositionsMap.put(whiteQueenPosition, Piece.QUEEN_WHITE);

        boardGame = new BoardGame(initBoard);

        assertFalse(pieceHasBattleMoveInAnyDirection.getResult(boardGame, whitePawnPosition, currentPlayer));
    }

    @Test
    public void trueIfOnPosition2_1isWhiteQueenAndOnPosition5_4BlackPawn() {
        Position whiteQueenPositionWithBattleMove = Position.getPosition(2, 1);
        Position blackPawnPosition = Position.getPosition(5,4);
        initBordPositionsMap.put(whiteQueenPositionWithBattleMove, Piece.QUEEN_WHITE);
        initBordPositionsMap.put(blackPawnPosition, Piece.PAWN_BLACK);

        boardGame = new BoardGame(initBoard);

        assertTrue(pieceHasBattleMoveInAnyDirection.getResult(boardGame, whiteQueenPositionWithBattleMove, currentPlayer));
    }

}