package tp1.p3.logic.gameobjects;

import tp1.p3.logic.GameItem;
import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public abstract class Zombies extends GameObject {

	
	public Zombies (int col, int row, int speed, int vida, GameWorld game  , int damage) {
		super ( game , col , row , speed, vida , damage);
	}
	public Zombies() {
	}

	public abstract String getName();
	
	public String getDescription() {
		return Messages.zombieDescription(getName() , getSpeed(), getDamage(), getVida());
	}
	
	protected abstract int getVida();
	protected abstract int getDamage();
	protected abstract int getSpeed();
	
	public boolean fillPosition() {
		return true;
	}
	
	protected abstract Zombies create(GameWorld game , int col , int row);

	public boolean matchZombie(String word ) {
		return word.equalsIgnoreCase(getName());
	}
	
	public boolean receiveZombieAttack(int damage) {
		return false;
	}
	
	public boolean receivePlantAttack(int damage , boolean explosion) {	
		vida = vida - damage; 
		if (vida < 0 && explosion) {
			game.addPuntos(10);
		}
		return true;
	}
	
	public boolean zombieMovement() {
		if (this.nCiclos >= getSpeed()) {
			col--;
			nCiclos = 0;
			return true;
		}
		return false;
	}
	
	public void update() {
		if (game.isPositionFullOcuped(col - 1 , row)) {
			GameItem item = game.getGameItemInPosition(col - 1, row);
		    if(item != null ) {  
		        item.receiveZombieAttack(this.damage);	 
		    }
		}
		else {
			nCiclos++;
			zombieMovement();
		}
	}
	
	public void onEnter() {
	}

	public void onExit() {
			game.zombieOnExit();
	}
	
	public boolean catchObject() {
		return false;
	}


	

}

