package game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Static Class opens Socket to Connect with Server - you send and get messages.
 * Copyright (c) 2018 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 1.0
 *
 */

public class JavaClient {

	private static final int port = 3856;
	private static final String server = "cloud.kleiner-heuler.de";

	@SuppressWarnings("resource")
	/**
	 * Static method to communicate with the server
	 * 
	 * @param message
	 * @return Response from Server
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static List<String> sendMessage(String message)
			throws UnknownHostException, IOException, ClassNotFoundException {

		List<String> response = new ArrayList<String>();
		//InetAddress host = InetAddress.getByName(server);
		int timeout = 2000;
		SocketAddress socketAddress = new InetSocketAddress(server, port);
		
		//Socket socket = new Socket(host.getHostName(), port);
		Socket socket = new Socket();
		socket.connect(socketAddress, timeout);

		ObjectOutputStream socketOutput = new ObjectOutputStream(socket.getOutputStream());
		socketOutput.writeObject("" + message);
		ObjectInputStream socketInput = new ObjectInputStream(socket.getInputStream());
		response = handleResponse(socketInput);
		//System.out.println("Message: " + response.toString());
		// close resources
		socketOutput.close();
		socketInput.close();
		socket.close();
		return response;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	/**
	 * private method to format response as List
	 * 
	 * @param resObject
	 * @return response as ArrayList
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private static List<String> handleResponse(ObjectInputStream resObject) throws ClassNotFoundException, IOException {

		List<String> response = new ArrayList<String>();

		Object o = resObject.readObject();
		if (o instanceof ArrayList<?>) {
			return (ArrayList) o;
			// do something
		} else {
			response.add((String) o);
		}

		return response;
	}
}
