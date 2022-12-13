package tp1.p3.logic.gameobjects;

import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public class BucketHead extends Zombies{
	
	public static final int BUCKET_HEAD_FRECUENCIA = 4;
	public static final int BUCKET_HEAD_DAÑO = 1;
	public static final int BUCKET_HEAD_RESISTENCIA = 8;

	public BucketHead(int col, int row, GameWorld game) {
		super(col, row, BUCKET_HEAD_FRECUENCIA, BUCKET_HEAD_RESISTENCIA, game, BUCKET_HEAD_DAÑO);
	}

	public BucketHead() {
	}
	
	public String getName() {
		return Messages.BUCKET_HEAD_ZOMBIE_NAME;
	}
	protected String getSymbol() {
		return Messages.BUCKET_HEAD_ZOMBIE_SYMBOL;
	}
	
	protected int getVida() {
		return BUCKET_HEAD_RESISTENCIA;
	}
	protected int getDamage() {
		return BUCKET_HEAD_DAÑO;
	}
	protected int getSpeed() {
		return BUCKET_HEAD_FRECUENCIA;
	}

	
	protected Zombies create(GameWorld game, int col, int row) {
		return new BucketHead(col , row , game);
	}


}
