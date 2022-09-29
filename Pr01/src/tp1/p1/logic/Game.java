package tp1.p1.logic;

import java.util.Random;
import tp1.p1.logic.gameobjects.*;
import tp1.p1.control.Level;

public class Game {

 private Level level;
 private long seed;
 
 private int ciclos;
 private int soles;
 private Random rand;
 
 
 public static final int NUM_ROWS = 4;
 public static final int NUM_COLS = 8;
 
 
 public Game (long seed, Level level) {
	 this.level = level;
	 this.seed = seed;
	 this.ciclos = 0;
	 this.soles = 50;
	 this.rand = new Random(seed);
	 
 }
 
 public String positionToString(int columna, int fila) {   
	 return " ";
 }
 
 
 public void update(ZombieList zombiesList, SunflowerList sunflowersList , PeashooterList peashooterList ) {
	
	this.soles = sunflowersList.update() * 10;
	
 }
 
 
 
 
 
 
 
 
 
 
 
 

}
