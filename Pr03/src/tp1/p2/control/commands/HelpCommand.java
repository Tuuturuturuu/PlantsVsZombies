package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class HelpCommand extends Command {

	protected String getName() {
		return Messages.COMMAND_HELP_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_HELP_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_HELP_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_HELP_HELP;
	}

	public ExecutionResult execute(GameWorld game) {
		StringBuilder buffer = new StringBuilder(Messages.HELP_AVAILABLE_COMMANDS);
		buffer.append(Messages.LINE_SEPARATOR);
		for (Command command : Command.getAvailableCommands()) {
			/* @formatter:off */
			buffer.append(command.getDetails());
			buffer.append(Messages.HELP_DETAILS_COMMAND_HELP_SEPARATOR);
			buffer.append(command.getHelp());
			buffer.append(Messages.LINE_SEPARATOR);
			/* @formatter:on */
		}

		System.out.println(buffer.toString());

		return new ExecutionResult(false);
	}

}
