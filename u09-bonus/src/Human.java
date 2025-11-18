
public class Human {
	
	public int health, position;
	
	public int getHealth() {
		return health;
	}
	
	public int getPosition() {
		return position;
	}
	
	public boolean isAlive() {
		return health > 0;
	}
	
	public boolean scheduleAction(Action action) {
		//TODO
		
		return false;
	}
}
