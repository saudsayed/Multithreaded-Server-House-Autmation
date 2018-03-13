/****************************************************************************************
 * Name			 : Ahmed Sayed
 * Application	 : A multi-threaded (e.g. file-based) web server
 * Program		 : ClientConnector.java
 * Description   : This program sends the client request and finds the File from the root directory of Server
 									and send it in the form of response on the browser
 * Date			 : 12/03/2018
 ***************************************************************************************/
import java.io.*;
import java.text.DateFormat;
import java.util.Date;
import java.net.Socket;

public class ClientConnector extends Thread{

	Socket socket;
	BufferedReader buffreader;
	OutputStreamWriter os;
	PrintWriter printwriter;
	String fileName,responseHeader;

	//Consturctor of ClientConnector
	public ClientConnector(Socket socket) throws Exception{
		this.socket = socket; //Socket instance from Server
		buffreader = new BufferedReader(new InputStreamReader(socket.getInputStream())); //Used to read the input from he file
		os = new OutputStreamWriter(socket.getOutputStream()); //Used to write the conetent
		printwriter = new PrintWriter(os); //Writing the content of file for the respose
	}

	public void run() {
		try {
		String readClientReq = "";


		while(buffreader.ready() || readClientReq.length() == 0) {
			readClientReq += (char) buffreader.read();
		}

		request req = new request(readClientReq); //Request will extract the fileName and stored them in the variable
		response res = new response(); //Response will read the Files and will wrtie back the response
		responseHeader+="HTTP/1.1 200 OK\n"; //Adding HTTP/1.1 keep-alive behavior with Status code 200 OK
		responseHeader+="Content-Type: text/html\n"; //Specifying the type of Response as HTML
		printwriter.println(responseHeader);
    //printwriter.println("Content-Type: text/html");
    printwriter.println("");

		printwriter.print(res.response); //Wrting the Response
		os.flush(); // flushing the stream.
		printwriter.close(); // closes the stream and releases any system resources associated with it
		buffreader.close();
		socket.close(); // Closes the Socket
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	public class response {
	String response="";

	String path = "/Users/ahmedsayed/Public/Personal/MCS/Adobe/Socket/root"; //Absolute path of the Server root folder
	public response() {
		File file = new File(path + fileName);//Request stores the FileName into Global variable fileName
		//System.out.println("Response Filename" + file);
		try{

				FileInputStream fis = new FileInputStream(file); //Sending the File to FileInputStream to start reading

				int end;
				while( true ) {
					end = fis.read(); // read single line from the file
					if (end != -1){ // If not the end of file
						response += (char) end; //Appending the File content to respmnse variable
					} else {
						break; // If we reach the end of file
					}

			}

			//System.out.println("HTML Content"+response);
		}
		catch(FileNotFoundException e){
			responseHeader = responseHeader.replace("200", "404");//File Not Found Status Code
		}
		catch(Exception e) {
			responseHeader = responseHeader.replace("200", "500");//Server Error
		}
	}
}

	public class request {


		public request(String readClientReq) {
			String req[] = readClientReq.split("\n");//Extractng the First line of Request GET /FileName HTTP/1.1

			String reqLine[] = req[0].split(" "); // Now Extractng the Filename from the above Line
			fileName = reqLine[1]; // variable fileName stores Filename, Later on can be used in the response
			//System.out.println("fileName"+fileName);
		}

	}
}
