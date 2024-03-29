package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

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

	public ExecutionResult execute(GameWorld game) {
		game.playerQuits();
		return new ExecutionResult(false);
	}

}
