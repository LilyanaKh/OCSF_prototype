package il.haifa.OCSF_SimpleChat_10;

import java.io.IOException;// exec:java@client 

import il.haifa.OCSF_SimpleChat_10.database.QuestionInterface;
import il.haifa.OCSF_SimpleChat_10.server.AbstractServer;
import il.haifa.OCSF_SimpleChat_10.server.ConnectionToClient;

public class SimpleChatServer extends AbstractServer {
	private static int flag_action1 = 1;
	private static int flag_action2 = 0;
	private static int flag_action3 = 0;
	private static int flag_action4 = 0;
	private static String question_num;

	public SimpleChatServer(int port) {
		super(port);
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {

		String outputString = "Loading Failed";
		System.out.println("Received Message: " + msg.toString() + " ");
		if (flag_action1 == 1) {
			if (msg.toString().charAt(0) != '1') {
				msg = "Invalid number, please try again (or exit to quit)>";
			} else {
				System.out.println("HELOOOOO_1");
				try {
					System.out.println(outputString);

					outputString = QuestionInterface.getAllQuestions();

					System.out.println("HELOOOOO_3");

				}

				catch (Exception e) {

				}
				System.out.println("HELOOOOO_4");

				msg = "Showing the questions: \n " + outputString
						+ ".\nPlease enter the ID of the question to edit (or exit to quit)> ";
				flag_action1 = 0;
				flag_action2 = 1;
				flag_action3 = 0;
				flag_action4 = 0;
			}
		} else if (flag_action2 == 1) ////// remember the case of loading failed
		{
			question_num = msg.toString();
			msg = "Please edit the question (or exit to quit)>";
			flag_action1 = 0;
			flag_action2 = 0;
			flag_action3 = 1;
			flag_action4 = 0;

			/////// editing
		}

		else if (flag_action3 == 1) {
			msg = "Would you like to save the question ? (or exit to quit)> ";
			flag_action1 = 0;
			flag_action2 = 0;
			flag_action3 = 0;
			flag_action4 = 1;
		} else if (flag_action4 == 1) {
			msg = "Showing the updated question: >, ******QUESTIONNNNNNNNNNN***.\nPlease enter 1 to show the questions again (or exit to quit)>";
			flag_action1 = 1;
			flag_action2 = 0;
			flag_action3 = 0;
			flag_action4 = 0;

		}

		sendToAllClients(msg);
	}

	@Override
	protected synchronized void clientDisconnected(ConnectionToClient client) {
		// TODO Auto-generated method stub

		System.out.println("Client Disconnected.");
		super.clientDisconnected(client);
	}

	@Override
	protected void clientConnected(ConnectionToClient client) {
		super.clientConnected(client);
		System.out.println("Client connected: " + client.getInetAddress());
	}

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out.println("Required argument: <port>");
		} else {
			QuestionInterface.LoadData(args);
			SimpleChatServer server = new SimpleChatServer(Integer.parseInt(args[0]));
			server.listen();
		}
	}
}
