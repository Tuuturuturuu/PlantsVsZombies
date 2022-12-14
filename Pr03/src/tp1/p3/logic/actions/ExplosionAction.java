package tp1.p3.logic.actions;

import tp1.p3.logic.GameItem;
import tp1.p3.logic.GameWorld;

public class ExplosionAction implements GameAction {

	private int col;

	private int row;

	private int damage;
	
	boolean planta;

	public ExplosionAction(int col, int row, int damage, boolean reciveZombieAttack) {
		this.col = col;
		this.row = row;
		this.damage = damage;
		this.planta = reciveZombieAttack;
	}

	public void execute(GameWorld game) {
		
		for (int i = col - 1 ; i <= col + 1; i++) {
			for(int j = row - 1; j <= row + 1 ; j++) {
				if (game.isPositionFullOcuped(i, j)) {
					GameItem item = game.getGameItemInPosition(i, j);
					if (item != null) {
						if (planta) {
							item.receivePlantAttack(this.damage, true);
						}
						else {
							item.receiveZombieAttack(damage);
						}
						
					}
				}
			}
		}
	}

}
