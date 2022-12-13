package tp1.p3.logic;

import java.util.Random;

import tp1.p3.logic.actions.SunsCreate;
import tp1.p3.logic.gameobjects.Sun;

public class SunsManager {

	private static final int COOLDOWN_RANDOM_SUN = 5;

	private GameWorld game;

	private Random rand;

	private int cooldown;
	

	public SunsManager(GameWorld game, Random rand) {
		this.game = game;
		this.rand = rand;
		this.cooldown = COOLDOWN_RANDOM_SUN;
	}

	public int getCatchedSuns() {
		return Sun.cogidos;
	}

	public int getGeneratedSuns() {
		return Sun.generados; 
	}

	public void update() {
		if (cooldown == 0) {
			game.addAction(new SunsCreate());
			cooldown = COOLDOWN_RANDOM_SUN;
		} else {
			cooldown--;
		}	
	}

	private int getRandomInt(int bound) {
		return this.rand.nextInt(bound);
	}

	public void addSun() {
		int col = getRandomInt(GameWorld.NUM_COLS);
		int row = getRandomInt(GameWorld.NUM_ROWS);
		game.addItem(new Sun(this.game, col, row));
	}
	
	
}
