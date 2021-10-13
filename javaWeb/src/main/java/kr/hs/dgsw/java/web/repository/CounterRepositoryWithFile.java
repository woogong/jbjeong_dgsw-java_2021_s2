package kr.hs.dgsw.java.web.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class CounterRepositoryWithFile implements CounterRepository {

	private static final String FILE_PATH = "counter.txt";
	
	private File file;
	
	public CounterRepositoryWithFile() {
		file = new File(FILE_PATH);
		System.out.println("file path : " + file.getAbsolutePath());
	}
	
	@Override
	public void addCount() {
		// 현재 카운터를 읽어온다.
		int count = getCount();
		
		// 카운터를 증가시킨다.
		count++;
		
		// 파일에 쓴다.
		try {
			writeToFile(count);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public int getCount() {
		try {
			String sCount = readFile();
			int count = Integer.parseInt(sCount);
			
			return count;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private String readFile() throws Exception {
		if (file.exists()) {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			reader.close();
			
			return line;
		} else {
			return "0";
		}
	}
	
	private void writeToFile(int count) throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		
		writer.write(count + "");
		
		writer.close();
	}
}
