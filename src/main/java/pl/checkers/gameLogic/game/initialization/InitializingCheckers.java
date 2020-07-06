package pl.checkers.gameLogic.game.initialization;


import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.game.initialization.InitialBoardSettingInterface;
import pl.checkers.gameLogic.piece.Color;
import pl.checkers.gameLogic.piece.Piece;
import pl.checkers.gameLogic.piece.VectorYaxis;

public class InitializingCheckers implements InitialBoardSettingInterface {
       private static final int numberOfRowsPlaced = 3;

    public void init(Piece[][] board) {
        setWhitePieces(board);
        setBlackPieces(board);
    }

    private static void setWhitePieces(Piece[][] boardGame) {
        settingDirectionOfColorMovementOnTheBoard(Color.BLACK, VectorYaxis.positiveDirectionOfMovementAlongYAxis);

        for (int i = 0; i < numberOfRowsPlaced; i++) {
            int initialRowIndex = (i % 2 == 0 ? 1 : 0);
            for (int j = initialRowIndex; j < BoardGame.SIZE; j += 2) {
                boardGame[i][j] = Piece.PAWN_BLACK;
            }
        }
    }

    private static void settingDirectionOfColorMovementOnTheBoard(Color color, VectorYaxis positiveDirectionOfMovementAlongYAxis) {
        color.setDirectionOfMovementAlongYAxis(positiveDirectionOfMovementAlongYAxis);
    }

    private static void setBlackPieces(Piece[][] boardGame) {
        settingDirectionOfColorMovementOnTheBoard(Color.WHITE, VectorYaxis.negativeDirectionOfMovementAlongYAxis);
        int startingXCoordinate = BoardGame.SIZE - 1;
        for (int i = startingXCoordinate; i > startingXCoordinate - numberOfRowsPlaced; i--) {
            int initialRowIndex = (i % 2 == 0 ? 1 : 0);
            for (int j = initialRowIndex; j < BoardGame.SIZE; j += 2) {
                boardGame[i][j] = Piece.PAWN_WHITE;
            }
        }
    }
}
