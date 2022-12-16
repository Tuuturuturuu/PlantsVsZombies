package tp1.p3.logic;

import static tp1.p3.view.Messages.error;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import tp1.p3.control.Command;
import tp1.p3.control.exceptions.CommandExecuteException;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.control.exceptions.NotCatchablePositionException;
import tp1.p3.control.exceptions.NotEnoughCoinsException;
import tp1.p3.control.exceptions.RecordException;
import tp1.p3.control.Level;
import tp1.p3.logic.actions.GameAction;
import tp1.p3.logic.gameobjects.GameObject;
import tp1.p3.logic.gameobjects.Sun;
import tp1.p3.view.Messages;

public class Game implements GameStatus, GameWorld {

	public static final int INIT_SUNS = 50;
	public static final int SCORE_X_ZOMBIE = 10;
	public static final int SUNSCOINS_X_SUN = 10;

	private long seed;

	private Level level;

	private int cycle;

	private GameObjectContainer container;

	private ZombiesManager zombiesManager;
	private SunsManager sunsManager;

	private Deque<GameAction> actions;

	private int suns;
	private int puntos;

	private boolean playerQuit;

	private String ganador;

	private Random rand;

	private Record record;

	// TODO add your attributes here

	public Game(long seed, Level level) throws GameException {
		this.seed = seed;
		this.level = level;
		reset();

	}

	/**
	 * Resets the game.
	 */

	public void reset() throws GameException {
		reset(this.level, this.seed);
	}

	/**
	 * Resets the game with the provided level and seed.
	 * 
	 * @param level {@link Level} Used to initialize the game.
	 * @param seed  Random seed Used to initialize the game.
	 * @throws RecordException
	 */

	public void reset(Level level, long seed) throws GameException {
		this.seed = seed;
		this.level = level;
		this.rand = new Random(seed);
		this.container = new GameObjectContainer();

		this.record = new Record(level);
		record.loadRecords(Messages.RECORD_FILENAME);
		this.zombiesManager = new ZombiesManager(this, level, rand);
		this.sunsManager = new SunsManager(this, rand);
		this.suns = INIT_SUNS;
		Sun.cogidos = 0;
		Sun.generados = 0;
		this.puntos = 0;
		this.cycle = 0;
		this.actions = new ArrayDeque<>();
		ganador = "Player leaves the game";
		System.out.println(String.format(Messages.CONFIGURED_LEVEL, level.name()));
		System.out.println(String.format(Messages.CONFIGURED_SEED, seed));

	}

	public void playerQuits() {
		playerQuit = true;
	}

	public boolean isPlayerQuits() {
		return playerQuit;
	}

	public boolean isPositionFullOcuped(int col, int row) {
		return container.isPositionFullOcuped(col, row);
	}

	public GameItem getGameItemInPosition(int col, int row) {
		return container.getItem(col, row);
	}

	public String positionToString(int col, int row) {
		return container.positionToString(col, row);
	}
	
	public Level getLevel() {	
		return level;
	}

	public int getRecord() {
		return record.getRecord();
	}

	public int getCycle() {
		return cycle;
	}

	public int getScore() {
		return puntos;
	}

	public int getSuncoins() {
		return suns;
	}

	public int getGeneratedSuns() {
		return Sun.generados;
	}

	public int getCaughtSuns() {
		return Sun.cogidos;
	}

	public int getRemainingZombies() {
		return zombiesManager.getRemainingZombies();
	}

	public String getGanador() {
		return ganador;
	}

	public void addSun() {
		sunsManager.addSun();
	}

	public boolean addItem(GameObject gameObject) {
		container.addObject(gameObject);
		return true;
	}

	public void addAction(GameAction action) {
		actions.add(action);
	}

	public void addSunCoins() {
		suns += SUNSCOINS_X_SUN;

	}

	public void addPuntos(int puntos) {
		this.puntos += puntos;

	}

	public void tryToBuy(int cost) throws GameException {
		if (suns < cost) {
			throw new NotEnoughCoinsException(Messages.NOT_ENOUGH_COINS);
		} else {
			suns = suns - cost;
		}

	}

	public void tryToCatchObject(int col, int row) throws GameException {
		GameItem item = container.getSun(col, row);
		if (item == null) {
			throw new NotCatchablePositionException(Messages.NO_CATCHABLE_IN_POSITION.formatted(col, row));
		}

		while (item != null) {
			item.catchObject();
			item = container.getSun(col, row);
		}

	}

	/**
	 * Executes the game actions and update the game objects in the board.
	 * 
	 */

	public void update() throws GameException {

		// 1. Execute pending actions
		executePendingActions();

		// 2. Execute game Actions
		zombiesManager.update();
		sunsManager.update();

		// 3. Game object updates

		container.update();

		// 4. & 5. Remove dead and execute pending actions
		boolean deadRemoved = true;
		while (deadRemoved || areTherePendingActions()) {
			// 4. Remove dead
			deadRemoved = this.container.removeDead();

			// 5. execute pending actions
			executePendingActions();
		}

		this.cycle++;

		// 6. Notify commands that a new cycle started
		Command.newCycle();
		// 7. Update record
		if (puntos > record.getRecord()) {
			record.saveRecord(Messages.RECORD_FILENAME, puntos);
		}
	}

	public boolean execute(Command command) throws GameException {
		return command.execute(this);
	}

	private boolean areTherePendingActions() {
		return this.actions.size() > 0;
	}

	private void executePendingActions() {
		while (!this.actions.isEmpty()) {
			GameAction action = this.actions.removeLast();
			action.execute(this);
		}
	}

	public boolean isFinished() {
		if (!(zombiesManager.getZombiesAlived() == 0 && zombiesManager.getRemainingZombies() == 0)) {
			for (int i = 0; i < NUM_ROWS; i++) {
				if (isPositionFullOcuped(-1, i)) {
					ganador = Messages.ZOMBIES_WIN;
					return true;
				}
			}
			return false;
		}
		ganador = Messages.PLAYER_WINS;
		return true;
	}

	public void zombieOnExit() {
		zombiesManager.onExit();
		addPuntos(SCORE_X_ZOMBIE);
	}

	public void checkValidObjectPosition(int col, int row) throws GameException {
		if (container.isPositionFullOcuped(col, row) || col >= GameWorld.NUM_COLS || row >= GameWorld.NUM_ROWS) {
			throw new CommandExecuteException(Messages.INVALID_POSITION.formatted(col, row));
		}

	}

	// Venian en el enunciado pero no los usaria
	public void checkValidPlantPosition(int col, int row) throws GameException {
		checkValidObjectPosition(col, row);
	}

	public void checkValidZombiePosition(int col, int row) throws GameException {
		checkValidObjectPosition(col, row);
	}


}
