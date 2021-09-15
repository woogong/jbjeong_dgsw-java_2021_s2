package kr.hs.dgsw.javaClass.tcpServer.dev;

import kr.hs.dgsw.javaClass.tcpServer.SessionsCallback;
import kr.hs.dgsw.javaClass.tcpServer.TcpServer;
import kr.hs.dgsw.javaClass.tcpServer.TcpServerFactory;

public class Executor {

	public static void main(String[] args) {
		try {
			TcpServer server = TcpServerFactory.make(TcpServerFactory.NIO_NONBLOCKING_SERVER);
			
			/* new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(1000L);
						server.stop();
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
			}).start(); */
			
			SessionsCallback sessionsCallback = new SimpleSessionsCallback();
			server.setSessionsCallback(sessionsCallback);
			
			server.start(3000);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
