package il.haifa.OCSF_SimpleChat_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ChatClientCLI {
	int flag = 0;

	private SimpleChatClient client;
	private boolean isRunning;
	private static final String SHELL_STRING1 = "Please enter 1 to see the questions (or exit to quit)> ";
	private Thread loopThread;

	public ChatClientCLI(SimpleChatClient client) {
		this.client = client;
		this.isRunning = false;
	}

	public void loop() throws IOException {
		loopThread = new Thread(new Runnable() {

			// @Override
			public void run() {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String message;
				String str1;
				String str3;
				String str4;
				while (client.isConnected()) {
					if (flag == 0) {
						System.out.print(SHELL_STRING1);
					}
					flag = 1;
					try {
						message = reader.readLine();

						if (message.isBlank())
							continue;

						if (message.equalsIgnoreCase("exit")) {
							System.out.println("Closing connection.");
							client.closeConnection();
						} else {
							client.sendToServer(message);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});

		loopThread.start();
		this.isRunning = true;

	}

	public void displayMessage(Object message) throws IOException {
		/*
		 * if (isRunning) { System.out.print("(Interrupted)\n"); }
		 */

		if (isRunning)
			System.out.print(message.toString());
	}

	public void closeConnection() {
		System.out.println("Connection closed.");
		System.exit(0);
	}

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Required arguments: <host> <port>");
		} else {
			String host = args[0];
			int port = Integer.parseInt(args[1]);

			SimpleChatClient chatClient = new SimpleChatClient(host, port);
			chatClient.openConnection();
		}
	}
}
