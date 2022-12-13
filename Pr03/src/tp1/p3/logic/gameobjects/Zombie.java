package tp1.p3.logic.gameobjects;

import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public class Zombie extends Zombies {
	
	public static final int ZOMBIE_FRECUENCIA = 2;
	public static final int ZOMBIE_DAÑO = 1;
	public static final int ZOMBIE_RESISTENCIA = 5;
	
	public Zombie(int col, int row, GameWorld game) {
		super(col, row, ZOMBIE_FRECUENCIA  , ZOMBIE_RESISTENCIA, game, ZOMBIE_DAÑO);
	}
	
	public Zombie() {
	}

	public String getName() {
		return Messages.ZOMBIE_NAME;
	}
	
	protected String getSymbol() {
		return Messages.ZOMBIE_SYMBOL;
	}
	
	protected int getVida() {
		return ZOMBIE_RESISTENCIA;
	}
	protected int getDamage() {
		return ZOMBIE_DAÑO;
	}
	protected int getSpeed() {
		return ZOMBIE_FRECUENCIA;
	}
	
	protected Zombies create(GameWorld game, int col, int row) {
		return new Zombie(col , row, game);
	}
	
	
	
}
