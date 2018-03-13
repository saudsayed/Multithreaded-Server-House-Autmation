/****************************************************************************************
 * Name			 : Ahmed Sayed
 * Application	 : A multi-threaded (e.g. file-based) web server
 * Program		 : Server.java
 * Description   : This program creates the Socket for the Client's incoming request.
 * Date			 : 12/03/2018
 ***************************************************************************************/
import java.net.*;

public class Server {

		ServerSocket serverSocket;

		public static void main(String[] args) throws Exception{
			Server s = new Server(); //Creating the instance of the Socket
			s.openSocket(); //Method Call
		}

		private void openSocket() throws Exception {
			System.out.println("Waiting for the Incoming request at Port: 8080");
			serverSocket = new ServerSocket(8080);
			while(true){
				Socket socket = serverSocket.accept(); //Accepting the Clent Request
				System.out.println("Client Request Accepted");
				ClientConnector cc = new ClientConnector(socket); //Sending the Socket instance to CLient Connector
				System.out.println("Thread started");
				cc.start(); //Executing the run() function of ClientConnector
			}
		}

}
