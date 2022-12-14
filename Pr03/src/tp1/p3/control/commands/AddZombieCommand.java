package tp1.p3.control.commands;

import static tp1.p3.view.Messages.error;

import tp1.p3.control.Command;
import tp1.p3.control.exceptions.CommandExecuteException;
import tp1.p3.control.exceptions.CommandParseException;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.logic.GameWorld;
import tp1.p3.logic.gameobjects.ZombieFactory;
import tp1.p3.logic.gameobjects.Zombies;
import tp1.p3.view.Messages;

public class AddZombieCommand extends Command {

	private int zombieIdx;

	private int col;

	private int row;

	public AddZombieCommand() {

	}

	private AddZombieCommand(int zombieIdx, int col, int row) {
		this.zombieIdx = zombieIdx;
		this.col = col;
		this.row = row;
	}

	protected String getName() {
		return Messages.COMMAND_ADD_ZOMBIE_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_ADD_ZOMBIE_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_ADD_ZOMBIE_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_ADD_ZOMBIE_HELP;
	}

	public boolean execute(GameWorld game) throws GameException {

		game.checkValidZombiePosition(col, row);
		Zombies zombie = ZombieFactory.spawnZombie(this.zombieIdx, game, col, row);
		game.addItem(zombie);
		game.update();
		return true;

	}

	public Command create(String[] parameters) throws GameException {
		if (parameters.length == 4) {
			try {
				zombieIdx = Integer.parseInt(parameters[1]);
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
