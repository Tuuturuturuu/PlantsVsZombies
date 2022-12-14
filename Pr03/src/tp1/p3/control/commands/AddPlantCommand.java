package tp1.p3.control.commands;

import static tp1.p3.view.Messages.error;

import tp1.p3.control.Command;
import tp1.p3.control.exceptions.CommandExecuteException;
import tp1.p3.control.exceptions.CommandParseException;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.logic.GameWorld;
import tp1.p3.logic.gameobjects.PlantFactory;
import tp1.p3.logic.gameobjects.Planta;
import tp1.p3.view.Messages;

public class AddPlantCommand extends Command implements Cloneable {

	private int col;

	private int row;

	private String plantName;

	private boolean consumeCoins;

	public AddPlantCommand() {
		this(false);
	}

	public AddPlantCommand(boolean consumeCoins) {
		this.consumeCoins = consumeCoins;
	}

	protected String getName() {
		return Messages.COMMAND_ADD_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_ADD_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_ADD_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_ADD_HELP;
	}

	public boolean execute(GameWorld game) throws GameException {

		if (col < GameWorld.NUM_COLS && row < GameWorld.NUM_ROWS ) {
			game.checkValidPlantPosition(col, row);
			Planta plant = PlantFactory.spawnPlant(this.plantName, game, col, row);

			game.tryToBuy(plant.getCoste());
			game.addItem(plant);
			game.update();
			return true;

		} else {
			throw new CommandExecuteException(Messages.INVALID_POSITION);
		}
	} 

	public Command create(String[] parameters) throws GameException {
		if (parameters.length == 4) {
			plantName = parameters[1];
			try {
				col = Integer.parseInt(parameters[2]);
				row = Integer.parseInt(parameters[3]);
			} catch (NumberFormatException ex) {

				throw new CommandParseException(Messages.INVALID_POSITION.formatted(parameters[2], parameters[3]), ex);

			}
			return this;
		} else if (parameters.length < 4) {
			throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
		} else {
			throw new CommandParseException(Messages.INVALID_COMMAND);
		}
	}

}
