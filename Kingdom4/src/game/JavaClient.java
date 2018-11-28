package game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class JavaClient {

	private static final int port = 1234;
	private static final String server = "localhost";
	// private String message;

	@SuppressWarnings("resource")
	public static List<String> sendMessage(String message)
			throws UnknownHostException, IOException, ClassNotFoundException {

		List<String> response = new ArrayList<String>();

		InetAddress host = InetAddress.getByName(server);

		Socket socket = new Socket(host.getHostName(), port);
		ObjectOutputStream socketOutput = new ObjectOutputStream(socket.getOutputStream());
		socketOutput.writeObject("" + message);
		ObjectInputStream socketInput = new ObjectInputStream(socket.getInputStream());
		response = handleResponse(socketInput);
		System.out.println("Message: " + response.toString());
		// close resources
		socketOutput.close();
		socketInput.close();

		return response;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
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
