package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Zombies;
import tp1.p2.logic.gameobjects.ZombieFactory;
import tp1.p2.view.Messages;

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

	public ExecutionResult execute(GameWorld game) {
		String error;

		if (col < GameWorld.NUM_COLS + 1 && row < GameWorld.NUM_ROWS && !game.isPositionFullOcuped(col, row)) {
			Zombies zombie = ZombieFactory.spawnZombie(this.zombieIdx, game, col, row);
			if (zombie == null) {
				error = Messages.INVALID_GAME_OBJECT;
			} else {
				game.addItem(zombie);
				game.update();
				return new ExecutionResult(true);
			}
		} else {
			error = Messages.INVALID_POSITION;
		}
		return new ExecutionResult(error);
	}

	public Command create(String[] parameters) {
		if (parameters.length == 4) {	
			try {
				zombieIdx = Integer.parseInt(parameters[1]);
				col = Integer.parseInt(parameters[2]);
				row = Integer.parseInt(parameters[3]);
	        } catch (NumberFormatException ex) {
	        	System.out.println(Messages.error(Messages.INVALID_COMMAND));
	            return null;
	        }
			return this;
		} else if (parameters.length < 4) { 
			System.out.println(Messages.error(Messages.COMMAND_PARAMETERS_MISSING));
		} else {
			System.out.println(Messages.error(Messages.INVALID_COMMAND));
		}
		return null;
	}

}
