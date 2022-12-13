package tp1.p3.logic.gameobjects;

import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public abstract class Planta extends GameObject {
	
	public Planta(){
		
	}
	
	public Planta (int col, int row, int speed, int vida, GameWorld game  , int damage) {
		super ( game , col , row, speed, vida , damage);
	}

	public abstract String getName();

	public abstract String getShortcut();
	
	public abstract int getCoste();
	protected abstract int getvida();
	protected abstract int getdamage();
	
	public boolean fillPosition() {
		return true;
	}
	
	public void onEnter() {
		
	}
	public void onExit() {
		
	}
	
	public String getDescription() {
		return Messages.plantDescription(getShortcut(), getCoste(), getdamage(), getvida());
	}
	
	protected abstract Planta create(GameWorld game , int col , int row);

	public boolean matchPlant(String word ) {
		return word.equalsIgnoreCase(getName()) || word.equalsIgnoreCase(getSymbol());
	}
	
	public boolean receiveZombieAttack(int damage) {
		vida = vida - damage; 
		if (vida == 0) {
			//game.objetoMuerto(this);
		}
		return true;
	}
	
	public boolean receivePlantAttack(int damage) {	
		return false;
	}
	
	public boolean catchObject() {
		return false;
	}

	

}
