package qrcode.error;

public class CustomerNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long customerId;

	public CustomerNotFoundException(long customerId) {
	this.customerId = customerId;
	}

	public long getCustomerId() {
		return customerId;
	}
}
