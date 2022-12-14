package tp1.p3.control;

import static tp1.p3.view.Messages.debug;
import static tp1.p3.view.Messages.error;

import java.util.Scanner;

import tp1.p3.control.exceptions.GameException;
import tp1.p3.control.exceptions.RecordException;
import tp1.p3.logic.Game;
import tp1.p3.view.GamePrinter;
import tp1.p3.view.Messages;

/**
 * Accepts user input and coordinates the game execution logic.
 */
public class Controller {

	private Game game;

	private Scanner scanner;

	private GamePrinter gamePrinter;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		this.gamePrinter = new GamePrinter(game);
	}

	/**
	 * Draw / Paint the game.
	 */
	private void printGame() {
		System.out.println(gamePrinter);
	}

	/**
	 * Prints the final message once the match is finished.
	 */
	public void printEndMessage() {
		System.out.println(gamePrinter.endMessage());
	}

	/**
	 * Show prompt and request command.
	 *
	 * @return the player command as words
	 */
	private String[] prompt() {
		System.out.print(Messages.PROMPT);
		String line = scanner.nextLine();
		String[] words = line.toLowerCase().trim().split("\\s+");

		System.out.println(debug(line));

		return words;
	}

	/**
	 * Runs the game logic.
	 * @throws RecordException 
	 * para que al acabar el juego actualice el record
	 */
	public void run() throws RecordException {
		boolean refreshDisplay = true;
		//Record record = new Record(EASY);
		

		while (!game.isFinished() && !game.isPlayerQuits()) {

			// 1. Draw
			if (refreshDisplay) {
				printGame();
			}
			String[] words = prompt();
			try {
				refreshDisplay = false;
				// 2-4. User action & Game Action & Update
			
			Command command = Command.parse(words);
			refreshDisplay = game.execute(command);
			} catch (GameException e) {
				System.out.println(error(e.getMessage()));
			}
		}
	
		if (refreshDisplay) {
			printGame();

		}
		/*if (game.getScore() > record.getRecord()) {
			record.cargarRecord(game.getScore());
		}*/

		printEndMessage();

	}

}
