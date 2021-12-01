package kr.hs.dgsw.javaClass.server.ftp.command;

public interface MyFtpCommand {

	public String getCommandLine();
	
	public boolean execute() throws RuntimeException;
	
	public String getResultMessage();
	
}
