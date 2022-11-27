package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.logic.gameobjects.Planta;
import tp1.p2.view.Messages;

public class AddPlantCheatCommand extends Command implements Cloneable {
	
	private int col;

	private int row;

	private String plantName;
	
	boolean consumeCoins;

	protected String getName() {
		return Messages.COMMAND_CHEAT_PLANT_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_CHEAT_PLANT_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_CHEAT_PLANT_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_CHEAT_PLANT_HELP;
	}

	public ExecutionResult execute(GameWorld game) {
		
		String error;
		if (col < GameWorld.NUM_COLS && row < GameWorld.NUM_ROWS && !game.isPositionFullOcuped(col, row)) {
			Planta plant = PlantFactory.spawnPlant(this.plantName, game, col, row);
			if (plant == null) {
				error = Messages.INVALID_GAME_OBJECT;
			} else {
				game.addItem(plant);
				return new ExecutionResult(true);
			}
		} else {
			error = Messages.INVALID_POSITION;
		}
		return new ExecutionResult(error);
	}
	
	public Command create(String[] parameters) {
		consumeCoins = false;
		if (parameters.length == 4) {
			plantName = parameters[1];
			col = Integer.parseInt(parameters[2]);
			row = Integer.parseInt(parameters[3]);
			return this;
		}
		else if(parameters.length < 4) { 
			System.out.println(Messages.error(Messages.COMMAND_PARAMETERS_MISSING));
		}
		else {
			System.out.println(Messages.error(Messages.INVALID_COMMAND));
		}
		return null;
	}

}
