package tp1.p2.logic;

import java.util.Random;

import tp1.p2.control.Level;
import tp1.p2.logic.gameobjects.Zombies;
import tp1.p2.logic.gameobjects.ZombieFactory;

/**
 * Manage zombies in the game.
 *
 */
public class ZombiesManager {

	private GameWorld game;

	private Level level;

	private Random rand;

	private int zombiesAlived;
	
	private int remainingZombies;
	

	public ZombiesManager(GameWorld game, Level level, Random rand) {
		this.game = game;
		this.level = level;
		this.rand = rand;
		this.zombiesAlived = 0;
		this.remainingZombies = level.getNumberOfZombies();
	}

	/**
	 * Checks if the game should add (if possible) a zombie to the game.
	 * 
	 * @return <code>true</code> if a zombie should be added to the game.
	 */
	private boolean shouldAddZombie() {
		return rand.nextDouble() < level.getZombieFrequency();
	}

	/**
	 * Return a random row within the board limits.
	 * 
	 * @return a random row.
	 */
	private int randomZombieRow() {
		return rand.nextInt(GameWorld.NUM_ROWS);
	}

	private int randomZombieType() {
		return rand.nextInt(ZombieFactory.getAvailableZombies().size());
	}

	public void update() {
		addZombie();
	}

	public boolean addZombie() {
		int row = randomZombieRow();
		return addZombie(row);
	}

	public boolean addZombie(int row) {
		boolean canAdd = getRemainingZombies() > 0 && shouldAddZombie() && isPositionEmpty(GameWorld.NUM_COLS, row);
		int zombieType = randomZombieType();

		if (canAdd) {
			zombiesAlived++;
			game.addItem(ZombieFactory.spawnZombie(zombieType, game, GameWorld.NUM_COLS, row));
			remainingZombies--;
		}
		return canAdd;
	}

	private boolean isPositionEmpty(int numCols, int row) {
		return 	!game.isPositionFullOcuped(numCols, row);
	}
	
	public int getZombiesAlived() {
		return zombiesAlived;
	}
	public int getRemainingZombies() {
		return remainingZombies;
	}
	public void onExit() {
		zombiesAlived--;
	}
	

}
