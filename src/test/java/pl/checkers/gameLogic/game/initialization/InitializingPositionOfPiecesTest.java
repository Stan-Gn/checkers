package pl.checkers.gameLogic.game.initialization;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.piece.Piece;

import static pl.checkers.gameLogic.piece.Piece.*;

public class InitializingPositionOfPiecesTest {
    private BoardGame boardGame;
    private InitialBoardSettingInterface initializingPositionOfPieces;

    @Before
    public void setUp() throws Exception {
        initializingPositionOfPieces = new InitializingCheckers();
        boardGame = new BoardGame(initializingPositionOfPieces);


    }

    @Test
    public void whitePiecesInPosition() {
        Position[] positions = {
                Position.getPosition(0,7),
                Position.getPosition(2,7),
                Position.getPosition(4,7),
                Position.getPosition(6,7),
                Position.getPosition(1,6),
                Position.getPosition(3,6),
                Position.getPosition(5,6),
                Position.getPosition(7,6),
                Position.getPosition(0,5),
                Position.getPosition(2,5),
                Position.getPosition(4,5),
                Position.getPosition(6,5)
        };
        Piece whitePiece = PAWN_WHITE;

        for (Position position : positions) {
            assertThat(boardGame.getPiece(position)).isEqualTo(whitePiece);
        }

    }

    @Test
    public void emptyPositionsBetweenWhitePieces() {
        Position[] positions = {
                Position.getPosition(1,7),
                Position.getPosition(3,7),
                Position.getPosition(5,7),
                Position.getPosition(7,7),
                Position.getPosition(0,6),
                Position.getPosition(2,6),
                Position.getPosition(4,6),
                Position.getPosition(6,6),
                Position.getPosition(1,5),
                Position.getPosition(3,5),
                Position.getPosition(5,5),
                Position.getPosition(7,5)

        };

        Piece emptyPosition = null;

        for (Position position : positions) {
            assertThat(boardGame.getPiece(position)).isEqualTo(emptyPosition);
        }

    }

    @Test
    public void blackPiecesInPosition() {
        Position[] positions = {
                Position.getPosition(1,0),
                Position.getPosition(3,0),
                Position.getPosition(5,0),
                Position.getPosition(7,0),
                Position.getPosition(0,1),
                Position.getPosition(2,1),
                Position.getPosition(4,1),
                Position.getPosition(6,1),
                Position.getPosition(1,2),
                Position.getPosition(3,2),
                Position.getPosition(5,2),
                Position.getPosition(7,2)
        };
        Piece whitePiece = PAWN_BLACK;

        for (Position position : positions) {
            assertThat(boardGame.getPiece(position)).isEqualTo(whitePiece);
        }

    }

    @Test
    public void emptyPositionsBetweenBlackPieces() {
        Position[] positions = {
                Position.getPosition(0,0),
                Position.getPosition(2,0),
                Position.getPosition(4,0),
                Position.getPosition(6,0),
                Position.getPosition(1,1),
                Position.getPosition(3,1),
                Position.getPosition(5,1),
                Position.getPosition(7,1),
                Position.getPosition(0,2),
                Position.getPosition(2,2),
                Position.getPosition(4,2),
                Position.getPosition(6,2)

        };

        Piece emptyPosition = null;

        for (Position position : positions) {
            assertThat(boardGame.getPiece(position)).isEqualTo(emptyPosition);
        }

    }
}