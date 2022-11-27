package tp1.p2.logic;

import tp1.p2.control.Command;
import tp1.p2.control.Level;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;


public interface GameWorld {

	public static final int NUM_ROWS = 4;

	public static final int NUM_COLS = 8;

	// TODO add your code here

	void addSun();

	boolean tryToCatchObject(int col, int row);

	boolean addItem(GameObject gameObject);
	
	boolean execute(Command command);

	int getSuncoins();

	boolean isPositionFullOcuped(int i, int row);

	GameItem getGameItemInPosition(int i, int row);

	void consumeSuns(int coste);
	
	void playerQuits();

	void reset();

	void reset(Level level, long seed);

	void addAction(GameAction action);

	void addSunCoins();

	void update();

	void zombieOnExit();

	

}
