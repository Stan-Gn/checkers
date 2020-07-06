package pl.checkers.gameLogic.validation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.game.Players;
import pl.checkers.gameLogic.piece.Color;
import pl.checkers.gameLogic.piece.Piece;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.Init.InitializationWithIndividualFields;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CheckingIfCurrentPlayerHasMoveWithAnyPieceTest {
    private Map<Position, Piece> initFields;
    private InitializationWithIndividualFields boardInit;
    private AbstractPlayer playerWhiteColor = mock(AbstractPlayer.class);
    private AbstractPlayer playerBlackColor = mock(AbstractPlayer.class);

    private BoardGame boardGame;
    private CheckingIfCurrentPlayerHasMoveWithAnyPiece checkingIfCurrentPlayerHasMoveWithAnyPiece;
    private Players players;

    @Before
    public void setUp() throws Exception {
        this.initFields = new HashMap<>();
        this.boardInit = new InitializationWithIndividualFields(initFields);
        this.players = Mockito.spy(new Players());


        players.addPlayer(playerWhiteColor);
        players.addPlayer(playerBlackColor);

        when(playerBlackColor.getColor()).thenReturn(Color.BLACK);
        when(playerWhiteColor.getColor()).thenReturn(Color.WHITE);
        when(players.getCurrentPlayer()).thenReturn(playerWhiteColor);
    }

    @Test
    public void falseIfWhitePawnIsBlockedByTwoOpponentsPawns() {
        Position positionWhitePawn = Position.getPosition(0, 7);
        Position positionFirstBlackPawn = Position.getPosition(1, 6);
        Position positionSecondBlackPawn = Position.getPosition(2, 5);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        initFields.put(positionFirstBlackPawn, Piece.PAWN_BLACK);
        initFields.put(positionSecondBlackPawn, Piece.PAWN_BLACK);
        boardGame = new BoardGame(boardInit);
        this.checkingIfCurrentPlayerHasMoveWithAnyPiece = new CheckingIfCurrentPlayerHasMoveWithAnyPiece(boardGame, players);

        assertFalse(checkingIfCurrentPlayerHasMoveWithAnyPiece.getResult());

    }

    @Test
    public void falseIfWhiteQueenIsBlockedByTwoOpponentsPawns() {
        Position positionWhiteQueen = Position.getPosition(0, 7);
        Position positionFirstBlackPawn = Position.getPosition(1, 6);
        Position positionSecondBlackPawn = Position.getPosition(2, 5);
        initFields.put(positionWhiteQueen, Piece.QUEEN_WHITE);
        initFields.put(positionFirstBlackPawn, Piece.PAWN_BLACK);
        initFields.put(positionSecondBlackPawn, Piece.PAWN_BLACK);
        boardGame = new BoardGame(boardInit);
        this.checkingIfCurrentPlayerHasMoveWithAnyPiece = new CheckingIfCurrentPlayerHasMoveWithAnyPiece(boardGame, players);

        assertFalse(checkingIfCurrentPlayerHasMoveWithAnyPiece.getResult());

    }

    @Test
    public void trueIfWhiteQueenIsHasOnlyOneBattleMove() {
        Position positionWhiteQueen = Position.getPosition(0, 7);
        Position positionFirstBlackPawn = Position.getPosition(1, 6);
        initFields.put(positionWhiteQueen, Piece.QUEEN_WHITE);
        initFields.put(positionFirstBlackPawn, Piece.PAWN_BLACK);
        boardGame = new BoardGame(boardInit);
        this.checkingIfCurrentPlayerHasMoveWithAnyPiece = new CheckingIfCurrentPlayerHasMoveWithAnyPiece(boardGame, players);

        assertTrue(checkingIfCurrentPlayerHasMoveWithAnyPiece.getResult());

    }

    @Test
    public void trueIfWhitePawnIsHasOnlyOneBattleMove() {
        Position positionWhitePawn = Position.getPosition(0, 7);
        Position positionFirstBlackPawn = Position.getPosition(1, 6);
        initFields.put(positionWhitePawn, Piece.PAWN_WHITE);
        initFields.put(positionFirstBlackPawn, Piece.PAWN_BLACK);
        boardGame = new BoardGame(boardInit);
        this.checkingIfCurrentPlayerHasMoveWithAnyPiece = new CheckingIfCurrentPlayerHasMoveWithAnyPiece(boardGame, players);

        assertTrue(checkingIfCurrentPlayerHasMoveWithAnyPiece.getResult());

    }
}