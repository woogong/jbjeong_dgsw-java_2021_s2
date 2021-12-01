package kr.hs.dgsw.javaClass.server.chatting.client.userCommand;

import kr.hs.dgsw.javaClass.server.chatting.client.ChattingClient;

public abstract class UserCommandPrototype implements UserCommand {

	protected String commandLine;
	
	protected ChattingClient chattingClient;
	
	public UserCommandPrototype(String commandLine, ChattingClient chattingClient) {
		this.commandLine = commandLine;
		this.chattingClient = chattingClient;
	}
	
	@Override
	public String getCommandLine() {
		return commandLine;
	}
}
