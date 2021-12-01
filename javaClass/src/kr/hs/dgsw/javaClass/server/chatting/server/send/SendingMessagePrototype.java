package kr.hs.dgsw.javaClass.server.chatting.server.send;

import java.io.UnsupportedEncodingException;

public abstract class SendingMessagePrototype implements SendingMessage {

	private String head;
	
	private String payload;
	
	public SendingMessagePrototype(String head) {
		this.head = head;
	}
	
	@Override
	public byte[] getPacket() {
		try {
			int length = 0;
			
			if (payload != null) {
				length = payload.getBytes("UTF-8").length;
			} else {
				payload = "";
			}
			
			String str = String.format("%s%04d%s", head, length, payload);
			
			return str.getBytes("UTF-8");
		} catch (Exception e) {
			throw new RuntimeException("Cannot make sending packet", e);
		}
		
		
	}
}
