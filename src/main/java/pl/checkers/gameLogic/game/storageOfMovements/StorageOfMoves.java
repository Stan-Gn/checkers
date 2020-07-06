package pl.checkers.gameLogic.game.storageOfMovements;

import pl.checkers.gameLogic.game.movements.Movement;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class StorageOfMoves implements StorageOfMovesInterface {
    private final List<Movement> listOfMoves = new LinkedList<>();

    public StorageOfMoves() {
    }

    public void addMoves(Iterator<Movement> moves) {
        while (moves.hasNext()) {
            listOfMoves.add(0, moves.next());
        }
        removeMovesAboveFifteenthIndex();
    }

    private void removeMovesAboveFifteenthIndex() {
        int indexOfFifteenthItem = 14;
        if (listOfMoves.size() > 15) {
            for (int i = listOfMoves.size(); i >= indexOfFifteenthItem; i--) {
                int lastItemIndexToRemove = listOfMoves.size() - 1;
                listOfMoves.remove(lastItemIndexToRemove);
            }
        }
    }

    public List<Movement> getLastFifteenMovesIfExist() {
        return listOfMoves;
    }


}
