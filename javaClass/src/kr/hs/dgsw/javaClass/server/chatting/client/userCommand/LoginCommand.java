package kr.hs.dgsw.javaClass.server.chatting.client.userCommand;

import kr.hs.dgsw.javaClass.server.chatting.client.ChattingClient;

public class LoginCommand extends UserCommandPrototype {

	private String id;
	
	private String name;
	
	public LoginCommand(String commandLine, ChattingClient chattingClient) {
		super(commandLine, chattingClient);
	}

	@Override
	public void execute() {
		parse();
		
		chattingClient.send("ID", id + name);
	}
	
	private void parse() {
		String[] tokens = commandLine.split(" ");
		if (tokens.length != 3) {
			throw new RuntimeException("Invalid command");
		}
		
		id = tokens[1];
		if (id.length() != 4) {
			throw new RuntimeException("Length of client id must be 4");
		}
		
		name = tokens[2];
	}
	
}
