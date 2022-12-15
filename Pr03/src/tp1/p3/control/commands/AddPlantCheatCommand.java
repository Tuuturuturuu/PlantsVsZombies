package tp1.p3.control.commands;

import tp1.p3.control.Command;
import tp1.p3.control.exceptions.CommandExecuteException;
import tp1.p3.control.exceptions.CommandParseException;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.logic.GameWorld;
import tp1.p3.logic.gameobjects.PlantFactory;
import tp1.p3.logic.gameobjects.Planta;
import tp1.p3.view.Messages;

public class AddPlantCheatCommand extends AddPlantCommand {

	

	public AddPlantCheatCommand() {
		this(false);
	}

	public AddPlantCheatCommand(boolean consumeCoins) {
		this.consumeCoins = consumeCoins;
	}

	protected String getName() {
		return Messages.COMMAND_CHEAT_PLANT_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_CHEAT_PLANT_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_CHEAT_PLANT_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_CHEAT_PLANT_HELP;
	}

}
