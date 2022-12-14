package tp1.p3.logic;


import tp1.p3.control.Command;
import tp1.p3.control.Level;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.control.exceptions.RecordException;
import tp1.p3.logic.actions.GameAction;
import tp1.p3.logic.gameobjects.GameObject;


public interface GameWorld {

	public static final int NUM_ROWS = 4;

	public static final int NUM_COLS = 8;

	// TODO add your code here

	void addSun();

	void tryToCatchObject(int col, int row) throws GameException;

	boolean addItem(GameObject gameObject);
	
	boolean execute(Command command) throws GameException;

	int getSuncoins();

	boolean isPositionFullOcuped(int i, int row);

	GameItem getGameItemInPosition(int i, int row);
	
	void playerQuits();

	void reset() throws GameException;

	void reset(Level level, long seed) throws GameException;

	void addAction(GameAction action);

	void addSunCoins();

	void update() throws GameException;

	void zombieOnExit();

	void tryToBuy(int cost) throws GameException;

	void checkValidPlantPosition(int col, int row) throws GameException;

	void checkValidZombiePosition(int col, int row) throws GameException;

	void checkValidObjectPosition(int col, int row) throws GameException;

	void addPuntos(int puntos);
	

}
