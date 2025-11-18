import java.util.LinkedList;
import java.util.Queue;

public class Turn {
    private final Queue<ActionEntry> _actionQueue;

    public Turn() {
        _actionQueue = new LinkedList<>();
    }
        
    public void addAction(Human human, Action action) {
        _actionQueue.add(new ActionEntry(action, human));
    }

    public ActionEntry popLatestAction() {
        return _actionQueue.poll();
    }
}
