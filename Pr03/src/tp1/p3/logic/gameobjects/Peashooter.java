package tp1.p3.logic.gameobjects;

import tp1.p3.logic.GameItem;
import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public class Peashooter extends Planta {

	public static final int PEASHOOTER_COSTE = 50;
	public static final int PEASHOOTER_FRECUENCIA = 0;
	public static final int PEASHOOTER_DAÑO = 1;
	public static final int PEASHOOTER_RESISTENCIA = 3;
	
	
	
	public Peashooter(int col, int row, GameWorld game) {
		super(col, row, PEASHOOTER_FRECUENCIA, PEASHOOTER_RESISTENCIA, game, PEASHOOTER_DAÑO);
	}

	public Peashooter() {
	}

	public String getSymbol() {
		return Messages.PEASHOOTER_SYMBOL;
	}

	public String getName() {
		return Messages.PEASHOOTER_NAME;
	}

	public String getShortcut() {
		return Messages.PEASHOOTER_NAME_SHORTCUT;
	}

	public int getCoste() {
		return PEASHOOTER_COSTE;
	}
	public int getvida() {
		return PEASHOOTER_RESISTENCIA;
	}
	public int getdamage() {
		return PEASHOOTER_DAÑO;
	}

	protected Planta create(GameWorld game, int col, int row) {
		return new Peashooter(col, row, game);
	}

	public void update() {
		
		boolean colision = false;
		int i = col + 1;
		
		nCiclos ++; 
		if (nCiclos >= PEASHOOTER_FRECUENCIA) {
			while (colision == false && i < GameWorld.NUM_COLS) {
				if (game.isPositionFullOcuped(i, row)) {
					colision = true;
					GameItem item = game.getGameItemInPosition(i, row);
					if (item != null) {
						item.receivePlantAttack(this.damage);
					}
				}
				i++;
			}
			if (colision) {
				nCiclos = 0;
			}
		}
	}

}
