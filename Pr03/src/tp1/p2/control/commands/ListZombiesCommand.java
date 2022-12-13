package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Zombies;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.logic.gameobjects.Planta;
import tp1.p2.logic.gameobjects.ZombieFactory;
import tp1.p2.view.Messages;

public class ListZombiesCommand extends Command {

	protected String getName() {
		return Messages.COMMAND_LIST_ZOMBIES_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_LIST_ZOMBIES_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_LIST_ZOMBIES_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_LIST_ZOMBIES_HELP;
	}

	public ExecutionResult execute(GameWorld game) {
		System.out.println(Messages.AVAILABLE_ZOMBIES);
		for (Zombies zombie: ZombieFactory.getAvailableZombies()) {
			System.out.println(zombie.getDescription());
		}

		System.out.println();
		return new ExecutionResult(false);
	}

}
