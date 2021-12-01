package kr.hs.dgsw.javaClass.server.ftp.command;

import kr.hs.dgsw.javaClass.server.ftp.FilesManager;

public class CommandPwd extends AbstractMyFtpCommand {

	public CommandPwd(String[] tokens, FilesManager filesManager) {
		super(tokens, filesManager);
	}
	
	@Override
	public boolean execute() throws RuntimeException {
		if (tokens.length == 1) {
			resultMessage = filesManager.getCurrentPath();
		} else {
			throw new RuntimeException("Usage : pwd");
		}
		
		return true;
	}
}
