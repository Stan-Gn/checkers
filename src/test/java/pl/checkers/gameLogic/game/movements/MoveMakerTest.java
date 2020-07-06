package pl.checkers.gameLogic.game.movements;

import org.junit.Before;
import org.junit.Test;
import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.game.movements.Movement;
import pl.checkers.gameLogic.game.movements.MovementListToMake;
import pl.checkers.gameLogic.game.movements.MoveMaker;
import pl.checkers.gameLogic.game.movements.TypeOfMovement;
import pl.checkers.gameLogic.piece.Color;
import pl.checkers.gameLogic.piece.Piece;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.Init.InitializationWithIndividualFields;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static pl.checkers.gameLogic.piece.Piece.*;

public class MoveMakerTest {
    private Map<Position, Piece> initFieldsMap;
    private InitializationWithIndividualFields boardInit;
    private BoardGame boardGame;
    private AbstractPlayer currentPlayer = mock(AbstractPlayer.class);
    private MovementListToMake listOfMovesToMake;
    private Movement move;
    private MoveMaker moveMaker;

    @Before
    public void setUp() throws Exception {
        this.initFieldsMap = new HashMap<>();
        this.boardInit = new InitializationWithIndividualFields(initFieldsMap);
        when(currentPlayer.getColor()).thenReturn(Color.WHITE);
        this.listOfMovesToMake = new MovementListToMake();
        this.move = new Movement();
        this.moveMaker = new MoveMaker(boardGame);
    }

    @Test
    public void makeMoveFromListOfMoves() {
        Position positionWhitePawn = Position.getPosition(6,6);
        initFieldsMap.put(positionWhitePawn,Piece.PAWN_WHITE);
        boardGame = new BoardGame(boardInit);
        this.moveMaker = new MoveMaker(boardGame);

        move.setFrom(positionWhitePawn);
        move.setTo(Position.getPosition(7,7));
        move.setTypeOfMovement(TypeOfMovement.REGULAR_MOVE);
        listOfMovesToMake.addMove(move);

        move = new Movement();
        move.setTypeOfMovement(TypeOfMovement.EXCHANGE_OF_PAWN_FOR_A_QUEEN);
        move.setFrom(Position.getPosition(7,7));
        listOfMovesToMake.addMove(move);

        moveMaker.makeMoveFromMoveList(listOfMovesToMake);

        assertThat(boardGame.getPiece(Position.getPosition(7,7))).isEqualTo(QUEEN_WHITE);
    }
}