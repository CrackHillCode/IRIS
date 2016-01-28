package in.techutils.tester.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
//import java.util.List;

public class SocketChatServer /* implements Runnable */ {
	// private List<String> connections;
	private static final int IRIS_PORT = 3246;
	//
	// public List<String> getConnections() {
	// return connections;
	// }
	//
	// public void setConnections(List<String> connections) {
	// this.connections = connections;
	// }

	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(IRIS_PORT);
		try {
			while (true) {
				Socket socket = listener.accept();
				try {
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					out.println(new Date().toString());
				} finally {
					socket.close();
				}
			}
		} finally {
			listener.close();
		}
	}

	/*
	 * @Override public void run() {
	 * 
	 * }
	 */
}
