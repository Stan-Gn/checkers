package pl.checkers.gameLogic.validation;


import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.player.AbstractPlayer;

public interface PieceHasBattleMoveInAnyDirectionInterface {
     boolean getResult(BoardGame boardGame, Position position, AbstractPlayer currentPlayer);
}
