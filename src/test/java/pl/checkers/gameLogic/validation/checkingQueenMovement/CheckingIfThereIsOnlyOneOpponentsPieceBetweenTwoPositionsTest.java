package pl.checkers.gameLogic.validation.checkingQueenMovement;


import org.junit.Before;
import org.junit.Test;
import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.exceptions.InvalidMoveException;
import pl.checkers.gameLogic.piece.Color;
import pl.checkers.gameLogic.piece.Piece;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.Init.InitializationWithIndividualFields;


import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class CheckingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositionsTest {
    private Map<Position, Piece> initFields;
    private InitializationWithIndividualFields boardInit;
    private BoardGame boardGame;
    private AbstractPlayer currentPlayer = mock(AbstractPlayer.class);
    private String whitePiecesPlayer = "Test Player 1";
    private CheckingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositions checkingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositions;


    @Before
    public void setUp() throws Exception {
        initFields = new HashMap<>();
        boardInit = new InitializationWithIndividualFields(initFields);
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        checkingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositions = new CheckingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositions();
    }

    @Test
    public void trueWhenBattleMoveFromPosition1_1ToPosition6_6AndOnPosition4_4OnlyOneOpponentsPiece () {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        initFields.put(Position.getPosition(1,1), Piece.QUEEN_WHITE);
        initFields.put(Position.getPosition(4,4), Piece.QUEEN_BLACK);

        boardGame = new BoardGame(boardInit);

        Position from = Position.getPosition(1,1);
        Position to = Position.getPosition(6,6);

        boolean result = checkingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositions.getResult(from,to,boardGame, currentPlayer);

        assertTrue(result);
    }

    @Test
    public void expectedExceptionWhenMoveFromPosition1_1ToPosition6_6AndOnPosition4_4CurrentPlayerPiece () {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        initFields.put(Position.getPosition(1,1), Piece.QUEEN_WHITE);
        initFields.put(Position.getPosition(4,4), Piece.PAWN_WHITE);

        boardGame = new BoardGame(boardInit);

        Position from = Position.getPosition(1,1);
        Position to = Position.getPosition(6,6);

        String expectedExceptionMessage = new InvalidMoveException(currentPlayer, "Incorrect movement, on the move line is your piece").getMessage();

        try {
            checkingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositions.getResult(from, to, boardGame, currentPlayer);
        }catch (InvalidMoveException e){
            assertThat(e.getMessage()).isEqualTo(expectedExceptionMessage);
        }
    }

    @Test
    public void expectedExceptionWhenMoveFromPosition1_1ToPosition6_6AndOnPosition4_4and5_5OpponentsPieces () {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        initFields.put(Position.getPosition(1,1), Piece.QUEEN_WHITE);
        initFields.put(Position.getPosition(4,4), Piece.PAWN_BLACK);
        initFields.put(Position.getPosition(5,5), Piece.PAWN_BLACK);

        boardGame = new BoardGame(boardInit);

        Position from = Position.getPosition(1,1);
        Position to = Position.getPosition(6,6);

        String expectedExceptionMessage = new InvalidMoveException(currentPlayer, "Incorrect movement, there are more than one opponents pieces on the move line").getMessage();

        try {
            checkingIfThereIsOnlyOneOpponentsPieceBetweenTwoPositions.getResult(from, to, boardGame, currentPlayer);
        }catch (InvalidMoveException e){
            assertThat(e.getMessage()).isEqualTo(expectedExceptionMessage);
        }
    }
    private void settingDataForMockedCurrentPlayer(Color color, String currentPlayerName) {
        when(currentPlayer.getColor()).thenReturn(color);
        when(currentPlayer.getName()).thenReturn(currentPlayerName);
    }

}