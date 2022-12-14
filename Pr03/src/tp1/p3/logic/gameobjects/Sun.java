package tp1.p3.logic.gameobjects;

import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public class Sun extends GameObject {

	public static int generados;
	public static int cogidos;

	public static final int SUNS_VIDA = 10;

	public Sun(GameWorld game, int col, int row) {
		super(game, col, row, 0, SUNS_VIDA, 0);

	}

	public boolean receiveZombieAttack(int damage) {
		return false;
	}

	public boolean receivePlantAttack(int damage , boolean explosion) {
		return false;
	}

	public boolean fillPosition() {
		return false;
	}

	public boolean catchObject() {
		vida = 0;
		game.addSunCoins();
		cogidos++;
		return true;
	}

	protected String getSymbol() {
		return Messages.SUN_SYMBOL;
	}

	public String getDescription() {
		return Messages.SUN_DESCRIPTION;
	}

	public void update() {
		vida--;
	}

	public void onEnter() {
		generados++;
	}

	public void onExit() {
	}

	

}
