package pl.checkers.gameLogic.game;


import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.exceptions.InvalidMoveException;
import pl.checkers.gameLogic.game.initialization.InitialBoardSettingInterface;
import pl.checkers.gameLogic.game.initialization.InitializingCheckers;
import pl.checkers.gameLogic.player.AbstractPlayer;

public class Game implements Runnable {
    private final Players players;
    private final BoardGame boardGame;
    private final Rules rules;
    private Position from;
    private Position to;
    AbstractPlayer currentPlayer;

    public Game(Players players) {
        InitialBoardSettingInterface initBoard = new InitializingCheckers();
        this.boardGame = new BoardGame(initBoard);
        this.players = players;
        this.rules = new Rules(boardGame, players);
        players.initSettings(boardGame.getClonedBoard());

    }

    public void run() {
        boolean occurredExceptionFlag = false;
        do {
            boolean correctMoveFlag;
            getCurrentPlayerFromListOfGameParticipants();
            do {
                correctMoveFlag = true;
                getPositionsFromCurrentPlayerToMakeAMoveOnTheBoard(currentPlayer);
                try {
                    validateMoveOfCurrentPlayer(currentPlayer);
                } catch (InvalidMoveException e) {
                    correctMoveFlag = false;
                    sendCurrentPlayerInfoAboutWrongMove(e);
                } catch (Exception e) {
                    correctMoveFlag = false;
                    occurredExceptionFlag = true;
                    sendPlayersInformationAboutAnError(e);
                }

                if (correctMoveFlag) {
                    makeCurrentPlayerMoveOnBoard();
                    saveMovementToListOfMovesOfBothPlayers();
                    sendBoardToPlayers();
                }

            } while ((!correctMoveFlag || whetherCurrentPlayerIsToMakeNextMove()) && !occurredExceptionFlag);

           rules.nextPlayer();

        } while (rules.isGameInProgress() && !occurredExceptionFlag);

        sendPlayersReasonForEndOfGame();

    }

    private void sendPlayersReasonForEndOfGame() {
        players.sendMessageToAllPlayers(rules.getReasonEndOfGame());
    }

    private boolean whetherCurrentPlayerIsToMakeNextMove() {
        return rules.nextMoveToCurrentPlayer();
    }

    private void sendBoardToPlayers() {
        players.sendBoardToPlayers(boardGame.getClonedBoard());
    }

    private void saveMovementToListOfMovesOfBothPlayers() {
        rules.saveToStorage();
    }

    private void sendCurrentPlayerInfoAboutWrongMove(InvalidMoveException e) {
        currentPlayer.infoForPlayer(e.getMessage());
    }

    private void makeCurrentPlayerMoveOnBoard() {
        rules.makeMove(currentPlayer);
    }

    private void sendPlayersInformationAboutAnError(Exception e) {
        players.sendMessageToAllPlayers("Error " + e.getMessage());
    }

    private void getCurrentPlayerFromListOfGameParticipants() {
        currentPlayer = players.getCurrentPlayer();
    }

    private void validateMoveOfCurrentPlayer(AbstractPlayer currentPlayer) throws CloneNotSupportedException {
        rules.checkMove(from, to, currentPlayer);
    }

    private void getPositionsFromCurrentPlayerToMakeAMoveOnTheBoard(AbstractPlayer currentPlayer) {
        from = currentPlayer.getPositionFrom();
        to = currentPlayer.getPositionTo();
    }

}


