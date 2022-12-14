package tp1.p3.control.commands;

import tp1.p3.control.Command;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public class ExitCommand extends Command {

	protected String getName() {
		return Messages.COMMAND_EXIT_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_EXIT_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_EXIT_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_EXIT_HELP;
	}

	public boolean execute(GameWorld game) throws GameException {
		game.playerQuits();
		return false;
	}

}
