package pl.checkers.gameLogic.validation.checkingPawnMovement;


import org.junit.Before;
import org.junit.Test;
import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.game.movements.Movement;
import pl.checkers.gameLogic.piece.Color;
import pl.checkers.gameLogic.piece.Piece;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.PieceHasBattleMoveInAnyDirectionInterface;
import pl.checkers.gameLogic.validation.checkingPawnMovement.PawnHasBattleMoveInAnyDirection;
import pl.checkers.gameLogic.validation.Init.InitializationWithIndividualFields;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PawnHasBattleMoveInAnyDirectionAfterFirstMoveTest {
    private Map<Position, Piece> initFields;
    private InitializationWithIndividualFields boardInit;
    private BoardGame boardGame;
    private AbstractPlayer currentPlayer = mock(AbstractPlayer.class);
    private String whitePiecesPlayer = "Test Player 1";
    private Movement move;
    private PieceHasBattleMoveInAnyDirectionInterface pawnHasBattleMoveInAnyDirection = new PawnHasBattleMoveInAnyDirection();



    @Before
    public void setUp() throws Exception {
        this.initFields = new HashMap<>();
        this.boardInit = new InitializationWithIndividualFields(initFields);
        this.move = new Movement();

    }

    //todo sprawdzenie jak pionek jest na pozycj 0 0 żeby nie sprawdzał -1 -1;

    @Test
    public void trueIfWhitePawnHasBattleMoveOnPosition2_2AndBlackPawnOnPosition3_3() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        Position positionWhitePawn = Position.getPosition(2, 2);
        Position positionBlackPawn = Position.getPosition(3,3);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        initFields.put(positionBlackPawn,Piece.PAWN_BLACK);
        boardGame = new BoardGame(boardInit);
        move.setFrom(positionWhitePawn);

        boolean result = pawnHasBattleMoveInAnyDirection.getResult(boardGame,positionWhitePawn,currentPlayer);

        assertTrue(result);

    }
    @Test
    public void trueIfWhitePawnHasBattleMoveOnPosition2_2BlackPawnOnPosition1_3() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        Position positionWhitePawn = Position.getPosition(2, 2);
        Position positionBlackPawn = Position.getPosition(1,3);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        initFields.put(positionBlackPawn,Piece.PAWN_BLACK);
        boardGame = new BoardGame(boardInit);
        move.setFrom(positionWhitePawn);

        boolean result = pawnHasBattleMoveInAnyDirection.getResult(boardGame,positionWhitePawn,currentPlayer);

        assertTrue(result);

    }
    @Test
    public void trueIfWhitePawnHasBattleMoveOnPosition2_2BlackPawnOnPosition1_1() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        Position positionWhitePawn = Position.getPosition(2, 2);
        Position positionBlackPawn = Position.getPosition(1,1);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        initFields.put(positionBlackPawn,Piece.PAWN_BLACK);
        boardGame = new BoardGame(boardInit);
        move.setFrom(positionWhitePawn);

        boolean result = pawnHasBattleMoveInAnyDirection.getResult(boardGame,positionWhitePawn,currentPlayer);

        assertTrue(result);

    }

    @Test
    public void trueIfWhitePawnHasBattleMoveOnPosition2_2BlackPawnOnPosition3_1() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        Position positionWhitePawn = Position.getPosition(2, 2);
        Position positionBlackPawn = Position.getPosition(3,1);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        initFields.put(positionBlackPawn,Piece.PAWN_BLACK);
        boardGame = new BoardGame(boardInit);
        move.setFrom(positionWhitePawn);

        boolean result = pawnHasBattleMoveInAnyDirection.getResult(boardGame,positionWhitePawn,currentPlayer);

        assertTrue(result);

    }

    @Test
    public void falseIfWhitePawnHasBattleMoveOnPosition2_2andPosition3_3IsEmpty() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        Position positionWhitePawn = Position.getPosition(2, 2);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        boardGame = new BoardGame(boardInit);
        move.setFrom(positionWhitePawn);

        boolean result = pawnHasBattleMoveInAnyDirection.getResult(boardGame,positionWhitePawn,currentPlayer);

        assertFalse(result);

    }
    @Test
    public void falseIfWhitePawnHasBattleMoveOnPosition2_2andPosition1_3IsEmpty() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        Position positionWhitePawn = Position.getPosition(2, 2);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        boardGame = new BoardGame(boardInit);
        move.setFrom(positionWhitePawn);

        boolean result = pawnHasBattleMoveInAnyDirection.getResult(boardGame,positionWhitePawn,currentPlayer);

        assertFalse(result);

    }
    @Test
    public void falseIfWhitePawnHasBattleMoveOnPosition2_2andPosition1_1IsEmpty() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        Position positionWhitePawn = Position.getPosition(2, 2);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        boardGame = new BoardGame(boardInit);
        move.setFrom(positionWhitePawn);

        boolean result = pawnHasBattleMoveInAnyDirection.getResult(boardGame,positionWhitePawn,currentPlayer);

        assertFalse(result);

    }
    @Test
    public void falseIfWhitePawnHasBattleMoveOnPosition2_2andPosition3_1IsEmpty() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        Position positionWhitePawn = Position.getPosition(2, 2);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        boardGame = new BoardGame(boardInit);
        move.setFrom(positionWhitePawn);

        boolean result = pawnHasBattleMoveInAnyDirection.getResult(boardGame,positionWhitePawn,currentPlayer);

        assertFalse(result);

    }
    @Test
    public void falseIfWhitePawnHasBattleMoveOnPosition2_2AndWhitePawnOnPosition3_3() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        Position positionWhitePawn = Position.getPosition(2, 2);
        Position positionSecondWhitePawn = Position.getPosition(3,3);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        initFields.put(positionSecondWhitePawn,Piece.PAWN_WHITE);
        boardGame = new BoardGame(boardInit);
        move.setFrom(positionWhitePawn);

        boolean result = pawnHasBattleMoveInAnyDirection.getResult(boardGame,positionWhitePawn,currentPlayer);

        assertFalse(result);

    }
    @Test
    public void falseIfWhitePawnHasBattleMoveOnPosition2_2AndWhitePawnOnPosition1_3() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        Position positionWhitePawn = Position.getPosition(2, 2);
        Position positionSecondWhitePawn = Position.getPosition(1,3);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        initFields.put(positionSecondWhitePawn,Piece.PAWN_WHITE);
        boardGame = new BoardGame(boardInit);
        move.setFrom(positionWhitePawn);

        boolean result = pawnHasBattleMoveInAnyDirection.getResult(boardGame,positionWhitePawn,currentPlayer);

        assertFalse(result);

    }
    @Test
    public void falseIfWhitePawnHasBattleMoveOnPosition2_2AndWhitePawnOnPosition1_1() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        Position positionWhitePawn = Position.getPosition(2, 2);
        Position secondPositionWhitePawn = Position.getPosition(1,1);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        initFields.put(secondPositionWhitePawn,Piece.PAWN_WHITE);
        boardGame = new BoardGame(boardInit);
        move.setFrom(positionWhitePawn);

        boolean result = pawnHasBattleMoveInAnyDirection.getResult(boardGame,positionWhitePawn,currentPlayer);

        assertFalse(result);

    }

    @Test
    public void trueIfWhitePawnHasBattleMoveOnPosition2_2AndWhitePawnOnPosition3_1() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        Position positionWhitePawn = Position.getPosition(2, 2);
        Position positionSecondWhitePawn = Position.getPosition(3,1);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        initFields.put(positionSecondWhitePawn,Piece.PAWN_WHITE);
        boardGame = new BoardGame(boardInit);
        move.setFrom(positionWhitePawn);

        boolean result = pawnHasBattleMoveInAnyDirection.getResult(boardGame,positionWhitePawn,currentPlayer);

        assertFalse(result);

    }



    private void settingDataForMockedCurrentPlayer(Color color, String currentPlayerName) {
        when(currentPlayer.getColor()).thenReturn(color);
        when(currentPlayer.getName()).thenReturn(currentPlayerName);
    }

}