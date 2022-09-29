package tp1.p1.logic.gameobjects;

public class ZombieList {
 private int contador;
 private Zombie[] zombies;
 
 
 
 public ZombieList(int contador) {
	 this.contador = contador;
	 this.zombies = new Zombie[contador];
 }
 public void addSunflower( int col, int row) {
		Zombie zombie = new Zombie(col, row);
		 zombies[contador] = zombie;              
		 contador++;
}


public boolean isPositionEmpty(int col, int row) {
	boolean ocupado = false;
	int i = 0;
	while (i < contador && ocupado == false) {
		if (!zombies[i].libre(col, row)) {
			ocupado = true;
		}
		i++;
	}
	return ocupado;
}

}
