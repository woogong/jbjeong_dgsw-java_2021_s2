package kr.hs.dgsw.javaClass.server.ftp.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import kr.hs.dgsw.javaClass.server.ftp.MyFtpServer;

public class MyFtpClient {

	private Scanner scanner;
	
	private Socket socket;
	
	private InputStream is;
	
	private OutputStream os;
	
	private Thread listenerThread;
	
	
	public MyFtpClient() {
		scanner = new Scanner(System.in);
	}
	
	public String getCommandLine() {
		return scanner.nextLine();
	}

	
	public void connect(String address, int port) throws Exception {
		socket = new Socket(address, port);
		is = socket.getInputStream();
		os = socket.getOutputStream();
		
		listenerThread = new Thread(new Listener());
		listenerThread.setDaemon(true);
		listenerThread.start();
	}
	
	public void start() {
		String line;
		
		while (true) {
			line = getCommandLine().trim();
			
			if ("quit".equals(line)) {
				disconnect();
				break;
			} else {
				send(line);
			}
			
		}
	}
	
	public void send(String message) {
		try {
			System.out.println("send : " + message);
			os.write(message.getBytes());
			os.write(new byte[] {0x13});
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			if (is != null) {
				is.close();
			}
			
			if (os != null) {
				os.close();
			}
			
			if (socket != null) {
				socket.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private class Listener implements Runnable {
		@Override
		public void run() {
			try {
				byte[] buffer = new byte[1024];
				int length;
				
				while ((length = is.read(buffer)) >= 0) {
					String line = new String(buffer, 0, length);
					System.out.println(line);
				}
				
			} catch (Exception e) {
				
			}
			
			disconnect();
		}
	}
	
	public static void main(String[] args) {
		try {
			MyFtpClient client = new MyFtpClient();
			client.connect("127.0.0.1", MyFtpServer.PORT);
			client.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
