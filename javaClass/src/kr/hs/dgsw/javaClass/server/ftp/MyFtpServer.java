package kr.hs.dgsw.javaClass.server.ftp;

import kr.hs.dgsw.javaClass.tcpServer.TcpServer;
import kr.hs.dgsw.javaClass.tcpServer.TcpServerFactory;

public class MyFtpServer {

	public static final int PORT = 8021;
	
	public static void main(String[] args) {

		TcpServer server = 
				TcpServerFactory.make(TcpServerFactory.NIO_BLOCKING_SERVER);
		
		server.setSessionsCallback(new ClientAgent());
		
		server.start(8021);
		
	}
	
}
