package kr.hs.dgsw.javaClass.server.ftp;

public interface FilesManager {

	public String getCurrentPath();
	
	public String changeDirectory(String directory);
	
	public String changeDirectory();
	
	public String makeDirectory(String name);
	
	public String rename(String name, String newName);
	
	public boolean delete(String name);
	
	public String list();
	
	public String list(String directory);
	
}
