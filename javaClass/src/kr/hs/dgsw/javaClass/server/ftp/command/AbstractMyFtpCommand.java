package kr.hs.dgsw.javaClass.server.ftp.command;

import kr.hs.dgsw.javaClass.server.ftp.FilesManager;

public abstract class AbstractMyFtpCommand implements MyFtpCommand {

	protected String[] tokens;
	
	protected FilesManager filesManager;
	
	protected String resultMessage;
	
	public AbstractMyFtpCommand(String[] tokens, FilesManager filesManager) {
		this.tokens = tokens;
		this.filesManager = filesManager;
	}
	
	@Override
	public String getCommandLine() {
		StringBuilder buffer = new StringBuilder();
		for (String token : tokens) {
			buffer.append(token).append(" ");
		}
		
		return buffer.toString().trim();
	}
	
	@Override
	public String getResultMessage() {
		return resultMessage;
	}

}
