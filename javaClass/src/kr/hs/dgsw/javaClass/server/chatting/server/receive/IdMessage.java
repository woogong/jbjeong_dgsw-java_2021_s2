package kr.hs.dgsw.javaClass.server.chatting.server.receive;

import kr.hs.dgsw.javaClass.server.chatting.server.ChattingServer;
import kr.hs.dgsw.javaClass.server.chatting.server.ClientData;
import kr.hs.dgsw.javaClass.tcpServer.TcpSession;

public class IdMessage extends ReceivedMessage {

	private String id;
	
	private String name;
	
	public IdMessage(ChattingServer server, TcpSession session, String payload) {
		super(server, session, payload);
		parse();
	}
	
	@Override
	public void execute() {
		if (chattingServer.isDuplicatedId(session, id)) {
			
		} else {
			ClientData clientData = new ClientData();
			clientData.setData("id", id);
			clientData.setData("name", name);
			
			chattingServer.setClientData(session, clientData);
		}
	}
	
	private void parse() {
		id = payload.substring(0, 4);
		name = payload.substring(4);
	}
	
	@Override
	public String toString() {
		
		return id + ":" + name;
	}
}
