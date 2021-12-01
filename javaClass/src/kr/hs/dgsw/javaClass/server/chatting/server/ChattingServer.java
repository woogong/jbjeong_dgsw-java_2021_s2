package kr.hs.dgsw.javaClass.server.chatting.server;

import java.util.Collection;
import java.util.HashMap;

import kr.hs.dgsw.javaClass.server.chatting.server.receive.ReceivedMessage;
import kr.hs.dgsw.javaClass.server.chatting.server.receive.ReceivedMessageManager;
import kr.hs.dgsw.javaClass.tcpServer.SessionsCallback;
import kr.hs.dgsw.javaClass.tcpServer.TcpServer;
import kr.hs.dgsw.javaClass.tcpServer.TcpServerFactory;
import kr.hs.dgsw.javaClass.tcpServer.TcpSession;

public class ChattingServer implements SessionsCallback {

	public static final int PORT = 1200;
	
	private TcpServer tcpServer;
	
	private TcpSession tcpSession;
	
	private ReceivedMessageManager receivedMessageManager;
	
	private HashMap<TcpSession, ClientData> clientMap = new HashMap<TcpSession, ClientData>();
	
	public static void main(String[] args) {
		try {
			ChattingServer chattingServer = new ChattingServer();
			chattingServer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		tcpServer = TcpServerFactory.make(TcpServerFactory.SOCKET_SERVER);
		tcpServer.setSessionsCallback(this);
		
		tcpServer.start(PORT);
	}
	
	private void processClientMessage() {
		while (true) {
			ReceivedMessage message = receivedMessageManager.getMessage();
			message.execute();
		}
	}

	@Override
	public void onConnected(TcpSession session) {
		//System.out.println("server onconnected");
		tcpSession = session;
	}

	@Override
	public void onDisconnected(TcpSession session) {
		//System.out.println("server ondisconnected");
		
	}

	@Override
	public void onMessageReceived(TcpSession session, byte[] data, int offset, int length) {
		//System.out.println("server onMessageReceived  " + length);
	
		receivedMessageManager.register(this, session, data, offset, length);
		
	}
	
	public void setReceivedMessageManager(ReceivedMessageManager receivedMessageManager) {
		this.receivedMessageManager = receivedMessageManager;
	}
	
	public void setClientData(TcpSession session, ClientData clientData) {
		this.clientMap.put(session, clientData);
		
		// 모두에게 사용자 접속 정보 보냄
	}
	
	public boolean isDuplicatedId(TcpSession session, String id) {
		Collection<ClientData> list = clientMap.values();
		for (ClientData item : list) {
			if (id.equals(item.getData("id"))) {
				return true;
			}
		}
		
		return false;
	}
	
}
