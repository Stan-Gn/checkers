package pl.checkers.gameLogic.game;


import pl.checkers.gameLogic.board.BoardGame;
import pl.checkers.gameLogic.board.Position;
import pl.checkers.gameLogic.game.movements.Movement;
import pl.checkers.gameLogic.game.movements.MovementListToMake;
import pl.checkers.gameLogic.game.movements.MoveMaker;
import pl.checkers.gameLogic.game.movements.TypeOfMovement;
import pl.checkers.gameLogic.game.storageOfMovements.StorageOfMoves;
import pl.checkers.gameLogic.piece.Figure;
import pl.checkers.gameLogic.player.AbstractPlayer;
import pl.checkers.gameLogic.validation.*;
import pl.checkers.gameLogic.validation.checkingResult.EndOfGameChecker;
import pl.checkers.gameLogic.validation.preChecking.PreChecking;

import java.util.List;
import java.util.Random;

public class Rules implements RulesInterface {
    private final BoardGame boardGame;
    private final PreChecking preChecking;
    private final MoveValidation pieceMoveValidation;
    private final MovementListToMake listOfMovesToMake;
    private Movement movement;
    private final MoveMaker moveMaker;
    private final StorageOfMoves storageOfMoves;
    private final EndOfGameChecker endOfGameChecker;
    private final Players players;


    public Rules(BoardGame boardGame, Players players) {
        this.players = players;
        this.boardGame = boardGame;
        this.preChecking = new PreChecking(boardGame);
        this.pieceMoveValidation = new MoveValidation(boardGame);
        this.storageOfMoves = new StorageOfMoves();
        this.moveMaker = new MoveMaker(boardGame);
        this.listOfMovesToMake = new MovementListToMake();
        this.endOfGameChecker = new EndOfGameChecker(players, boardGame,storageOfMoves);

    }

    public boolean checkMove(Position from, Position to, AbstractPlayer currentPlayer) throws CloneNotSupportedException {
        this.movement = new Movement();

        initialPositionCheckToMakeAMove(from, to, currentPlayer);

        boolean moveIsValid = pieceMoveValidation.validate(from, to, currentPlayer, listOfMovesToMake, movement);

        if (moveIsValid && isTypeOfRegularMovement()) {
            PieceHasBattleMoveInAnyDirection pieceHasBattleMoveInAnyDirection = new PieceHasBattleMoveInAnyDirection();

            boolean isCurrentlyMovingPieceWithRegularMovementShouldMakeBattleMovement = pieceHasBattleMoveInAnyDirection.getResult(boardGame, from, currentPlayer);
            List<Position> listOfPlayerBattleMovesOnBoard = PlayerHasBattleMoveOnBoardWithAnyPiece.getResult(boardGame, currentPlayer);

            if (isCurrentlyMovingPieceWithRegularMovementShouldMakeBattleMovement) {
                setRemovalPenalToMovesToMakeForNotMakingBattleMoveWithCurrentlyMovingPiece(from);
            } else if (playerHasAListOfBattleMovesOneOfWhichShouldBeMadeOnBoard(listOfPlayerBattleMovesOnBoard)) {
                drawPieceFromListOfBattleMovesToRemove(listOfPlayerBattleMovesOnBoard);
            }

        } else if (moveIsValid && isTypeOfBattleMovement()) {
            checkingIfCurrentPlayerHasNextBattleMovement(currentPlayer);
        }

        if (checkingConditionToReplacePawnWithAQueen(currentPlayer)) {
            setPawnExchangeForAQueenAsMoveToMake();
        }

        return true;
    }

    private void setPawnExchangeForAQueenAsMoveToMake() {
        Movement secondMove = new Movement();
        secondMove.setTypeOfMovement(TypeOfMovement.EXCHANGE_OF_PAWN_FOR_A_QUEEN);
        secondMove.setFrom(movement.getTo());
        listOfMovesToMake.addMove(secondMove);
    }

    private boolean checkingConditionToReplacePawnWithAQueen(AbstractPlayer currentPlayer) {
        return !nextMoveToCurrentPlayer()
                && (isTypeOfRegularMovement() || isTypeOfBattleMovement())
                && isFigureInFromPositionIsAPawn()
                && isPawnAtLastPositionAtEndOfBoard(currentPlayer);
    }

    private boolean isPawnAtLastPositionAtEndOfBoard(AbstractPlayer currentPlayer) {
        return (currentPlayer.getColor().getDirectionOfMovementAlongYAxis().getValue() == 1 ? 7 : 0) == movement.getTo().getY();
    }

    private boolean isFigureInFromPositionIsAPawn() {
        return boardGame.getPiece(movement.getFrom()).getFigure().equals(Figure.PAWN);
    }

    private void checkingIfCurrentPlayerHasNextBattleMovement(AbstractPlayer currentPlayer) {
        BoardGame clonedBoardGame = boardGame.clone();
        makingMoveOnClonedBoardToCheckSubsequentMoves(clonedBoardGame);

        PieceHasBattleMoveInAnyDirection pieceHasBattleMoveInAnyDirection = new PieceHasBattleMoveInAnyDirection();
        boolean afterBattleMovePlayerWillHaveAnotherBattleMove = pieceHasBattleMoveInAnyDirection.getResult(clonedBoardGame, movement.getTo(), currentPlayer);
        if (afterBattleMovePlayerWillHaveAnotherBattleMove) {
            movement.currentPlayerHasNextBattleMove();
        }
    }

    private void makingMoveOnClonedBoardToCheckSubsequentMoves(BoardGame clonedBoardGame) {
        MoveMaker moveMakerForClonedBoard = new MoveMaker(clonedBoardGame);
        moveMakerForClonedBoard.makeMove(movement);
    }

    private boolean isTypeOfBattleMovement() {
        return movement.getTypeOfMovement().equals(TypeOfMovement.BATTLE_MOVE);
    }

    private void drawPieceFromListOfBattleMovesToRemove(List<Position> listOfPlayerBattleMovesOnBoard) {
        Random index = new Random();
        Movement move = new Movement();
        move.setFrom(listOfPlayerBattleMovesOnBoard.get(index.nextInt(listOfPlayerBattleMovesOnBoard.size())));
        move.setTypeOfMovement(TypeOfMovement.REMOVAL_PENAL);
        listOfMovesToMake.addMove(move);
    }

    private boolean playerHasAListOfBattleMovesOneOfWhichShouldBeMadeOnBoard(List<Position> listOfPlayerBattleMovesOnBoard) {
        return listOfPlayerBattleMovesOnBoard.size() > 0;
    }


    private void setRemovalPenalToMovesToMakeForNotMakingBattleMoveWithCurrentlyMovingPiece(Position from) {
        listOfMovesToMake.clear();
        movement.setFrom(from);
        movement.setTypeOfMovement(TypeOfMovement.REMOVAL_PENAL);
        listOfMovesToMake.addMove(movement);
    }

    private boolean isTypeOfRegularMovement() {
        return movement.getTypeOfMovement().equals(TypeOfMovement.REGULAR_MOVE);
    }

    private void initialPositionCheckToMakeAMove(Position from, Position to, AbstractPlayer currentPlayer) {
        preChecking.getResult(from, to, currentPlayer);
    }

    public void makeMove(AbstractPlayer currentPlayer) {
        moveMaker.makeMoveFromMoveList(listOfMovesToMake);
    }

    public boolean nextMoveToCurrentPlayer() {
        return movement.isCurrentPlayerHasNextBattleMove();
    }


    public void saveToStorage() {
        storageOfMoves.addMoves(listOfMovesToMake.iterator());
    }

    public boolean isGameInProgress() {
        return endOfGameChecker.isGameInProgress();
    }

    public String getReasonEndOfGame() {
        return endOfGameChecker.getReason();
    }

    public void nextPlayer() {
        players.setNextPlayerAsCurrentPlayer();
        players.sendInfoToPlayersAboutActivity();
    }
}
