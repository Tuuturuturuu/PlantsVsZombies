package tp1.p2.logic.actions;

import tp1.p2.logic.GameWorld;

public class SunsCreate implements GameAction {

	public SunsCreate() {

	}

	public void execute(GameWorld game) {
		game.addSun();
	}

}
