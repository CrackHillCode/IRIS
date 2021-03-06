package in.techutils.tester.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

public class SocketClient {

	public static void main(String[] args) throws IOException {
		String serverAddress = JOptionPane
				.showInputDialog("Enter IP Address of a machine that is\n running the date service on port 3246:");
		Socket s = new Socket(serverAddress, 3246);
		BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String answer = input.readLine();
		JOptionPane.showMessageDialog(null, answer);
		s.close();
		System.exit(0);
	}
}
