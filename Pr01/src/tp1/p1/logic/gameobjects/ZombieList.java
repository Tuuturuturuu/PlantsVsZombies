package tp1.p1.logic.gameobjects;

public class ZombieList {
 private int contador;
 private Zombie[] zombies;
 
 
 
 public ZombieList(int contador) {
	 this.contador = contador;
	 this.zombies = new Zombie[contador];
 }
}
