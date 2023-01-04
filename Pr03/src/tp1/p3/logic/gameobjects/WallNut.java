package tp1.p3.logic.gameobjects;

import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public class WallNut extends Planta{
	public static final int WALL_NUT_COSTE = 50;
	public static final int WALL_NUT_FRECUENCIA = 0;
	public static final int WALL_NUT_DAMAGE = 0;
	public static final int WALL_NUT_RESISTENCIA = 10;


	public WallNut(int col, int row, GameWorld game) {
		super(col, row, WALL_NUT_FRECUENCIA, WALL_NUT_RESISTENCIA, game, WALL_NUT_DAMAGE);
	}

	public WallNut() {
	}

	public String getName() {
		return Messages.WALL_NUT_NAME ;
	}

	public String getShortcut() {
		return Messages.WALL_NUT_NAME_SHORTCUT;
	}
	
	protected String getSymbol() {
		return Messages.WALL_NUT_SYMBOL;
	}

	public int getCoste() {
		return WALL_NUT_COSTE;
	}
	public int getvida() {
		return WALL_NUT_RESISTENCIA;
	}
	public int getdamage() {
		return WALL_NUT_DAMAGE;
	}

	protected Planta create(GameWorld game, int col, int row) {
		return new WallNut(col , row , game);
	}

	public void update() {
	}

}
