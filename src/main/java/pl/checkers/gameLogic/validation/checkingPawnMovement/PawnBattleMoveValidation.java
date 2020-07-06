package pl.checkers.gameLogic.validation.checkingPawnMovement;


import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.preChecking.CheckingIfPieceInPositionBelongsCurrentPlayer;
import pl.checkers.gameLogic.validation.preChecking.CheckingIfPositionIsEmpty;

public class PawnBattleMoveValidation {
    public static final int NUMBER_OF_FIELDS_IN_BATTLE_MOVE = 2;

    public static boolean getResult(Position from, Position to, BoardGame boardGame, AbstractPlayer currentPlayer) {

        return (Math.abs(to.getX() - from.getX()) == NUMBER_OF_FIELDS_IN_BATTLE_MOVE &&
                Math.abs(to.getY() - from.getY()) == NUMBER_OF_FIELDS_IN_BATTLE_MOVE) &&
                !CheckingIfPositionIsEmpty.getResult(Position.getPosition((from.getX() + to.getX()) / 2, (from.getY() + to.getY()) / 2), boardGame) &&
                !CheckingIfPieceInPositionBelongsCurrentPlayer.getResult(currentPlayer, boardGame, Position.getPosition((from.getX() + to.getX()) / 2, (from.getY() + to.getY()) / 2)) &&
                CheckingIfPositionIsEmpty.getResult(to, boardGame);
    }
}
