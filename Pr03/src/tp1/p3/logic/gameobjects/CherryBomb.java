package tp1.p3.logic.gameobjects;

import tp1.p3.logic.GameWorld;
import tp1.p3.logic.actions.ExplosionAction;
import tp1.p3.logic.actions.GameAction;
import tp1.p3.view.Messages;

public class CherryBomb extends Planta{
	
	public static final int CHERRY_BOMB_COSTE = 50;
	public static final int CHERRY_BOMB_FRECUENCIA = 1;
	public static final int CHERRY_BOMB_DAÑO = 10;
	public static final int CHERRY_BOMB_RESISTENCIA = 2;

	public CherryBomb(int col, int row, GameWorld game) {
		super(col, row, CHERRY_BOMB_FRECUENCIA, CHERRY_BOMB_RESISTENCIA, game, CHERRY_BOMB_DAÑO);
	}

	public CherryBomb() {
	}

	public String getName() {
		return Messages.CHERRY_BOMB_NAME;
	}

	public String getShortcut() {
		return Messages.CHERRY_BOMB_NAME_SHORTCUT;
	}
	protected String getSymbol() {
		if(nCiclos == 1) {
			return Messages.CHERRY_BOMB_SYMBOL.toUpperCase();
		}
		return Messages.CHERRY_BOMB_SYMBOL;
	}

	public int getCoste() {
		return CHERRY_BOMB_COSTE;
	}
	public int getvida() {
		return CHERRY_BOMB_RESISTENCIA;
	}
	public int getdamage() {
		return CHERRY_BOMB_DAÑO;
	}

	
	protected Planta create(GameWorld game, int col, int row) {
		return new CherryBomb(col , row , game);
	}

	public void update() {
		GameAction action = new ExplosionAction(col, row, damage, this.receiveZombieAttack(0));
		nCiclos++;
		if (nCiclos == 2) {
			game.addAction(action);
			vida = 0;
		}	
	}

	

}
