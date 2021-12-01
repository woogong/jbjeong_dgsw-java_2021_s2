package kr.hs.dgsw.javaClass.server.ftp;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NioFilesManager implements FilesManager {

	public static final String DEFAULT_PATH = "C://";
	
	protected Path currentPath;
	
	public NioFilesManager() throws RuntimeException {
		try {
			
			changeDirectory();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public String getCurrentPath() {
		return currentPath.toAbsolutePath().toString();
	}

	@Override
	public String changeDirectory(String directory) {
		Path path = Paths.get(currentPath.toAbsolutePath().toString(), directory);
		
		if (Files.exists(path) && Files.isDirectory(path)) {
			currentPath = path;
			return currentPath.toAbsolutePath().toString();
		} else {
			throw new RuntimeException("Directory not exist - " + directory);
		}
	}

	@Override
	public String changeDirectory() {
		currentPath = Paths.get(DEFAULT_PATH);
		return currentPath.toAbsolutePath().toString();
	}

	@Override
	public String makeDirectory(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String rename(String name, String newName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list(String directory) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		
		try {
			FilesManager manager = new NioFilesManager();
			
			System.out.println(manager.getCurrentPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
