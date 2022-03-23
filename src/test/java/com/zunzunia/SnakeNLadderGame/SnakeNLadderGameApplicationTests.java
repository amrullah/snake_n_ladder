package com.zunzunia.SnakeNLadderGame;

import com.zunzunia.SnakeNLadderGame.entities.Game;
import com.zunzunia.SnakeNLadderGame.entities.Ladder;
import com.zunzunia.SnakeNLadderGame.entities.Snake;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@SpringBootTest
class SnakeNLadderGameApplicationTests {

	private static final GameInitializer gameInitializer = new GameInitializer();

	@Test
	void testGameCreation() throws IllegalAccessException {
		// copy this test case to create examples of your own
		int numberOfPlayers = 4;
		List<Snake> snakes = new ArrayList<>();
		snakes.add(new Snake(40, 13));
		List<Ladder> ladders = new ArrayList<>();
		ladders.add(new Ladder(52, 16));
		Game game = gameInitializer.constructGame(1, numberOfPlayers, snakes, ladders);

		assertEquals(numberOfPlayers, game.players.length);
		assertEquals(101, game.gameBoard.getTotalCountOfPositions());
		System.out.println("whatever");
//		game.runTillComplete();
	}

	@Test
	void testGameCreationWith_ConflictingSnakes_Tops() {
		List<Snake> snakes = new ArrayList<>();
		snakes.add(new Snake(40, 13));
		Snake secondSnakeObject = new Snake(40, 14);
		snakes.add(secondSnakeObject);

		List<Ladder> ladders = new ArrayList<>();
		ladders.add(new Ladder(52, 16));

		Exception exception1 = assertThrows(IllegalArgumentException.class,
				() -> gameInitializer.constructGame(1, 3, snakes, ladders));
		assertEquals("Two snakes have conflicting tops: 40", exception1.getMessage());
		
	}
	@Test
	void testGameCreationWith_ConflictingLadders_Bottoms() {
		List<Snake> snakes = new ArrayList<>();
		snakes.add(new Snake(40, 13));

		List<Ladder> ladders = new ArrayList<>();
		ladders.add(new Ladder(52, 16));
		ladders.add(new Ladder(55, 16));

		Exception exception2 = assertThrows(IllegalArgumentException.class,
				() -> gameInitializer.constructGame(1, 3, snakes, ladders));
		assertEquals("Two ladders have conflicting bottoms: 16", exception2.getMessage());


	}
	@Test
	void testGameCreationWith_ConflictingLadders_TopAndBottom() {
		List<Snake> snakes = new ArrayList<>();
		snakes.add(new Snake(40, 13));

		List<Ladder> ladders = new ArrayList<>();
		ladders.add(new Ladder(52, 16));

		ladders.add(new Ladder(92, 52));

		Exception exception3 = assertThrows(IllegalArgumentException.class,
				() -> gameInitializer.constructGame(1, 3, snakes, ladders));
		assertEquals("Some Ladders have clashing tops and bottoms : [52]", exception3.getMessage());
	}

	@Test
	void testGameCreationWith_ConflictingSnakes_TopAndBottom() {
		List<Snake> snakes = new ArrayList<>();
		snakes.add(new Snake(40, 13));
		snakes.add(new Snake(65, 40));

		List<Ladder> ladders = new ArrayList<>();
		ladders.add(new Ladder(52, 16));

		Exception exception3 = assertThrows(IllegalArgumentException.class,
				() -> gameInitializer.constructGame(1, 3, snakes, ladders));
		assertEquals("Some Snakes have clashing tops and bottoms : [40]", exception3.getMessage());
	}

	@Test
	void testGameCreationWith_Conflicting_SnakesTop_LadderBottom() {
		List<Snake> snakes = new ArrayList<>();
		snakes.add(new Snake(40, 13));
		snakes.add(new Snake(65, 39));

		List<Ladder> ladders = new ArrayList<>();
		ladders.add(new Ladder(97, 40));

		Exception exception3 = assertThrows(IllegalArgumentException.class,
				() -> gameInitializer.constructGame(1, 3, snakes, ladders));
		assertEquals("Some snakes have tops clashing with ladders' bottoms : [40]", exception3.getMessage());
	}

	@Test
	void testGameCreationWith_Conflicting_SnakesTop_LaddersTop() {
		List<Snake> snakes = new ArrayList<>();
		snakes.add(new Snake(40, 13));
		snakes.add(new Snake(65, 39));

		List<Ladder> ladders = new ArrayList<>();
		ladders.add(new Ladder(97, 43));
		ladders.add(new Ladder(65, 33));

		Exception exception3 = assertThrows(IllegalArgumentException.class,
				() -> gameInitializer.constructGame(1, 3, snakes, ladders));
		assertEquals("Some snakes have tops clashing with ladders' tops : [65]", exception3.getMessage());
	}

	@Test
	void testGameCreationWith_Conflicting_SnakesBottom_LaddersBottom() {
		List<Snake> snakes = new ArrayList<>();
		snakes.add(new Snake(40, 13));
		snakes.add(new Snake(65, 43));

		List<Ladder> ladders = new ArrayList<>();
		ladders.add(new Ladder(97, 43));
		ladders.add(new Ladder(66, 33));

		Exception exception3 = assertThrows(IllegalArgumentException.class,
				() -> gameInitializer.constructGame(1, 3, snakes, ladders));
		assertEquals("Some snakes have bottoms clashing with ladders' bottoms : [43]", exception3.getMessage());
	}
}
