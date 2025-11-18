import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class TurnScheduler {
    private final Queue<Turn> _turns = new LinkedList<>();

    public void scheduleAction(Action action, Human human, int delay) {
        final Turn turn = getTurnWithDelay(delay);
        turn.addAction(human, action);
    }

    public Turn popTurn() {
        return _turns.poll();
    }

    private Turn getTurnWithDelay(int delay) {
        while (_turns.size() < delay + 1) {
            final Turn turn = new Turn();
            _turns.add(turn);
        }

        return getTurnAtIndex(delay);
    }

    private Turn getTurnAtIndex(int index) {
        Iterator<Turn> iterator = _turns.iterator();
        int count = 0;

        Turn targetTurn = null;
        while (iterator.hasNext()) {
            Turn turn = iterator.next();
            if (count == index) {
                targetTurn = turn;
                break;
            }
            count++;
        }

        return targetTurn;
    }
}
