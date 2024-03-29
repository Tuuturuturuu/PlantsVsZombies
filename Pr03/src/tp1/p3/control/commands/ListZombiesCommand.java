package tp1.p3.control.commands;

import tp1.p3.control.Command;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.logic.GameWorld;
import tp1.p3.logic.gameobjects.PlantFactory;
import tp1.p3.logic.gameobjects.Planta;
import tp1.p3.logic.gameobjects.ZombieFactory;
import tp1.p3.logic.gameobjects.Zombies;
import tp1.p3.view.Messages;

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

	public boolean execute(GameWorld game) throws GameException {
		System.out.println(Messages.AVAILABLE_ZOMBIES);
		for (Zombies zombie: ZombieFactory.getAvailableZombies()) {
			System.out.println(zombie.getDescription());
		}

		System.out.println();
		return false;
	}

}
