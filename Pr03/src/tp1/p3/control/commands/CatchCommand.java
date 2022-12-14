package tp1.p3.control.commands;

import static tp1.p3.view.Messages.error;

import tp1.p3.control.Command;
import tp1.p3.control.exceptions.CommandExecuteException;
import tp1.p3.control.exceptions.CommandParseException;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.control.exceptions.NotCatchablePositionException;
import tp1.p3.control.Level;
import tp1.p3.logic.GameWorld;
import tp1.p3.logic.gameobjects.Sun;
import tp1.p3.view.Messages;

public class CatchCommand extends Command {

	private static boolean caughtSunThisCycle = false;

	private int col;

	private int row;

	public CatchCommand() {
		caughtSunThisCycle = false;
	}

	protected void newCycleStarted() {
		caughtSunThisCycle = false;
	}

	private CatchCommand(int col, int row) {
		this.col = col;
		this.row = row;
	}

	protected String getName() {
		return Messages.COMMAND_CATCH_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_CATCH_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_CATCH_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_CATCH_HELP;
	}

	public boolean execute(GameWorld game) throws GameException {
		if (!caughtSunThisCycle) {
			game.tryToCatchObject(col, row);
			caughtSunThisCycle = true;
			// VER COMO HACER PARA QUE COJA TODOS LOS SOLES

		} else {
			throw new CommandExecuteException(Messages.error(Messages.SUN_ALREADY_CAUGHT));
		}

		return true;
	}

	public Command create(String[] parameters) throws GameException {
		if (parameters.length == 3) {
			try {
				col = Integer.parseInt(parameters[1]);
				row = Integer.parseInt(parameters[2]);
			} catch (NumberFormatException ex) {
				throw new CommandParseException(Messages.INVALID_POSITION.formatted(parameters[1], parameters[2]), ex);
			}
			return this;
		} else if (parameters.length < 3) {
			throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
		} else {
			throw new CommandParseException(Messages.INVALID_COMMAND);
		}
	}

}
