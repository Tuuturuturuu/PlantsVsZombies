package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.control.Level;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Sun;
import tp1.p2.view.Messages;

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

	
	public ExecutionResult execute(GameWorld game) { 
		boolean cogido = true;
		if (!caughtSunThisCycle) {
			caughtSunThisCycle = game.tryToCatchObject(col, row);
			if (caughtSunThisCycle) {
				while(cogido) {
					cogido = game.tryToCatchObject(col, row);
				}	
			}
			
		}
		else {
			return new ExecutionResult(Messages.error(Messages.SUN_ALREADY_CAUGHT));
		}
		
		return new ExecutionResult(true);
	}

	public Command create(String[] parameters) {
		if (parameters.length == 3) {
			try {
				col = Integer.parseInt(parameters[1]);
				row = Integer.parseInt(parameters[2]);
	        } catch (NumberFormatException ex) {
	        	System.out.println(Messages.error(Messages.INVALID_COMMAND));
	            return null;
	        }
			return this;
		} else if (parameters.length < 3) {
			System.out.println(Messages.error(Messages.COMMAND_PARAMETERS_MISSING));
		} else {
			System.out.println(Messages.error(Messages.INVALID_COMMAND));
		}
		return null;
	}

}
