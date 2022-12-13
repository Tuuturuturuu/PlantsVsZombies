package tp1.p3.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p3.logic.GameWorld;

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

	public static Planta spawnPlant(String plantName, GameWorld game, int col, int row) {
		if (isValidPlant(plantName)){
			for (Planta p : AVAILABLE_PLANTS) {
				if (p.matchPlant(plantName)) {
					return p.create(game, col, row);
				}
			}
		}
		return null;
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
