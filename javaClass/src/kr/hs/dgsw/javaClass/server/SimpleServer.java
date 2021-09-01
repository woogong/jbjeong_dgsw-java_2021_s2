package kr.hs.dgsw.javaClass.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

	private ServerSocket serverSocket;
	
	public static final String SEVER_ADDRESS = "127.0.0.1";
	
	public static final int PORT = 8000;

	
	public void startServer() throws Exception {
		
		serverSocket = new ServerSocket(PORT);
		System.out.println("서버 소켓이 생성되었습니다.");
		
		while (true) {
			System.out.println("클라이언트 접속 기다림");
			Socket socket = serverSocket.accept();
			System.out.println("클라이언트 접속됨 : " + socket.getInetAddress().toString());
			
			startTalking(socket);
		}
	}
	
	public void startTalking(Socket socket) {
		try {
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			byte[] bytes = new byte[4096];
			
			while (true) {
				int length = is.read(bytes);
				
				//String message = new String(bytes, 0, length);
				//System.out.println("클라이언트 메시지 : " + message);
				
				os.write(bytes, 0, length);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			SimpleServer server = new SimpleServer();
			server.startServer();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
