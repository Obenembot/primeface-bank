package za.co.pvi_bank.exceptions.invalidAmount;

public class InvalidAmountException extends Exception {

	private static final String MESSAGE = "Invalid Amount";
	public InvalidAmountException() {
		super(MESSAGE);
		// TODO Auto-generated constructor stub
	}

	public InvalidAmountException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}


}
