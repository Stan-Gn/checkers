package pl.checkers.gameLogic.validation.Init;


import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.game.initialization.InitialBoardSettingInterface;
import pl.checkers.gameLogic.piece.Piece;

public class InitWholeBoardWithWhitePiecesToTest implements InitialBoardSettingInterface {

    public void init(Piece[][] board) {
        putWhitePiecesWholeBoard(board);
    }

    static void putWhitePiecesWholeBoard(Piece[][] boardGame) {
        for (int i = 0; i < BoardGame.SIZE; i++) {
            for (int j = 0; j < BoardGame.SIZE;j++){
                boardGame[i][j] = Piece.PAWN_WHITE;
            }
        }
    }


}
