package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.logic.GameWorld;

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

	public static Zombies spawnZombie(int zombieIdx, GameWorld game, int col, int row) {
		if (!isValidZombie(zombieIdx)) {
			return null;
		}
		return AVAILABLE_ZOMBIES.get(zombieIdx).create(game ,col, row); // mirar si se puede
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
