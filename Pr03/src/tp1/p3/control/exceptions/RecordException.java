package tp1.p3.control.exceptions;

public class RecordException extends GameException{

	public RecordException(String message, Exception e) {
		super(message);
	}

	public RecordException(String string) {
		super(string);
	}

}
