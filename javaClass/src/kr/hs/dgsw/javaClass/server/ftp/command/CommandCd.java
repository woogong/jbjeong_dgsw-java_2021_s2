package kr.hs.dgsw.javaClass.server.ftp.command;

import kr.hs.dgsw.javaClass.server.ftp.FilesManager;

public class CommandCd extends AbstractMyFtpCommand {

	public CommandCd(String[] tokens, FilesManager filesManager) {
		super(tokens, filesManager);
	}
	
	@Override
	public boolean execute() throws RuntimeException {
		if (tokens.length == 1) {
			filesManager.changeDirectory();
		} else if (tokens.length == 2) {
			filesManager.changeDirectory(tokens[1]);
		} else {
			throw new RuntimeException("Usage : cd {targetDirectory}");
		}
		
		resultMessage = filesManager.getCurrentPath();
		
		return true;
	}
}
