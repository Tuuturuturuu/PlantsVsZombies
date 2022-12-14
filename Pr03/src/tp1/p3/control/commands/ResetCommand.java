package tp1.p3.control.commands;

import static tp1.p3.view.Messages.error;

import tp1.p3.control.Command;
import tp1.p3.control.exceptions.CommandParseException;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.control.Level;
import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;
import tp1.utils.StringUtils;

public class ResetCommand extends Command {

	private Level level;

	private long seed;

	public ResetCommand() {
	}

	public ResetCommand(Level level, long seed) {
		this.level = level;
		this.seed = seed;
	}

	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}

	public boolean execute(GameWorld game) throws GameException {
		if (seed == -1) {
			game.reset();
		} else {
			game.reset(level, seed);
		}
		return true;
	}

	public Command create(String[] parameters) throws GameException {
		if (parameters.length == 3) {
			try {
				seed = Long.parseLong(parameters[2]);
				level = Level.valueOfIgnoreCase(parameters[1]);
			} catch (NumberFormatException ex) {
				throw new CommandParseException(Messages.INVALID_COMMAND);
			}

			return this;
		} else if (parameters.length == 1) {
			seed = -1;
			return this;
		} else if (parameters.length == 2) {
			throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
		} else {
			throw new CommandParseException(Messages.INVALID_COMMAND);
		}
	}

}
