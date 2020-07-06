package pl.checkers.gameLogic.validation.preChecking;


import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.player.AbstractPlayer;

public class CheckingIfPieceInPositionBelongsCurrentPlayer {
    public static boolean getResult(AbstractPlayer currentPlayer, BoardGame boardGame, Position position) {
        return currentPlayer.getColor() == boardGame.getPiece(position).getColor();

    }
}
