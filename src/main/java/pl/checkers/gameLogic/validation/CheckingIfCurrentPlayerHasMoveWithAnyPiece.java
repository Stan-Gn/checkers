package pl.checkers.gameLogic.validation;

import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.game.Players;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.preChecking.CheckingIfPieceInPositionBelongsCurrentPlayer;
import pl.checkers.gameLogic.validation.preChecking.CheckingIfPositionIsEmpty;


public class CheckingIfCurrentPlayerHasMoveWithAnyPiece {
    private final Players players;
    private final BoardGame boardGame;
    private final PieceHasBattleMoveInAnyDirectionInterface pieceHasBattleMoveInAnyDirection;
    private final PieceHasRegularMoveInAnyDirection pieceHasRegularMoveInAnyDirection;


    public CheckingIfCurrentPlayerHasMoveWithAnyPiece(BoardGame boardGame, Players players) {
        this.players = players;
        this.boardGame = boardGame;
        this.pieceHasBattleMoveInAnyDirection = new PieceHasBattleMoveInAnyDirection();
        this.pieceHasRegularMoveInAnyDirection = new PieceHasRegularMoveInAnyDirection();
    }

    public boolean getResult() {
        AbstractPlayer currentPlayer = players.getCurrentPlayer();

        for (int i = 0; i < BoardGame.SIZE; i++) {
            for (int j = 0; j < BoardGame.SIZE; j++) {
                Position position = Position.getPosition(j, i);
                if (!CheckingIfPositionIsEmpty.getResult(position, boardGame) &&
                        CheckingIfPieceInPositionBelongsCurrentPlayer.getResult(currentPlayer, boardGame, position) &&
                        (pieceHasBattleMoveInAnyDirection.getResult(boardGame, position, currentPlayer) ||
                                pieceHasRegularMoveInAnyDirection.getResult(boardGame,position,currentPlayer))) {
                    return true;
                }
            }
        }
        return false;
    }
}
