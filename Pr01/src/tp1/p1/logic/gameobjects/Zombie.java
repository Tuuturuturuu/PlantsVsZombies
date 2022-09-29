package tp1.p1.logic.gameobjects;

public class Zombie {
	
	private int col;
	private int row;
	private int resistencia;
	private int coste;
	private double velocidad;
	private int dano;

	public Zombie(int col , int row) {
		this.col = col;
		this.row = row;
		this.resistencia = 5;
		this.coste = 20;
		this.velocidad = 0.5;
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
