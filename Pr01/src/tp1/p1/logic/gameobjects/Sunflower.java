package tp1.p1.logic.gameobjects;

import tp1.p1.view.Messages;
import tp1.p1.logic.Game;

public class Sunflower {

	private int col;
	private int row;
	private int resistencia;
	private int coste;
	private int frecuencia;
	private int dano;
	private int nCiclos;
	
	private Game juego;
	
public static String getDescription() {
		return Messages.SUNFLOWER_DESCRIPTION ;
	}

public Sunflower(int col , int row) {
	this.col = col;
	this.row = row;
	this.resistencia = 1;
	this.coste = 20;
	this.frecuencia = 10;
	this.dano = 0;
	this.nCiclos = 0;
}
public boolean libre(int col, int row){
	boolean libre = true;
	if (this.row == row && this.col == col) {
		libre = false;
	}
	return libre;
}
public boolean genera() {
	boolean genera = false;
	this.nCiclos ++; // mirar
	if (this.nCiclos == 2){
		genera = true;
		this.nCiclos = 0;
	}
	
	return genera;
}
public void menosVida(SunflowerList sunflowerList){
	int pos;
	this.resistencia --;
	if (resistencia == 0) {
		pos = sunflowerList.buscarPlanta(this.col , this.row);
		sunflowerList.plantaMuerta(pos);
	}
}

}
