package pl.checkers.gameLogic.validation.checkingQueenMovement;


import org.junit.Before;
import org.junit.Test;
import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.piece.Piece;
import pl.checkers.gameLogic.validation.Init.InitializationWithIndividualFields;


import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CheckingIfThereIsNoPieceBetweenTwoPositionsTest {
    private Map<Position,Piece> initBordPositions;
    private InitializationWithIndividualFields initBoard;
    private BoardGame boardGame;

    @Before
    public void setUp() throws Exception {
        initBordPositions = new HashMap();
        initBoard = new InitializationWithIndividualFields(initBordPositions);
    }

    @Test
    public void trueIfThereIsNoPieceBetweenPosition2_2andPosition6_6() {
        Position from = Position.getPosition(2,2);
        Position to = Position.getPosition(6,6);
        boardGame = new BoardGame(initBoard);

        boolean result = CheckingIfThereIsNoPieceBetweenTwoPositions.getResult(from,to,boardGame);

        assertTrue(result);
    }
    @Test
    public void trueIfThereIsNoPieceBetweenPosition6_6andPosition2_2() {
        Position from = Position.getPosition(6,6);
        Position to = Position.getPosition(2,2);
        boardGame = new BoardGame(initBoard);

        boolean result = CheckingIfThereIsNoPieceBetweenTwoPositions.getResult(from,to,boardGame);

        assertTrue(result);
    }

    @Test
    public void trueIfThereIsNoPieceBetweenPosition7_0andPosition5_2() {
        Position from = Position.getPosition(7,0);
        Position to = Position.getPosition(5,2);
        boardGame = new BoardGame(initBoard);

        boolean result = CheckingIfThereIsNoPieceBetweenTwoPositions.getResult(from,to,boardGame);

        assertTrue(result);
    }

    @Test
    public void trueIfThereIsNoPieceBetweenPosition5_2andPosition7_0() {
        Position from = Position.getPosition(5,2);
        Position to = Position.getPosition(7,0);
        boardGame = new BoardGame(initBoard);

        boolean result = CheckingIfThereIsNoPieceBetweenTwoPositions.getResult(from,to,boardGame);

        assertTrue(result);
    }

    @Test
    public void falseIfThereIsPieceBetweenPosition2_2andPosition6_6() {
        Position from = Position.getPosition(2,2);
        Position to = Position.getPosition(6,6);
        initBordPositions.put(Position.getPosition(4,4), Piece.PAWN_BLACK);
        boardGame = new BoardGame(initBoard);

        boolean result = CheckingIfThereIsNoPieceBetweenTwoPositions.getResult(from,to,boardGame);

        assertFalse(result);
    }
    @Test
    public void falseIfThereIsPieceBetweenPosition6_6andPosition2_2() {
        Position from = Position.getPosition(6,6);
        Position to = Position.getPosition(2,2);
        initBordPositions.put(Position.getPosition(4,4),Piece.PAWN_BLACK);
        boardGame = new BoardGame(initBoard);

        boolean result = CheckingIfThereIsNoPieceBetweenTwoPositions.getResult(from,to,boardGame);

        assertFalse(result);
    }

    @Test
    public void falseIfThereIsPieceBetweenPosition7_0andPosition3_4() {
        Position from = Position.getPosition(7,0);
        Position to = Position.getPosition(3,4);
        initBordPositions.put(Position.getPosition(5,2),Piece.PAWN_BLACK);
        boardGame = new BoardGame(initBoard);

        boolean result = CheckingIfThereIsNoPieceBetweenTwoPositions.getResult(from,to,boardGame);

        assertFalse(result);
    }

    @Test
    public void falseIfThereIsPieceBetweenPosition3_4andPosition7_0() {
        Position from = Position.getPosition(3,4);
        Position to = Position.getPosition(7,0);
        initBordPositions.put(Position.getPosition(5,2),Piece.PAWN_BLACK);
        boardGame = new BoardGame(initBoard);

        boolean result = CheckingIfThereIsNoPieceBetweenTwoPositions.getResult(from,to,boardGame);

        assertFalse(result);
    }




}