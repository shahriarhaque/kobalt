package experimental.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class P2PService implements NetworkService {

	// networking declarations start here

	private boolean clientReady = false;
	@SuppressWarnings("unused")
	private boolean selfReady = false;

	public int PORT = 8080;
	public InetAddress HOST;

	private Socket clientSock;
	private ServerSocket serverSock;
	private BufferedReader in;
	private PrintWriter out;

	@SuppressWarnings("unused")
	private boolean serverMode = false;
	Thread connThread;
	private static boolean isClosing = false;


	// networking declarations end here


	NetworkComponent game;

	public P2PService(NetworkComponent theGame){
		game = theGame;

		try {
			HOST = InetAddress.getLocalHost();
		} 
		catch (UnknownHostException e) {
			System.err.println("Could not set host to localhost");
			HOST = null;
		}
	}

	public void startSession(){
		new Thread(){
			public void run(){
				out.println("start");
				selfReady = true;
				
				
				if(!clientReady) waitForClient();
				System.out.println("Starting session");
				
				try{

					while(!isClosing){
						out.println(game.getData());
						game.parseData(in.readLine());
					}
				}

				catch (IOException e) {
					e.printStackTrace();
				}
			}

		}.start();
	}


	private void waitForClient(){

		try {
			while(!clientReady){

				String s = in.readLine();
				System.out.println("received: " + s);
				if(s.equals(NetworkConstants.READY_MESSAGE)) {
					clientReady = true;
					System.out.println("client is ready");
				}
			}
		}
		catch (IOException e) {}
	}

	public void connectToHost(String host, int port){

		try {
			HOST = InetAddress.getByName(host); // handles host names like www.google.com and 84.101.146.2
			PORT = port;

			clientSock  = new Socket(HOST,PORT);
			System.out.println("Connected to " + HOST + " at port: " + PORT);
			serverMode = false;
			in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
			out = new PrintWriter(clientSock.getOutputStream(), true);
			connThread = new Thread(){
				public void run(){
					waitForClient();
				}
			};

			connThread.start();

		}

		catch (UnknownHostException e) {
			e.printStackTrace();
			System.err.println("Host: " + host + " not found");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void startServer(int port){
		PORT = port;

		connThread = new Thread(){
			public void run(){
				try {
					serverSock = new ServerSocket(PORT);
					System.out.println("Server started... Listening at port: " + PORT);
					serverMode = true;
					clientSock = serverSock.accept();
					in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
					out = new PrintWriter(clientSock.getOutputStream(), true);

					System.out.println("Connected to " + clientSock.getInetAddress().getHostAddress() 
							+ " at port: " + PORT);
					//waitForClient();
				} 


				catch (IOException e) {
					System.out.println(e.getMessage());
				}


			}
		};

		connThread.start();


	}

	public void closeSession() {
		try {

			if(serverSock != null) serverSock.close();
			if(clientSock != null) clientSock.close();

		} 

		catch (IOException e) {}

	}

}
