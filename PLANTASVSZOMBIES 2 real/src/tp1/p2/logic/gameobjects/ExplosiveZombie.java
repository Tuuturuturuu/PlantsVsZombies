package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class ExplosiveZombie extends Zombies {

	public static final int EXPLOSIVE_ZOMBIE_FRECUENCIA = 2;
	public static final int EXPLOSIVE_ZOMBIE_DAÑO = 1;
	public static final int EXPLOSIVE_ZOMBIE_DAÑO_EXPLOSIVE = 3;
	public static final int EXPLOSIVE_ZOMBIE_RESISTENCIA = 5;
	
	public ExplosiveZombie(int col, int row,  GameWorld game) {
		super(col, row, EXPLOSIVE_ZOMBIE_FRECUENCIA, EXPLOSIVE_ZOMBIE_RESISTENCIA, game, EXPLOSIVE_ZOMBIE_DAÑO);
	}
	
	public ExplosiveZombie() {
	}

	public String getName() {
		return Messages.EXPLOSIVE_ZOMBIE_NAME;
	}
	
	protected String getSymbol() {
		return Messages.EXPLOSIVE_ZOMBIE_SYMBOL;
	}
	
	protected int getVida() {
		return EXPLOSIVE_ZOMBIE_RESISTENCIA;
	}
	protected int getDamage() {
		return EXPLOSIVE_ZOMBIE_DAÑO;
	}
	protected int getSpeed() {
		return EXPLOSIVE_ZOMBIE_FRECUENCIA;
	}
	public void onExit() {
		game.zombieOnExit();
		game.addAction(new ExplosionAction (col , row, EXPLOSIVE_ZOMBIE_DAÑO_EXPLOSIVE, this.receiveZombieAttack(0)));
	}

	protected Zombies create(GameWorld game, int col, int row) {
		return new ExplosiveZombie(col, row, game);
	}

}
