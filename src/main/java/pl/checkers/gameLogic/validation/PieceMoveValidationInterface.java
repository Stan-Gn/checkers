package pl.checkers.gameLogic.validation;


import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.game.movements.MovementListToMake;
import pl.checkers.gameLogic.game.movements.Movement;
import pl.checkers.gameLogic.player.AbstractPlayer;

public interface PieceMoveValidationInterface {
    boolean getResult(Position from, Position to, BoardGame boardGame, AbstractPlayer currentPlayer, MovementListToMake listOfMovesToMake, Movement move);

}
