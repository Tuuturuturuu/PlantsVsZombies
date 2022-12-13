package tp1.p3.control.commands;

import tp1.p3.control.Command;
import tp1.p3.control.ExecutionResult;
import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public class NoneCommand extends Command {

	public NoneCommand() {
		// default command
		super(true);
	}

	protected String getName() {
		return Messages.COMMAND_NONE_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_NONE_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_NONE_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_NONE_HELP;
	}

	public ExecutionResult execute(GameWorld game) {
		game.update();
		return new ExecutionResult(true);
	}

}
