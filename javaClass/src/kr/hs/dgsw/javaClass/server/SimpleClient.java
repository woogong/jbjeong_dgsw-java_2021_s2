package kr.hs.dgsw.javaClass.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {

	private Socket socket;
	
	private InputStream is;
	
	private OutputStream os;
	
	private Scanner scanner;
	
	private static final String SEVER_ADDRESS = "127.0.0.1";
	
	private static final int PORT = 8000;
	
	
	public void connect() throws IOException {
		socket = new Socket(SEVER_ADDRESS, PORT);
	}

	public void disconnect() throws Exception {
		if (is != null) {
			is.close();
		}
		
		if (os != null) {
			os.close();
		}
		
		if (socket != null) {
			socket.close();
		}
	}

	public void prepareTalking() throws Exception {
		is = socket.getInputStream();
		os = socket.getOutputStream();
	}
	
	public void startTalking() throws Exception {
		
		String message = "안녕하세요.";
		byte[] bytes = message.getBytes();
		
		os.write(bytes);
		os.flush();
	}
	
	public void sendMessage(String message) throws Exception  {
		byte[] bytes = message.getBytes();
		
		os.write(bytes);
	}
	
	public String receiveMessage() throws Exception {
		byte[] buffer = new byte[1024];
		int length = is.read(buffer);
		
		return new String(buffer, 0, length);
	}
	
	public void processUserInput() throws Exception {
		scanner = new Scanner(System.in);
		String message = null;
		
		while (true) {
			message = scanner.nextLine();
			
			if ("quit".equals(message)) {
				break;
			}
			
			sendMessage(message);
			
			String returnMessage = receiveMessage();
			System.out.println("받은 메시지 : " + returnMessage);
		}
		
		scanner.close();
	}
	
	public static void main(String[] args) {
		try {
			SimpleClient client = new SimpleClient();
			client.connect();
			client.prepareTalking();
			client.processUserInput();
			//client.startTalking();
			client.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
