import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
	
	@Test
	public void testWarrior1() {
		Game game = new Game();
		Human h1 = game.createWarrior(100, 9);
		Human h2 = game.createWarrior(100, 10);
		Human h3 = game.createWarrior(100, 10);
		Human h4 = game.createWarrior(100, 11);
		Human h5 = game.createWarrior(100, 12);
		
		h1.scheduleAction(Action.ATTACK);
		h2.scheduleAction(Action.ATTACK);
		
		// action is not yet executed 
		assertEquals(100, h1.getHealth());
		assertEquals(100, h2.getHealth());
		assertEquals(100, h3.getHealth());
		assertEquals(100, h4.getHealth());
		assertEquals(100, h5.getHealth());
		
		game.advanceTurn();
		
		// attacks are executed
		assertEquals(90, h1.getHealth()); // hit by h2
		assertEquals(90, h2.getHealth()); // hit by h1
		assertEquals(90, h3.getHealth()); // hit by h1
		assertEquals(90, h4.getHealth()); // hit by h2
		assertEquals(100, h5.getHealth());
	}
	
	@Test
	public void testWarrior2() {
		Game game = new Game();
		Human h1 = game.createWarrior(100, 9);
		Human h2 = game.createWarrior(100, 10);
		
		h1.scheduleAction(Action.SUMMON);
		
		game.advanceTurn();
		
		assertEquals(100, h1.getHealth());
		assertEquals(100, h2.getHealth());
		
		h2.scheduleAction(Action.SUMMON);
		
		game.advanceTurn();
		
		assertEquals(95, h1.getHealth());
		assertEquals(100, h2.getHealth());
		
		game.advanceTurn();
		
		assertEquals(95, h1.getHealth());
		assertEquals(95, h2.getHealth());
	}
	
	@Test
	public void testCleric1() {
		Game game = new Game();
		Human h1 = game.createWarrior(100, 5);
		Human h2 = game.createCleric(100, 10);
		Human h3 = game.createWarrior(100, 11);
		Human h4 = game.createWarrior(100, 15);
		
		h2.scheduleAction(Action.SUMMON);
		
		game.advanceTurn();
		game.advanceTurn();
		
		h1.scheduleAction(Action.ATTACK);
		h3.scheduleAction(Action.ATTACK);
		
		game.advanceTurn();
		
		assertEquals(10, h1.getPosition()); // summoned by h2
		assertEquals(10, h4.getPosition()); // summoned by h2
		
		assertEquals(90, h1.getHealth()); // hit by h3
		assertEquals(90, h2.getHealth()); // hit by h3 
		assertEquals(90, h3.getHealth()); // hit by h1
		assertEquals(90, h4.getHealth()); // hit by h3
	}

	@Test
	public void testWarrior3() {
		Game game = new Game();
		Human h1 = game.createJester(10, 9);
		Human h2 = game.createWarrior(1, 10);
		Human h3 = game.createWarrior(9, 11);

		assertTrue(h2.scheduleAction(Action.ATTACK)); // kills h1 and h3

		game.advanceTurn();

		assertFalse(h1.scheduleAction(Action.ATTACK));
		assertFalse(h1.scheduleAction(Action.SUMMON));
		assertFalse(h3.scheduleAction(Action.ATTACK));
		assertFalse(h3.scheduleAction(Action.SUMMON));
	}

	@Test
	public void testAll1() {
		Game game = new Game();
		Human h1 = game.createCleric(10, 9);
		Human h2 = game.createWarrior(1, 10);
		Human h3 = game.createWarrior(9, 11);

		assertTrue(h2.scheduleAction(Action.ATTACK)); // kills h1 and h3

		game.advanceTurn();

		assertFalse(h1.scheduleAction(Action.ATTACK));
		assertFalse(h1.scheduleAction(Action.SUMMON));
		assertFalse(h3.scheduleAction(Action.ATTACK));
		assertFalse(h3.scheduleAction(Action.SUMMON));
	}
	
}
