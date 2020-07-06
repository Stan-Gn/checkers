package pl.checkers.gameLogic.validation.checkingQueenMovement;


import org.junit.Before;
import org.junit.Test;

import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.game.movements.MovementListToMake;
import pl.checkers.gameLogic.game.movements.Movement;
import pl.checkers.gameLogic.piece.Color;
import pl.checkers.gameLogic.piece.Piece;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.checkingQueenMovement.QueenMove;
import pl.checkers.gameLogic.validation.Init.InitializationWithIndividualFields;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QueenMoveTest {
    private Map<Position, Piece> initFields;
    private InitializationWithIndividualFields boardInit;
    private QueenMove queenMove = new QueenMove();
    private BoardGame boardGame;
    private AbstractPlayer currentPlayer = mock(AbstractPlayer.class);
    private String whitePiecesPlayer = "Test Player 1";
    private String blackPiecesPlayer = "Test Player 2";
    private Movement move ;
    private MovementListToMake listOfMovesToMake = new MovementListToMake();
    @Before
    public void setUp() throws Exception {
        this.initFields = new HashMap<>();
        this.boardInit = new InitializationWithIndividualFields(initFields);
        this.move = new Movement();

    }

    @Test
    public void trueIfRegularMoveFromPosition4_4toPosition7_7() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        initFields.put(Position.getPosition(4,4), Piece.QUEEN_WHITE);
        boardGame = new BoardGame(boardInit);

        Position from = Position.getPosition(4,4);
        Position to = Position.getPosition(7, 7);

        boolean result = queenMove.getResult(from,to,boardGame,currentPlayer,listOfMovesToMake,move);

        assertTrue(result);

    }

    @Test
    public void trueIfRegularMoveFromPosition7_7toPosition4_4() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        initFields.put(Position.getPosition(7,7), Piece.QUEEN_WHITE);
        boardGame = new BoardGame(boardInit);

        Position from = Position.getPosition(7,7);
        Position to = Position.getPosition(4,4);

        boolean result = queenMove.getResult(from,to,boardGame,currentPlayer,listOfMovesToMake,move);

        assertTrue(result);

    }

    @Test
    public void trueIfRegularMoveFromPosition4_4toPosition1_7() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        initFields.put(Position.getPosition(4,4), Piece.QUEEN_WHITE);
        boardGame = new BoardGame(boardInit);

        Position from = Position.getPosition(4,4);
        Position to = Position.getPosition(1,7);

        boolean result = queenMove.getResult(from,to,boardGame,currentPlayer,listOfMovesToMake,move);

        assertTrue(result);

    }

    @Test
    public void trueIfRegularMoveFromPosition1_7toPosition4_4() {
        settingDataForMockedCurrentPlayer(Color.WHITE, whitePiecesPlayer);
        initFields.put(Position.getPosition(1,7), Piece.QUEEN_WHITE);
        boardGame = new BoardGame(boardInit);

        Position from = Position.getPosition(1,7);
        Position to = Position.getPosition(4,4);

        boolean result = queenMove.getResult(from,to,boardGame,currentPlayer,listOfMovesToMake,move);

        assertTrue(result);
    }

    private void settingDataForMockedCurrentPlayer(Color color, String currentPlayerName) {
        when(currentPlayer.getColor()).thenReturn(color);
        when(currentPlayer.getName()).thenReturn(currentPlayerName);
    }
}
