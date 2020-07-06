package pl.checkers.gameLogic.validation.checkingResult;

import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.game.movements.Movement;
import pl.checkers.gameLogic.game.Players;
import pl.checkers.gameLogic.game.storageOfMovements.StorageOfMoves;
import pl.checkers.gameLogic.game.movements.TypeOfMovement;
import pl.checkers.gameLogic.piece.Color;
import pl.checkers.gameLogic.validation.CheckingIfCurrentPlayerHasMoveWithAnyPiece;
import pl.checkers.gameLogic.validation.preChecking.CheckingIfPositionIsEmpty;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EndOfGameChecker {
    private final Players players;
    private final StorageOfMoves storageOfMoves;
    private final BoardGame boardGame;
    private String reason = "";
    private final Set<Color> colors;
    private final CheckingIfCurrentPlayerHasMoveWithAnyPiece checkingIfCurrentPlayerHasMoveWithAnyPiece;


    public EndOfGameChecker(Players players, BoardGame boardGame,StorageOfMoves storageOfMoves) {
        this.storageOfMoves = storageOfMoves;
        this.players = players;
        this.boardGame = boardGame;
        this.colors = new HashSet<>();
        this.checkingIfCurrentPlayerHasMoveWithAnyPiece = new CheckingIfCurrentPlayerHasMoveWithAnyPiece(boardGame,players);

    }

    public boolean isGameInProgress() {
        colors.clear();
        if(!isTwoPlayersInGame()){
            reason = "Only one player left";
            return false;
        }
        else if(lastFifteenMovesAreRegularMoves()){
             reason = "Fifteen regular movements were made - DRAW ";
             return false;
        }
        else if (!piecesOfBothPlayersOnBoard()){
            String nameOfWinner = getNameOfWinner();
            reason = "The winner is: " + nameOfWinner;
            return false;
        }else if(!checkingIfCurrentPlayerHasMoveWithAnyPiece.getResult()){
            reason = nameOfCurrentPlayer() +": stalemate!";
            return false;
        }

        return true;
    }

    private String nameOfCurrentPlayer() {
        return players.getCurrentPlayer().getName();
    }

    private String getNameOfWinner() {
        return players.getPlayerOfColor(colors.stream().findFirst().get()).getName();
    }

    private boolean piecesOfBothPlayersOnBoard() {

        for (int i = 0; i < BoardGame.SIZE; i++)
            for (int j = 0; j < BoardGame.SIZE; j++) {
                if (!CheckingIfPositionIsEmpty.getResult(Position.getPosition(j, i), boardGame)) {
                    colors.add(boardGame.getPiece(Position.getPosition(j, i)).getColor());
                }
            }
        return colors.size() == 2;
    }

    private boolean isTwoPlayersInGame() {
        return players.size() == 2;
    }

    private boolean lastFifteenMovesAreRegularMoves() {
        List<Movement> listOfLastFifteenMovesIfExist = storageOfMoves.getLastFifteenMovesIfExist();
        return listOfLastFifteenMovesIfExist.size() >= 15 &&
                listOfLastFifteenMovesIfExist
                        .stream()
                        .allMatch(
                                move -> move.getTypeOfMovement().equals(TypeOfMovement.REGULAR_MOVE)
                        );



    }

    public String getReason() {
        return reason;
    }
}
