package tp1.p3.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p3.control.exceptions.GameException;
import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public class ZombieFactory {

	/* @formatter:off */
	private static final List<Zombies> AVAILABLE_ZOMBIES = Arrays.asList(
		new Zombie(),
		new BucketHead(),
		new Sporty(),
		new ExplosiveZombie()
	);
	/* @formatter:on */

	public static boolean isValidZombie(int zombieIdx) {
		return zombieIdx >= 0 && zombieIdx < AVAILABLE_ZOMBIES.size();
	}

	public static Zombies spawnZombie(int zombieIdx, GameWorld game, int col, int row) throws GameException  {
		if (!isValidZombie(zombieIdx)) {
			throw new GameException(Messages.INVALID_GAME_OBJECT);
		}
		return AVAILABLE_ZOMBIES.get(zombieIdx).create(game ,col, row); 
	}

	public static List<Zombies> getAvailableZombies() {
		return Collections.unmodifiableList(AVAILABLE_ZOMBIES);
	}

	/*
	 * Avoid creating instances of this class
	 */
	private ZombieFactory() {
	}
}
