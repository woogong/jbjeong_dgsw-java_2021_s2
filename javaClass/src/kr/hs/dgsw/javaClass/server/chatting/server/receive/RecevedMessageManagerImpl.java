package kr.hs.dgsw.javaClass.server.chatting.server.receive;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import kr.hs.dgsw.javaClass.server.chatting.server.ChattingServer;
import kr.hs.dgsw.javaClass.tcpServer.TcpSession;

public class RecevedMessageManagerImpl implements ReceivedMessageManager {

	public String line = "";
	
	public List<ReceivedMessage> messagePool = new ArrayList<ReceivedMessage>();
	
	private byte[] buffer = new byte[0];
	
	
	@Override
	public void register(ChattingServer chattingServer, TcpSession session, byte[] bytes, int offset, int length) {
		concatBytes(bytes, offset, length);
		parse1(chattingServer, session);
	}
	
	private void concatBytes(byte[] bytes, int offset, int length) {
		byte[] newBuffer = new byte[buffer.length + length];

		System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
		System.arraycopy(bytes, offset, newBuffer, buffer.length, length);
		
		buffer = newBuffer;
	}
	
	private void discardBytes(int length) {
		byte[] newBuffer = new byte[buffer.length - length];
		
		System.arraycopy(buffer, length, newBuffer, 0, buffer.length - length);
		buffer = newBuffer;
	}
	
	public ReceivedMessage getMessage() {
		
		System.out.println("getMessage");
		try {
			synchronized (messagePool) {
				messagePool.wait();
				
				if (messagePool.size() > 0) {
					return messagePool.remove(0);
				}
				
				
			}
		} catch (Exception e) {
		}
		
		return null;
	}
	
	private void parse1(ChattingServer chattingServer, TcpSession session) {
		while (true) {
			String head = getHead();
			if (head == null) {
				break;
			}
			
			int length = getLength();
			if (length == -1) {
				break;
			}
			
			String payload = getPayload(length);
			if (payload == null) {
				break;
			}
			
			ReceivedMessage message = ReceivedMessage.build(chattingServer, session, head, payload);
			if (message != null) {
				discardBytes(6 + length);
				synchronized (messagePool) {
					System.out.println("add message");
					messagePool.add(message);
				
					messagePool.notify();
				}
			} else {
				break;
			}
		}
		System.out.println("parse1 exit");
	}
	
	private String getHead() {
		if (buffer.length >= 2) {
			return new String(buffer, 0, 2);
		} else {
			return null;
		}
	}
	
	private int getLength() {
		if (buffer.length >= 6) {
			String str = new String(buffer, 2, 4);
			try {
				return Integer.parseInt(str);
			} catch (NumberFormatException e) {
				return -1;
			}
		} else {
			return -1;
		}
	}
	
	private String getPayload(int length) {
		if (buffer.length >= (6 + length)) {
			try {
				return new String(buffer, 6, length, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		} else {
			return null;
		}
	}
	

	public static void main(String[] args) {
		try {
			ReceivedMessageManager manager = new RecevedMessageManagerImpl();
			
			
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					ReceivedMessage message = manager.getMessage();
					System.out.println(message);
				}
			});

			//thread.setDaemon(true);
			thread.start();

			Thread.sleep(300);
			
			byte[] bytes = "ID0013ABCD정재봉".getBytes("UTF-8");
			manager.register(null, null, bytes, 0, bytes.length);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
