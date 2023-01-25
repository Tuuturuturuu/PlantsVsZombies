package tp1.p2.logic.gameobjects;

import tp1.p2.view.Messages;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.SunsCreate;

public class Sunflower extends Planta {

	public static final int SUNFLOWER_COSTE = 20;
	public static final int SUNFLOWER_DAMAGE = 0;
	public static final int SUNFLOWER_RESISTENCIA = 1;
	public static final int SUNFLOWER_FRECUENCIA = 3;
	

	public Sunflower(int col, int row, GameWorld game) {
		super(col, row, SUNFLOWER_FRECUENCIA, SUNFLOWER_RESISTENCIA, game, SUNFLOWER_DAMAGE);
	}

	public Sunflower() {
	}

	public String getSymbol() {
		return Messages.SUNFLOWER_SYMBOL;
	}

	public String getName() {
		return Messages.SUNFLOWER_NAME;
	}

	public String getShortcut() {
		return Messages.SUNFLOWER_NAME_SHORTCUT;
	}

	public int getCoste() {
		return SUNFLOWER_COSTE;
	}
	public int getvida() {
		return SUNFLOWER_RESISTENCIA;
	}
	public int getdamage() {
		return SUNFLOWER_DAMAGE;
	}

	protected Planta create(GameWorld game, int col, int row) {
		return new Sunflower(col, row, game);
	}

	public void update() {
		SunsCreate action = new SunsCreate();
		nCiclos++;
		if (nCiclos == SUNFLOWER_FRECUENCIA) {
			nCiclos = 0;
			game.addAction(action);

		}
	}

}
