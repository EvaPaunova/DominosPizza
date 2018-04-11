package exceptions;

public class InvalidArgumentsException extends Exception{

	public InvalidArgumentsException() {}

	public InvalidArgumentsException(String message) {
		super(message);
	}

	public InvalidArgumentsException(Throwable cause) {
		super(cause);
	}

	public InvalidArgumentsException(String message, Throwable cause) {
		super(message, cause);
	}
	
}