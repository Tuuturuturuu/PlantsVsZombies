package tp1.p3.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p3.control.exceptions.CommandExecuteException;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public class PlantFactory {

	/* @formatter:off */
	private static final List<Planta> AVAILABLE_PLANTS = Arrays.asList(
		new Sunflower(),
		new Peashooter(),
		new WallNut(),
		new CherryBomb()
	);
	/* @formatter:on */

	public static boolean isValidPlant(String plantName) {
		for (Planta p : AVAILABLE_PLANTS) {
			if (p.matchPlant(plantName)) {
				return true;
			}
		}

		return false;
	}

	public static Planta spawnPlant(String plantName, GameWorld game, int col, int row) throws GameException {
		if (isValidPlant(plantName)){
			for (Planta p : AVAILABLE_PLANTS) {
				if (p.matchPlant(plantName)) {
					return p.create(game, col, row);
				}
			}
		}
		throw new CommandExecuteException(Messages.INVALID_GAME_OBJECT);
	}

	public static Iterable<Planta> getAvailablePlants() {
		return Collections.unmodifiableList(AVAILABLE_PLANTS);
	}

	/*
	 * Avoid creating instances of this class
	 */
	private PlantFactory() {
	}
}
