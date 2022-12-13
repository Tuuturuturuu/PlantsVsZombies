package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.logic.gameobjects.Planta;
import tp1.p2.view.Messages;

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

	
	public ExecutionResult execute(GameWorld game) {
		System.out.println(Messages.AVAILABLE_PLANTS);
		for (Planta plant: PlantFactory.getAvailablePlants()) {
			System.out.println(plant.getDescription());
		}

		System.out.println();

		return new ExecutionResult(false);
	}

}
