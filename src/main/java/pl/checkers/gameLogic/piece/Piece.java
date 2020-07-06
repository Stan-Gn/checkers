package pl.checkers.gameLogic.piece;

import static pl.checkers.gameLogic.piece.Color.*;
import static pl.checkers.gameLogic.piece.Figure.*;


public enum Piece {
    PAWN_BLACK(PAWN, BLACK), PAWN_WHITE(PAWN,WHITE), QUEEN_WHITE(QUEEN,WHITE), QUEEN_BLACK(QUEEN,BLACK);

    final Color color;
    final Figure figure;


    Piece(Figure figure, Color color) {
        this.figure = figure;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Figure getFigure() {
        return figure;
    }


}
