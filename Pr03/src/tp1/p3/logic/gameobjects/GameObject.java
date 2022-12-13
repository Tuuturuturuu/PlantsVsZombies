package tp1.p3.logic.gameobjects;

import static tp1.p3.view.Messages.status;

import tp1.p3.logic.GameItem;
import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

/**
 * Base class for game non playable character in the game.
 *
 */
public abstract class GameObject implements GameItem {

	protected GameWorld game;

	protected int col;

	protected int row;

	protected int vida;
	
	protected int damage;
	
	protected int speed;
	
	protected int nCiclos;

	GameObject() {
	}

	GameObject(GameWorld game, int col, int row, int speed, int vida , int damage) {
		this.game = game;
		this.col = col;
		this.row = row;
		
		this.speed = speed;
		this.vida = vida;
		this.damage = damage;
		
		this.nCiclos = -1;
	}

	public boolean isInPosition(int col, int row) {
		return this.col == col && this.row == row;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public boolean isAlive() {
		return vida > 0;
	}

	public String toString() {
		if (isAlive()) {
			return String.format(Messages.GAME_OBJECT_STATUS, getSymbol(), vida);
		} else {
			return "";
		}
	}

	abstract protected String getSymbol();

	abstract public String getDescription();

	abstract public void update();

	abstract public void onEnter();

	abstract public void onExit();
}
