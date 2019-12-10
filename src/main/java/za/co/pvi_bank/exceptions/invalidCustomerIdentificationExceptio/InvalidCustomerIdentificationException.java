package za.co.pvi_bank.exceptions.invalidCustomerIdentificationExceptio;

@SuppressWarnings("serial")
public class InvalidCustomerIdentificationException extends Exception {

	private final static String MESSAGE = "INVAlid CUSTOMER ID";

	public InvalidCustomerIdentificationException(String message) {

		super(message);

	}

	public InvalidCustomerIdentificationException() {
		super(MESSAGE);
		// TODO Auto-generated constructor stub
	}

}
