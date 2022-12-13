package tp1.p3.control.commands;

import static tp1.p3.view.Messages.error;

import tp1.p3.control.Command;
import tp1.p3.control.exceptions.CommandExecuteException;
import tp1.p3.control.exceptions.CommandParseException;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.logic.GameWorld;
import tp1.p3.logic.gameobjects.PlantFactory;
import tp1.p3.logic.gameobjects.Planta;
import tp1.p3.view.Messages;

public class AddPlantCommand extends Command implements Cloneable {

	private int col;

	private int row;

	private String plantName;

	private boolean consumeCoins;

	public AddPlantCommand() {
		this(false); 
	}

	public AddPlantCommand(boolean consumeCoins) {
		this.consumeCoins = consumeCoins;
	}

	protected String getName() {
		return Messages.COMMAND_ADD_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_ADD_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_ADD_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_ADD_HELP;
	}

	public boolean execute(GameWorld game) throws GameException {
		String error; 
		
		if (col < GameWorld.NUM_COLS && row < GameWorld.NUM_ROWS && !game.isPositionFullOcuped(col, row)) {
			Planta plant = PlantFactory.spawnPlant(this.plantName, game, col, row);
			if (plant == null) {
				error = Messages.INVALID_GAME_OBJECT;
			}
			else {
				if (plant.getCoste() > game.getSuncoins()) {
					consumeCoins = false;
					error = Messages.NOT_ENOUGH_COINS;
				}
				else {
					game.addItem(plant); 
					game.consumeSuns(plant.getCoste());
					game.update();
					return  true;
				}
			}
		}
		else {
			error = Messages.INVALID_POSITION;
		}
		return false;
	}

	public Command create(String[] parameters) throws GameException {
		if (parameters.length == 4) {
			plantName = parameters[1];
			try {
				col = Integer.parseInt(parameters[2]);
				row = Integer.parseInt(parameters[3]);
	        } catch (NumberFormatException ex) {
	        	System.out.println(Messages.error(Messages.INVALID_COMMAND));
	            return null; // throw
	        }
			
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


