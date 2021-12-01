package kr.hs.dgsw.javaClass.server.chatting.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import kr.hs.dgsw.javaClass.tcpServer.SessionsCallback;
import kr.hs.dgsw.javaClass.tcpServer.TcpSession;

public class TcpClient implements TcpSession {

	private Socket socket;
	
	private InputStream is;
	
	private OutputStream os;
	
	private Thread listnerThread;
	
	private SessionsCallback sessionsCallback;
	
	
	public void connect(String serverAddress, int port) throws Exception {
		socket = new Socket(serverAddress, port);
		
		is = socket.getInputStream();
		os = socket.getOutputStream();
		
		listnerThread = new Thread(new Listener());
		listnerThread.setDaemon(true);
		listnerThread.start();
		
		//sessionsCallback.onConnected(this);
	}
	
	@Override
	public void disconnect() {
		close();
	}
	
	@Override
	public void send(byte[] bytes) {
		send(bytes, 0, bytes.length);
	}

	@Override
	public void send(byte[] bytes, int offset, int length) {
		try {
			os.write(bytes, offset, length);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	private void onReceive(byte[] bytes, int offset, int length) {
		if (sessionsCallback != null) {
			sessionsCallback.onMessageReceived(this, bytes, 0, length);
		}
	}
	
	public void setSessionsCallback(SessionsCallback sessionsCallback) {
		this.sessionsCallback = sessionsCallback;
	}
	
	protected void close() {
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
		} catch (IOException e) {
		} finally {
			if (sessionsCallback != null) {
				sessionsCallback.onDisconnected(this);
			}
		}
	}
	
	private class Listener implements Runnable {
		public void run() {
			
			byte[] buffer = new byte[1024];
			int length;
			
			try {
				while ((length = is.read(buffer)) >= 0) {
					if (length > 0) {
						onReceive(buffer, 0, length);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (sessionsCallback != null) {
				sessionsCallback.onDisconnected(null);
			}
		};
	}
}
