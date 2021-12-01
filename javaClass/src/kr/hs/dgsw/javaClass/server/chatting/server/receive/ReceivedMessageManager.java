package kr.hs.dgsw.javaClass.server.chatting.server.receive;

import kr.hs.dgsw.javaClass.server.chatting.server.ChattingServer;
import kr.hs.dgsw.javaClass.tcpServer.TcpSession;

public interface ReceivedMessageManager {

	public void register(ChattingServer chattingServer, TcpSession session, byte[] bytes, int offset, int length);
	
	public ReceivedMessage getMessage();
	
}
