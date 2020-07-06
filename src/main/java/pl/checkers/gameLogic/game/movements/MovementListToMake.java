package pl.checkers.gameLogic.game.movements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MovementListToMake implements Iterable<Movement> {
    private final List<Movement>list = new ArrayList<>();

    public MovementListToMake() {
    }
    public void addMove(Movement move){
        list.add(move);
    }
    public void clear(){
        list.clear();
    }

    @Override
    public Iterator<Movement> iterator() {
        return list.iterator();
    }
}
