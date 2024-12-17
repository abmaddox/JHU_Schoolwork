/**
 * @author Andrew Maddox
 * @since Feb 29, 2024
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class will represent a client object specialized for the BHC server located on web6.jhuep.com at port 20025. The constructor takes a message to be used as output
 * to the server and the input from the server is collected and stored for access later. Before accessing the output, the value for serverErrors should be checked to ensure
 * no errors occurred during the communication.
 *
 * Example output from assignment - hike_id:begin_month:begin_day:begin_year:duration:numberHikers (e.g: 0:8:7:2024:3:2)
 */
public class ClientBHC {

	private final String hostName;
	private final int port;
	private String serverErrors;
	private String serverOutput;

	public ClientBHC(String message){
		serverOutput = null;
		//mimics error messages from server so error handling can be the same
		serverErrors = "-0.01:";
		hostName  = "web6.jhuep.com";
		port = 20025;
		try (
				Socket socket = new Socket(hostName, port);
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				){
			out.println(message);
			String temp;
			while ((temp = in.readLine()) != null) {
				//Logging
				System.out.println("Message from server: " + temp);
				serverOutput = temp;
			}
		}
		catch(UnknownHostException e) {
			serverErrors = serverErrors + "Unable to find host " + hostName + ",";
		}
		catch (IOException e) {
			serverErrors = serverErrors + "Unable to communicate with host " + hostName + ",";
		}
	}

	public String getServerErrors() {
		return serverErrors;
	}

	//gets the message returned to the socket by the server
	public String getServerOutput() {
		return serverOutput;
	}

}
