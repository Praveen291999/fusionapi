package fusionapi.Exception;

public class FusionApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 891636821375991904L;

	// Constructor with a custom message
	public FusionApiException(String message) {
		super(message);
	}

	// Constructor with a custom message and a cause (another throwable)
	public FusionApiException(String message, Throwable cause) {
		super(message, cause);
	}

	// Constructor that just passes a cause (another throwable)
	public FusionApiException(Throwable cause) {
		super(cause);
	}

}
