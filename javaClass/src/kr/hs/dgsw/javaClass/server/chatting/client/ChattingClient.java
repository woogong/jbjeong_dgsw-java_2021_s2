package kr.hs.dgsw.javaClass.server.chatting.client;

import java.io.UnsupportedEncodingException;

import kr.hs.dgsw.javaClass.server.chatting.client.userCommand.UserCommand;
import kr.hs.dgsw.javaClass.server.chatting.client.userCommand.UserCommandBuilder;
import kr.hs.dgsw.javaClass.server.chatting.client.userCommand.UserCommandBuilderImpl;
import kr.hs.dgsw.javaClass.tcpServer.SessionsCallback;
import kr.hs.dgsw.javaClass.tcpServer.TcpSession;

public class ChattingClient implements SessionsCallback {
	
	private static final String USER_ID = "9997";
	
	private static final String USER_NAME = "글렌리";
	
	private UserInterface userInterface;
	
	private UserCommandBuilder userCommandBuilder;
	
	private TcpClient tcpClient;
	
	public static void main(String[] args) {
		try {
			ChattingClient chattingClient = new ChattingClient();
			
			chattingClient.setUserInput(new UserInputByScanner("quit"));
			
			chattingClient.setUserCommandBuilder(new UserCommandBuilderImpl());
			//chattingClient.start();
			
			chattingClient.startUserCommandReceiving();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startUserCommandReceiving() {
		String line = null;
		while (true) {
			line = userInterface.getUserInput();

			UserCommand command = userCommandBuilder.build(line, this);
			if (command != null) {
				command.execute();
			}
			
			
			if ("quit".equals(line)) {
				break;
			}
		}
		
		if (tcpClient != null) {
			tcpClient.close();
		}
	}
	
	public void connectToServer(String address, int port) {
		try {
			tcpClient = new TcpClient();
			tcpClient.connect(address, port);
			
			tcpClient.setSessionsCallback(this);
			
			userInterface.showSystemMessage(address + " 서버에 접속했습니다.");
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void disconnect() {
		if (tcpClient != null) {
			tcpClient.disconnect();
			
			userInterface.showSystemMessage("서버에 접속이 종료되었습니다.");
		}
	}
	

	@Override
	public void onConnected(TcpSession session) {
		System.out.println("client onconnected");
		
	}

	@Override
	public void onDisconnected(TcpSession session) {
		System.out.println("client ondisconnected");
		
	}

	@Override
	public void onMessageReceived(TcpSession session, byte[] data, int offset, int length) {
		System.out.println("client onMessageReceived  " + length);
		
	}
	
	public void setUserInput(UserInterface userInput) {
		this.userInterface = userInput;
	}
	
	public UserInterface getUserInterface() {
		return userInterface;
	}
	
	public void setUserCommandBuilder(UserCommandBuilder userCommandBuilder) {
		this.userCommandBuilder = userCommandBuilder;
	}
	
	public void send(String head, String payload) {
		try {
			byte[] payloadBytes = payload.getBytes("UTF-8");
			String message = String.format("%s%04d%s", head, payloadBytes.length, payload);
			
			tcpClient.send(message.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			
		}
	}
	
}
