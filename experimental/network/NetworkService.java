package experimental.network;

public interface NetworkService {
	
	/**
	 * Starts a network service session.
	 */
	public void startSession();
	
	/**
	 * Shuts down a network service session and closes any open connections. 
	 */
	public void closeSession();

}
