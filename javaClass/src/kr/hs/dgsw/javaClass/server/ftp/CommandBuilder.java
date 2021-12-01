package kr.hs.dgsw.javaClass.server.ftp;

import kr.hs.dgsw.javaClass.server.ftp.command.CommandCd;
import kr.hs.dgsw.javaClass.server.ftp.command.CommandPwd;
import kr.hs.dgsw.javaClass.server.ftp.command.MyFtpCommand;

public class CommandBuilder {

	private FilesManager filesManager;
	
	public CommandBuilder(FilesManager filesManager) {
		this.filesManager = filesManager;
	}
	
	public MyFtpCommand build(String commandLine) {

		String[] tokens = commandLine.split(" ");
		MyFtpCommand command = null;
		
		if ("cd".equals(tokens[0])) {
			command = new CommandCd(tokens, filesManager);
		} else if ("pwd".equals(tokens[0])) {
			command = new CommandPwd(tokens, filesManager);
		}
		
		
		if (command != null) {
			return command;
		} else {
			throw new RuntimeException("Unknown command");
		}
	}
	
}
