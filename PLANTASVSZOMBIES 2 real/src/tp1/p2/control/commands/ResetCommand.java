package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.control.Level;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;
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

	public ExecutionResult execute(GameWorld game) {
		if (seed == -1) {
			game.reset();
		} else {
			game.reset(level, seed);
		}
		return new ExecutionResult(true);
	}

	public Command create(String[] parameters) {
		if (parameters.length == 3) {
			try {
				seed = Long.parseLong(parameters[2]);
				level = Level.valueOfIgnoreCase(parameters[1]); 
	        } catch (NumberFormatException ex) {
	        	System.out.println(Messages.error(Messages.INVALID_COMMAND));
	            return null;
	        }
			
			return this;
		}
		else if (parameters.length == 1) {
			seed = -1;
			return this;
		} else if (parameters.length == 2) {
			System.out.println(Messages.error(Messages.COMMAND_PARAMETERS_MISSING));
		} else {
			System.out.println(Messages.error(Messages.INVALID_COMMAND));
		}
		return null;
	}

}
