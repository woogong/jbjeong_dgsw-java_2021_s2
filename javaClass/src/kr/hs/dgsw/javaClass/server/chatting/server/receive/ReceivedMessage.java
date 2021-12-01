package kr.hs.dgsw.javaClass.server.chatting.server.receive;

import kr.hs.dgsw.javaClass.server.chatting.server.ChattingServer;
import kr.hs.dgsw.javaClass.tcpServer.TcpSession;

public abstract class ReceivedMessage {
	
	protected ChattingServer chattingServer;
	
	protected TcpSession session;
	
	protected String payload;

	public ReceivedMessage(ChattingServer chattingServer, TcpSession session, String payload) {
		this.chattingServer = chattingServer;
		this.session = session;
		this.payload = payload;
	}
	
	public abstract void execute();
	
	
	public static ReceivedMessage build(ChattingServer server, TcpSession session, String head, String payload)
	{
		ReceivedMessage message = null;
		if ("ID".equals(head)) {
			message = new IdMessage(server, session, payload);
		}
		
		return message;
	}
	
}
