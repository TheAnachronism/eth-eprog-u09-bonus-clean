import java.util.LinkedList;
import java.util.List;

public class Game {

    private final List<Human>[] _humans = new List[1000];
    private final TurnScheduler _turnScheduler = new TurnScheduler();

    public Game() {
        for (int i = 0; i < _humans.length; i++) {
            _humans[i] = new LinkedList<>();
        }
    }

    void advanceTurn() {
        final Turn turnToExecute = _turnScheduler.popTurn();
        if (turnToExecute == null)
            return;

        ActionEntry actionEntry;
        do {
            actionEntry = turnToExecute.popLatestAction();
            if (actionEntry == null) continue;
            final Human human = actionEntry.human();
            human.resetActionState();

            if (human.humanClass == HumanClass.JESTER) {
                continue;
            }

            switch (actionEntry.action()) {
                case ATTACK -> handleDamage(human.position, human.humanClass == HumanClass.WARRIOR ? 10 : 3);
                case SUMMON -> {
                    switch (human.humanClass) {
                        case WARRIOR -> human.health -= 5;
                        case CLERIC -> handleClericMove(human.position);
                    }
                }
            }
        } while (actionEntry != null);
    }

    Human createJester(int health, int position) {
        return createHuman(HumanClass.JESTER, health, position);
    }

    Human createWarrior(int health, int position) {
        return createHuman(HumanClass.WARRIOR, health, position);
    }

    Human createCleric(int health, int position) {
        return createHuman(HumanClass.CLERIC, health, position);
    }

    private Human createHuman(HumanClass humanClass, int health, int position) {
        final Human human = new Human(_turnScheduler, humanClass, health, position);
        _humans[position].add(human);

        return human;
    }

    private void handleDamage(int position, int damage) {
        if (position > 0) {
            List<Human> humans = _humans[position - 1];
            for (Human human : humans) {
                human.health -= damage;
            }
        }

        if (position < _humans.length) {
            List<Human> humans = _humans[position + 1];
            for (Human human : humans) {
                human.health -= damage;
            }
        }
    }

    private void handleClericMove(int position) {
        for (int offset = 3; offset <= 5; offset++) {
            if (position - offset > 0) {
                moveHumans(position, position - offset);
            }

            if (position + offset < _humans.length) {
                moveHumans(position, position + offset);
            }
        }
    }

    private void moveHumans(int targetPosition, int sourcePosition) {
        List<Human> humans = _humans[sourcePosition];
        for (Human human : humans) {
            human.position = targetPosition;
        }
        _humans[targetPosition].addAll(humans);
        _humans[sourcePosition].clear();
    }
}
