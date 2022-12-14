package tp1.p3.control.exceptions;

public class CommandParseException extends GameException {

	public CommandParseException(String message) {
		super(message);
	}
	
	public CommandParseException(String message, Throwable cause) {
		super(message, cause);
	}

}
