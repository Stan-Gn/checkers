package pl.checkers.gameLogic.game.storageOfMovements;

import pl.checkers.gameLogic.game.movements.Movement;

import java.util.Iterator;
import java.util.List;

public interface StorageOfMovesInterface {
    void addMoves(Iterator<Movement> moves);

    List<Movement> getLastFifteenMovesIfExist();
}
