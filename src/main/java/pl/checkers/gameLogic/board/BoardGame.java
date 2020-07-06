package pl.checkers.gameLogic.board;


import pl.checkers.gameLogic.game.initialization.InitialBoardSettingInterface;
import pl.checkers.gameLogic.piece.Piece;

import java.util.Arrays;

public class BoardGame implements Cloneable{
    public static final int SIZE = 8;
    private Piece board[][] = new Piece[SIZE][SIZE];
    private final InitialBoardSettingInterface boardInitialisation;

    public BoardGame(InitialBoardSettingInterface boardInitialisation) {
        this.boardInitialisation = boardInitialisation;
        boardInitialisation.init(board);
    }

    public void movePiece (Position from, Position to){
        board[to.getY()][to.getX()] = board[from.getY()][from.getX()];
        removePiece(from);
    }
    public void removePiece(Position from){
        board[from.getY()][from.getX()] = null;
    }

    public Piece getPiece (Position from){
        return board[from.getY()][from.getX()];
    }

    public Piece[][] getClonedBoard() {
       return board.clone();
    }


    public BoardGame clone() {
        BoardGame clonedBoard = new BoardGame(boardInitialisation);
        clonedBoard.board = Arrays.stream(board)
                .map(a ->  Arrays.copyOf(a, a.length))
                .toArray(Piece[][]::new);
        return clonedBoard;
    }


    public void setPieceAtPosition(Position position,Piece piece) {
        board[position.getY()][position.getX()] = piece;
    }
}
