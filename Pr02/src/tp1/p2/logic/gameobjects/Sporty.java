package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sporty extends Zombies{
	
	public static final int SPORTY_FRECUENCIA = 1;
	public static final int SPORTY_DAÑO = 1;
	public static final int SPORTY_RESISTENCIA = 2;

	public Sporty(int col, int row, GameWorld game) {
		super(col, row, SPORTY_FRECUENCIA, SPORTY_RESISTENCIA, game, SPORTY_DAÑO);
	}
	
	public Sporty() {
	}

	public String getName() {
		return Messages.SPORTY_ZOMBIE_NAME;
	}
	
	protected String getSymbol() {
		return Messages.SPORTY_ZOMBIE_SYMBOL;
	}
	
	protected int getVida() {
		return SPORTY_RESISTENCIA;
	}
	protected int getDamage() {
		return SPORTY_DAÑO;
	}
	protected int getSpeed() {
		return SPORTY_FRECUENCIA;
	}

	protected Zombies create(GameWorld game, int col, int row) {
		return new Sporty(col ,row , game);
	}

}
