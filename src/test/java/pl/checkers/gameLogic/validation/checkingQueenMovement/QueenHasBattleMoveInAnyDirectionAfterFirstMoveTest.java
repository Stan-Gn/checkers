package pl.checkers.gameLogic.validation.checkingQueenMovement;


import org.junit.Before;
import org.junit.Test;

import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.game.movements.Movement;
import pl.checkers.gameLogic.game.movements.TypeOfMovement;
import pl.checkers.gameLogic.piece.Color;
import pl.checkers.gameLogic.piece.Piece;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.checkingQueenMovement.QueenHasBattleMoveInAnyDirection;
import pl.checkers.gameLogic.validation.Init.InitializationWithIndividualFields;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QueenHasBattleMoveInAnyDirectionAfterFirstMoveTest {
    private Map<Position, Piece> initFields;
    private InitializationWithIndividualFields boardInit;
    private BoardGame boardGame;
    private AbstractPlayer currentPlayer = mock(AbstractPlayer.class);
    private String whitePiecesPlayer = "Test Player 1";
    private Movement move;
    private QueenHasBattleMoveInAnyDirection queenHasBattleMoveInAnyDirectionAfterFirstMove;

    @Before
    public void setUp() throws Exception {
        this.initFields = new HashMap<>();
        this.boardInit = new InitializationWithIndividualFields(initFields);
        this.move = new Movement();
        this.queenHasBattleMoveInAnyDirectionAfterFirstMove = new QueenHasBattleMoveInAnyDirection();
        move.setTypeOfMovement(TypeOfMovement.REGULAR_MOVE);

    }

    @Test
    public void falseOnlyThePiecesOfTheCurrentPlayerAreInEachDirection() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        initFields.put(Position.getPosition(4, 4), Piece.QUEEN_WHITE);
        initFields.put(Position.getPosition(2, 2), Piece.PAWN_WHITE);
        initFields.put(Position.getPosition(2, 6), Piece.PAWN_WHITE);
        initFields.put(Position.getPosition(6, 6), Piece.PAWN_WHITE);
        initFields.put(Position.getPosition(6, 2), Piece.PAWN_WHITE);
        Position from = Position.getPosition(4,4);
        move.setFrom(from);

        boardGame = new BoardGame(boardInit);

        boolean result = queenHasBattleMoveInAnyDirectionAfterFirstMove.getResult(boardGame, from, currentPlayer);

        assertFalse(result);
    }

    @Test
    public void falseIfInOneDirectionOnlyOpponentsPiecesArePlacedNextToEachOtherAndInOtherDirectionsPiecesOfCurrentPlayer() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        initFields.put(Position.getPosition(4, 4), Piece.QUEEN_WHITE);
        initFields.put(Position.getPosition(2, 2), Piece.PAWN_BLACK);
        initFields.put(Position.getPosition(1, 1), Piece.PAWN_BLACK);
        initFields.put(Position.getPosition(2, 6), Piece.PAWN_WHITE);
        initFields.put(Position.getPosition(6, 6), Piece.PAWN_WHITE);
        initFields.put(Position.getPosition(6, 2), Piece.PAWN_WHITE);
        Position from = Position.getPosition(4,4);
        move.setFrom(from);

        boardGame = new BoardGame(boardInit);

        boolean result = queenHasBattleMoveInAnyDirectionAfterFirstMove.getResult(boardGame, from, currentPlayer);

        assertFalse(result);
    }
    @Test
    public void trueIfInOneDirectionOnlyOpponentsPieceAndInOtherDirectionsPiecesOfCurrentPlayer() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        initFields.put(Position.getPosition(4, 4), Piece.QUEEN_WHITE);
        initFields.put(Position.getPosition(2, 2), Piece.PAWN_BLACK);
        initFields.put(Position.getPosition(2, 6), Piece.PAWN_WHITE);
        initFields.put(Position.getPosition(6, 6), Piece.PAWN_WHITE);
        initFields.put(Position.getPosition(6, 2), Piece.PAWN_WHITE);
        Position from = Position.getPosition(4,4);
        move.setFrom(from);

        boardGame = new BoardGame(boardInit);

        boolean result = queenHasBattleMoveInAnyDirectionAfterFirstMove.getResult(boardGame, from, currentPlayer);

        assertTrue(result);
    }



    private void settingDataForMockedCurrentPlayer(Color color, String currentPlayerName) {
        when(currentPlayer.getColor()).thenReturn(color);
        when(currentPlayer.getName()).thenReturn(currentPlayerName);
    }
}