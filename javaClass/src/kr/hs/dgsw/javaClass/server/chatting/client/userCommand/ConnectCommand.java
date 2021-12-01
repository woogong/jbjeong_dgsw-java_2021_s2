package kr.hs.dgsw.javaClass.server.chatting.client.userCommand;

import kr.hs.dgsw.javaClass.server.chatting.client.ChattingClient;

public class ConnectCommand extends UserCommandPrototype {

	private String address;
	
	private int port;
	
	public ConnectCommand(String commandLine, ChattingClient chattingClient) {
		super(commandLine, chattingClient);
	}

	@Override
	public void execute() {
		parse();
		
		chattingClient.connectToServer(address, port);
		
		
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
