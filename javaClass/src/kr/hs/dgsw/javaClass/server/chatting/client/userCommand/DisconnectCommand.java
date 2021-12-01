package kr.hs.dgsw.javaClass.server.chatting.client.userCommand;

import kr.hs.dgsw.javaClass.server.chatting.client.ChattingClient;

public class DisconnectCommand extends UserCommandPrototype {

	private String address;
	
	private int port;
	
	public DisconnectCommand(String commandLine, ChattingClient chattingClient) {
		super(commandLine, chattingClient);
	}

	@Override
	public void execute() {
		chattingClient.disconnect();
	}
	
	private void parse() {
		String[] tokens = commandLine.split(" ");
		if (tokens.length != 3) {
			throw new RuntimeException("Invalid command");
		}
		
		address = tokens[1];
		port = Integer.parseInt(tokens[2]);
		
	}
	
}
