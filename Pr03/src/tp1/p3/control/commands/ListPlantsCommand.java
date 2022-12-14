package tp1.p3.control.commands;

import tp1.p3.control.Command;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.logic.GameWorld;
import tp1.p3.logic.gameobjects.PlantFactory;
import tp1.p3.logic.gameobjects.Planta;
import tp1.p3.view.Messages;

public class ListPlantsCommand extends Command {

	protected String getName() {
		return Messages.COMMAND_LIST_NAME;
	}
	
	protected String getShortcut() {
		return Messages.COMMAND_LIST_SHORTCUT;
	}
	
	public String getDetails() {
		return Messages.COMMAND_LIST_DETAILS;
	}
	
	public String getHelp() {
		return Messages.COMMAND_LIST_HELP;
	}

	
	public boolean execute(GameWorld game) throws GameException {
		System.out.println(Messages.AVAILABLE_PLANTS);
		for (Planta plant: PlantFactory.getAvailablePlants()) {
			System.out.println(plant.getDescription());
		}

		System.out.println();

		return false;
	}

}
