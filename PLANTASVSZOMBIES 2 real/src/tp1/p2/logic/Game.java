package tp1.p2.logic;

import static tp1.p2.view.Messages.error;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;


import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.control.Level;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.logic.gameobjects.Sun;
import tp1.p2.view.Messages;

public class Game implements GameStatus, GameWorld {

	public static final int INIT_SUNS = 50;
	public static final int SUNSCOINS_X_SUN = 10;

	private long seed;

	private Level level;

	private int cycle;

	private GameObjectContainer container;
	
	private ZombiesManager zombiesManager;
	private SunsManager sunsManager;

	private Deque<GameAction> actions;

	private int suns;
	
	private boolean playerQuit;
	
	private int remainingZombies;
	
	private String ganador;
	
	

	// TODO add your attributes here

	public Game(long seed, Level level) {
		this.seed = seed;
		this.level = level;
		reset();
		cycle++;
	}

	/**
	 * Resets the game.
	 */

	public void reset() {
		reset(this.level, this.seed);
	}

	/**
	 * Resets the game with the provided level and seed.
	 * 
	 * @param level {@link Level} Used to initialize the game.
	 * @param seed  Random seed Used to initialize the game.
	 */

	public void reset(Level level, long seed) {
		this.seed = seed;
		this.level = level;
		this.container = new GameObjectContainer();
		this.remainingZombies = level.getNumberOfZombies();
		this.zombiesManager = new ZombiesManager(this, level, new Random(seed));
		this.sunsManager = new SunsManager(this, new Random(seed));
		this.suns = INIT_SUNS;
		Sun.cogidos = 0;
		Sun.generados = 0;
		this.cycle = -1;
		this.actions = new ArrayDeque<>();
		ganador = "Player Quits";
		System.out.println(String.format(Messages.CONFIGURED_LEVEL, level.name()));
		System.out.println(String.format(Messages.CONFIGURED_SEED, seed));
	}
	
	public void playerQuits(){
		playerQuit = true;
	}

	public boolean isPlayerQuits() {
		return playerQuit;
	}

	
	public boolean isPositionFullOcuped(int col, int row) {
		return container.isPositionFullOcuped(col, row);
	}

	public GameItem getGameItemInPosition(int col, int row) {
		return container.getItem(col , row);
	}
	
	public String positionToString(int col, int row) {
		return container.positionToString(col, row);
	}
	
	public int getRemainingZombies() {
		return remainingZombies;
	}
	public int getCycle() {
		return cycle;
	}
	public int getSuncoins() {
		return suns;
	}
	
	public void addSun() {
		sunsManager.addSun();
	}
	public void consumeSuns(int coste) {
		suns = suns - coste;
	}
	
	public void lessRemainingZombies() {
		remainingZombies--;
		zombiesManager.zombiesOnExit();
	}
	public void zombiesOnEnter() {
		zombiesManager.zombiesOnEnter();
	}
	
	public boolean tryToCatchObject(int col, int row) {	
		GameItem item = container.getSun(col, row);
		if (item != null) {	
			return item.catchObject();
		}
		return false;
		
	}
	
	/**
	 * Executes the game actions and update the game objects in the board.
	 * 
	 */

	public void update() {

		// 1. Execute pending actions
		executePendingActions();

		// 2. Execute game Actions
		// TODO add your code here
		zombiesManager.update();
		sunsManager.update();

		// 3. Game object updates
		
		// TODO add your code here
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

	}

	public boolean execute(Command command) {
		ExecutionResult result = command.execute(this);
		if (result.errorMessage() != null) {
			System.out.print(Messages.error(result.errorMessage()));
		}
		return result.draw();
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
		if (!(getRemainingZombies() == 0)) {
			for (int i = 0; i < NUM_ROWS ; i++) {
				if(isPositionFullOcuped( -1 , i)) {
					ganador = Messages.ZOMBIES_WIN;
					return true;
				}
			}
			return false;
		}
		ganador = Messages.PLAYER_WINS;
			return true;	
	}
	
	public String getGanador() {
		return ganador;
	}
	

	public boolean addItem(GameObject gameObject) {
		container.addObject(gameObject);
		return true;
	}

	public int getGeneratedSuns() {
		return Sun.generados;
	}

	public int getCaughtSuns() {
		return Sun.cogidos;
	}


	public void addAction(GameAction action) {
		actions.add(action);
	}

	public void addSunCoins() {
		suns += SUNSCOINS_X_SUN;
		
	}

	

	// TODO add your code here

}
