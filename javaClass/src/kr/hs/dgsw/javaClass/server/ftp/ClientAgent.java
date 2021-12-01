package kr.hs.dgsw.javaClass.server.ftp;

import kr.hs.dgsw.javaClass.server.ftp.command.MyFtpCommand;
import kr.hs.dgsw.javaClass.tcpServer.SessionsCallback;
import kr.hs.dgsw.javaClass.tcpServer.TcpSession;

public class ClientAgent implements SessionsCallback {
	
	private static final int STATE_COMMAND = 1;
	
	private static final int STATE_FILE_TRANSFER = 2;
	
	private int state = STATE_COMMAND;
	
	private CommandParser commandParser = new CommandParser();
	
	private FilesManager filesManager = new NioFilesManager();
	
	private CommandBuilder commandBuilder = new CommandBuilder(filesManager);
	

	@Override
	public void onConnected(TcpSession session) {
		// do nothing
	}

	@Override
	public void onDisconnected(TcpSession session) {
		// do nothing
	}

	@Override
	public void onMessageReceived(TcpSession session, byte[] data, int offset, int length) {
		//System.out.println("received : " + new String(data));

		if (state == STATE_COMMAND) {
			commandParser.addBytes(data, offset, length);
			
			while (commandParser.hasNext()) {
				executeCommand(session, commandParser.getCommand());
			}
		} else if (state == STATE_FILE_TRANSFER) {
			
		}
	}

	private void executeCommand(TcpSession session, String commandString) {
		System.out.println("executeCommand : |" + commandString + "|");
		
		try {
			MyFtpCommand command = commandBuilder.build(commandString);
			if (command == null) {
				session.send("Unknown command".getBytes());
			} else {
				command.execute();
				session.send(command.getResultMessage().getBytes());
			}
		} catch (Exception e) {
			session.send(e.getMessage().getBytes());
		}
	}
	
}
