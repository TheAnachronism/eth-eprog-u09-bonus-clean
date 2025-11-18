
public class Human {

    private TurnScheduler _scheduler;

    public int health, position;
    public HumanClass humanClass;

    public int getHealth() {
        return health;
    }

    public int getPosition() {
        return position;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public Human(TurnScheduler scheduler, HumanClass humanClass, int health, int position) {
        this._scheduler = scheduler;
        this.humanClass = humanClass;
        this.health = health;
        this.position = position;
    }

    public boolean scheduleAction(Action action) {
        if (!isAlive())
            return false;

        if (humanClass == HumanClass.JESTER) {
            return true;
        }

        int delay = switch (action) {
            case Action.ATTACK -> 0;
            case Action.SUMMON -> humanClass == HumanClass.WARRIOR ? 1 : 2;
        };

        _scheduler.scheduleAction(action, this, delay);
        return true;
    }
}
