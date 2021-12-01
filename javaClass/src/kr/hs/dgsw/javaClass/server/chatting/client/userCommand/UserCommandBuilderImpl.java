package kr.hs.dgsw.javaClass.server.chatting.client.userCommand;

import kr.hs.dgsw.javaClass.server.chatting.client.ChattingClient;

public class UserCommandBuilderImpl implements UserCommandBuilder {

	@Override
	public UserCommand build(String commandLine, ChattingClient chattingClient) {
		if (commandLine == null) {
			throw new NullPointerException("commandLine is null");
		}
		
		UserCommand command = null;
		
		if (commandLine.startsWith("connect ")) {
			command = new ConnectCommand(commandLine, chattingClient);
		} 
		else if (commandLine.startsWith("quit ")) {
			command = new DisconnectCommand(commandLine, chattingClient);
		}
		else if (commandLine.startsWith("login ")) {
			command = new LoginCommand(commandLine, chattingClient);
		}
		
		return command;
	}
	
}
