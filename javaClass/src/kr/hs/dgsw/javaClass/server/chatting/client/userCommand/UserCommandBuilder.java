package kr.hs.dgsw.javaClass.server.chatting.client.userCommand;

import kr.hs.dgsw.javaClass.server.chatting.client.ChattingClient;

public interface UserCommandBuilder {
	public UserCommand build(String commandLine, ChattingClient chattingClient);
}
