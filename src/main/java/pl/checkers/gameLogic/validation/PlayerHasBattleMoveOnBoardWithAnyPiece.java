package pl.checkers.gameLogic.validation;


import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.preChecking.CheckingIfPieceInPositionBelongsCurrentPlayer;
import pl.checkers.gameLogic.validation.preChecking.CheckingIfPositionIsEmpty;

import java.util.ArrayList;
import java.util.List;

public class PlayerHasBattleMoveOnBoardWithAnyPiece {

    public static List<Position> getResult(BoardGame boardGame, AbstractPlayer currentPlayer) {
        PieceHasBattleMoveInAnyDirectionInterface pieceHasBattleMoveInAnyDirection = new PieceHasBattleMoveInAnyDirection();
        List<Position>listOfPiecesThatHaveBattleMove = new ArrayList<>();

        for (int i = 0; i < BoardGame.SIZE; i++) {
            for (int j = 0; j < BoardGame.SIZE; j++) {
                Position currentPosition = Position.getPosition(j, i);
                if (!CheckingIfPositionIsEmpty.getResult(currentPosition, boardGame)
                        && CheckingIfPieceInPositionBelongsCurrentPlayer.getResult(currentPlayer, boardGame, currentPosition)
                        && pieceHasBattleMoveInAnyDirection.getResult(boardGame, currentPosition, currentPlayer)) {
                    listOfPiecesThatHaveBattleMove.add(currentPosition);
                }
            }
        }
        return listOfPiecesThatHaveBattleMove;
    }
}
