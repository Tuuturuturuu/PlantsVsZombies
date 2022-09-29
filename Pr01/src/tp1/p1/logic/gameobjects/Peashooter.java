package tp1.p1.logic.gameobjects;
import tp1.p1.view.Messages;
public class Peashooter {

	private int col;
	private int row;
	private int resistencia;
	private int coste;
	private int frecuencia;
	private int dano;
	
	public static String getDescription() {
		
		return Messages.PEASHOOTER_DESCRIPTION;
	}


public Peashooter(int col , int row) {
	this.col = col;
	this.row = row;
	this.resistencia = 3;
	this.coste = 50;
	this.frecuencia = 1;
	this.dano = 1;
}
public boolean libre(int col, int row){
	boolean libre = true;
	if (this.row == row && this.col == col) {
		libre = false;
	}
	return libre;
}

}
