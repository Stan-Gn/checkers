package pl.checkers.gameLogic.validation.checkingPawnMovement;


import org.junit.Before;
import org.junit.Test;

import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.exceptions.InvalidMoveException;
import pl.checkers.gameLogic.game.movements.MovementListToMake;
import pl.checkers.gameLogic.game.movements.Movement;
import pl.checkers.gameLogic.piece.Color;
import pl.checkers.gameLogic.piece.Piece;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.Init.InitializationWithIndividualFields;
import pl.checkers.gameLogic.validation.PawnMove;

import java.util.HashMap;
import java.util.Map;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class PawnMoveTest {
    private Map<Position, Piece> initFields;
    private InitializationWithIndividualFields boardInit;
    private PawnMove pawnMove = new PawnMove();
    private BoardGame boardGame;
    private AbstractPlayer currentPlayer = mock(AbstractPlayer.class);
    private String whitePiecesPlayer = "Test Player 1";
    private String blackPiecesPlayer = "Test Player 2";
    private Movement move;
    private MovementListToMake listOfMovesToMake = new MovementListToMake();

    @Before
    public void setUp() throws Exception {
       this.initFields = new HashMap<>();
       this.boardInit = new InitializationWithIndividualFields(initFields);
       this.move = new Movement();
    }

    @Test
    public void trueIfPawnMovesOnePositionInAccordanceWithDirectionOfWhiteMovementAlongYAxisAndAccordingToXAxis() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        Position positionWhitePawn = Position.getPosition(2, 2);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        boardGame = new BoardGame(boardInit);

        Position from = Position.getPosition(2, 2);
        Position to = Position.getPosition(3, 3);

        boolean result = pawnMove.getResult(from, to, boardGame, currentPlayer,listOfMovesToMake,move);

        assertTrue(result);
    }

    @Test
    public void trueIfPawnMovesOnePositionInAccordanceWithDirectionOfWhiteMovementAlongYAxisAndNotAccordingToXAxis() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        Position positionWhitePawn = Position.getPosition(2, 2);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        boardGame = new BoardGame(boardInit);

        Position from = Position.getPosition(2, 2);
        Position to = Position.getPosition(1, 3);

        boolean result = pawnMove.getResult(from, to, boardGame, currentPlayer,listOfMovesToMake,move);

        assertTrue(result);
    }


    @Test
    public void exceptionIfPawnMovesMoreThanOnePositionInDirectionOfMovementOfWhiteAlongYAxisAndAccordingToXAxis() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        Position positionWhitePawn = Position.getPosition(2, 2);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        boardGame = new BoardGame(boardInit);

        Position from = Position.getPosition(2, 2);
        Position to = Position.getPosition(4, 4);

        try {
            pawnMove.getResult(from, to, boardGame, currentPlayer,listOfMovesToMake,move);
        } catch (InvalidMoveException e) {
            Exception resultOccurredException = new InvalidMoveException(currentPlayer, "Invalid movement - movement of fields by more than one field.");
            assertThat(e.getMessage()).isEqualTo(resultOccurredException.getMessage());
        }

    }

    @Test
    public void exceptionIfPawnMovesMoreThanOnePositionInDirectionOfMovementOfWhiteAlongYAxisAndNotAccordingToXAxis() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        Position positionWhitePawn = Position.getPosition(2, 2);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        boardGame = new BoardGame(boardInit);

        Position from = Position.getPosition(2, 2);
        Position to = Position.getPosition(0, 4);

        try {
            pawnMove.getResult(from, to, boardGame, currentPlayer,listOfMovesToMake,move);
        } catch (InvalidMoveException e) {
            Exception resultOccurredException = new InvalidMoveException(currentPlayer, "Invalid movement - movement of fields by more than one field.");
            assertThat(e.getMessage()).isEqualTo(resultOccurredException.getMessage());
        }

    }

    @Test
    public void exceptionIfPawnMovesInOppositeDirectionToColorDirectionOnYAxisAndAccordingToXAxis() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        Position positionWhitePawn = Position.getPosition(2, 2);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        boardGame = new BoardGame(boardInit);

        Position from = Position.getPosition(2, 2);
        Position to = Position.getPosition(3, 1);

        try {
            pawnMove.getResult(from, to, boardGame, currentPlayer,listOfMovesToMake,move);
        } catch (InvalidMoveException e) {
            Exception resultOccurredException = new InvalidMoveException(currentPlayer, "Incorrect piece movement direction");
            assertThat(e.getMessage()).isEqualTo(resultOccurredException.getMessage());
        }
    }

    @Test
    public void exceptionIfPawnMovesInOppositeDirectionToColorDirectionOnYAxisAndNotAccordingToXAxis() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        Position positionWhitePawn = Position.getPosition(2, 2);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        boardGame = new BoardGame(boardInit);

        Position from = Position.getPosition(2, 2);
        Position to = Position.getPosition(1, 1);

        try {
            pawnMove.getResult(from, to, boardGame, currentPlayer,listOfMovesToMake,move);
        } catch (InvalidMoveException e) {
            Exception resultOccurredException = new InvalidMoveException(currentPlayer, "Incorrect piece movement direction");
            assertThat(e.getMessage()).isEqualTo(resultOccurredException.getMessage());
        }
    }


    @Test
    public void trueIfBattleMoveFromPosition4_4ToPosition6_6AndAtPosition5_5OpponentsPawn() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        Position positionWhitePawn = Position.getPosition(4, 4);
        Position positionBlackPawn = Position.getPosition(5, 5);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        initFields.put(positionBlackPawn, Piece.PAWN_BLACK);
        boardGame = new BoardGame(boardInit);

        Position from = Position.getPosition(4, 4);
        Position to = Position.getPosition(6, 6);


        boolean result = pawnMove.getResult(from, to, boardGame, currentPlayer,listOfMovesToMake,move);

        assertTrue(result);

    }

    @Test
    public void trueIfBattleMoveFromPosition4_4ToPosition2_6AndAtPosition3_5OpponentsPawn() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        Position positionWhitePawn = Position.getPosition(4, 4);
        Position positionBlackPawn = Position.getPosition(3, 5);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        initFields.put(positionBlackPawn, Piece.PAWN_BLACK);
        boardGame = new BoardGame(boardInit);

        Position from = Position.getPosition(4, 4);
        Position to = Position.getPosition(2, 6);


        boolean result = pawnMove.getResult(from, to, boardGame, currentPlayer,listOfMovesToMake,move);

        assertTrue(result);
    }


    private void settingDataForMockedCurrentPlayer(Color color, String currentPlayerName) {
        when(currentPlayer.getColor()).thenReturn(color);
        when(currentPlayer.getName()).thenReturn(currentPlayerName);
    }

}