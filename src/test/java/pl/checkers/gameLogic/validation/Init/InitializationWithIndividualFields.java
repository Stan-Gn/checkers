package pl.checkers.gameLogic.validation.Init;


import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.game.initialization.InitialBoardSettingInterface;
import pl.checkers.gameLogic.piece.Color;
import pl.checkers.gameLogic.piece.Piece;
import pl.checkers.gameLogic.piece.VectorYaxis;

import java.util.Iterator;
import java.util.Map;

public class InitializationWithIndividualFields implements InitialBoardSettingInterface {
    final Map<Position, Piece> initFields;

    public InitializationWithIndividualFields(Map<Position, Piece> initFields) {
        this.initFields = initFields;
    }

    @Override
    public void init(Piece[][] board) {
        setPiecesOnBoard(board);
    }

    private void setPiecesOnBoard(Piece[][] board) {
        clearBoard(board);

        Color.WHITE.setDirectionOfMovementAlongYAxis(VectorYaxis.positiveDirectionOfMovementAlongYAxis);
        Iterator<Map.Entry<Position, Piece>> init = initFields.entrySet().iterator();
        while (init.hasNext()) {
            Map.Entry<Position, Piece> entry = init.next();
            int x = entry.getKey().getX();
            int y = entry.getKey().getY();
            Piece piece = entry.getValue();
            board[y][x] = piece;
        }
    }

    private void clearBoard(Piece[][] board) {
        for(int i = 0; i< BoardGame.SIZE; i++){
            for(int j = 0;j<BoardGame.SIZE;j++){
                board[i][j] = null;
            }
        }
    }
}
