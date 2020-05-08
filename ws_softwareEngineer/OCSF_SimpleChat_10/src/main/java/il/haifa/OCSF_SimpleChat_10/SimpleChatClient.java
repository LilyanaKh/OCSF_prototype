package il.haifa.OCSF_SimpleChat_10;

import java.io.IOException;
import java.util.logging.Logger;

import il.haifa.OCSF_SimpleChat_10.client.AbstractClient;

public class SimpleChatClient extends AbstractClient {
	private static final Logger LOGGER = Logger.getLogger(SimpleChatClient.class.getName());

	private ChatClientCLI chatClientCLI;

	public SimpleChatClient(String host, int port) {
		super(host, port);
		this.chatClientCLI = new ChatClientCLI(this);
	}

	@Override
	protected void connectionEstablished() {
		// TODO Auto-generated method stub
		super.connectionEstablished();
		LOGGER.info("Connected to server.");

		try {
			chatClientCLI.loop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void handleMessageFromServer(Object msg) throws IOException {
		chatClientCLI.displayMessage(msg);
	}

	@Override
	protected void connectionClosed() {
		// TODO Auto-generated method stub
		super.connectionClosed();
		chatClientCLI.closeConnection();
	}
}
