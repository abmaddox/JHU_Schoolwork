/**
 * @author Andrew Maddox
 * @since Mar 8, 2024
 */
package edu.jhuep.maddox.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import edu.jhuep.maddox.thread.ThreadBHC;

/**
 * This class handles the spinning up of new threads when new connections are made to port 20016.
 */
public class ServerBHC {
	private final int port;

	public ServerBHC(int port) {
		this.port = port;
		
		init();
	}

	public void init() {
		
		try(ServerSocket serverSocket = new ServerSocket(this.port)){
			
			while(true) {
				Socket s = serverSocket.accept();
				ThreadBHC thread = new ThreadBHC(s);
				thread.start();
			}
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}

}
