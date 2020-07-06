package pl.checkers.gameLogic.validation.preChecking;


import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;

public class CheckingIfPositionIsEmpty {
    public static boolean getResult(Position position, BoardGame boardGame){
        return boardGame.getPiece(position) == null;
    }


}
