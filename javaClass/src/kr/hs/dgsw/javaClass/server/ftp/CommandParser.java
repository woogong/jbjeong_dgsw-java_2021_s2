package kr.hs.dgsw.javaClass.server.ftp;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class CommandParser {

	private static final String COMMAND_DELIMITER = "\r";
	
	private ByteBuffer buffer;
	
	private Queue<String> commandQueue = new ArrayBlockingQueue<String>(100);
	
	private Charset charset;
	
	private String fraction;
	
	
	private byte[] array = new byte[1024];
	
	public CommandParser() {
		buffer = ByteBuffer.allocate(100);
		charset = Charset.forName("UTF-8");
	}
	
	public void addBytes(byte[] bytes) {
		addBytes(bytes, 0, bytes.length);
	}
	
	public void addBytes(byte[] bytes, int offset, int length) {
		byte[] concated = new byte[array.length + length];
		System.arraycopy(array, 0, concated, 0, array.length);
		
		int index = array.length;
		for (int i = offset ; i < (offset + length) ; i++) {
			concated[index] = bytes[i];
			index++;
		}
		
		array = concated;
		
		parse1();
	}
	
	private void parse1() {
		
		
	}
	
	private void print(byte[] bytes, int offset, int length) {
		for (int i = offset ; i < (offset + length) ; i++) {
			System.out.print(String.format("%02x ", bytes[i]));
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		CommandParser parser = new CommandParser();
		
		
	}
	
	private void parse() {
		buffer.flip();
		
		String line = charset.decode(buffer).toString();
		
		buffer.clear();
		
		if (fraction != null && fraction.length() > 0) {
			line = fraction + line;
		}
		
		int index;
		while ((index = line.indexOf(COMMAND_DELIMITER)) >= 0)
		{
			System.out.println("line pre : |" + line + "| " +  line.length());
			System.out.println("index : " + index);
			if (index > 0) {
				String command = line.substring(0, index);
				System.out.println("command : |" + command + "|");
				commandQueue.add(command);
			}
			
			if (line.length() > (index + 1)) {
				line = line.substring(index + 1);
			} else {
				line = "";
			}
			System.out.println("line post : |" + line + "|");
		}
		
		if (line.length() > 0) {
			fraction = line;
		}
	}
	
	public boolean hasNext() {
		return (commandQueue.size() > 0);
	}
	
	public String getCommand() {
		return commandQueue.poll();
	}
	
	public static void main1(String[] args) {
		try {
			/*
			CommandParser parser = new CommandParser();
			
			String sample = "abcd\nhello\nmk";
			parser.addBytes(sample.getBytes());
			
			while (parser.hasNext()) {
				System.out.println(parser.getCommand());
			}
			
			parser.addBytes("dir\nabcd\nstop\\n".getBytes());
			
			while (parser.hasNext()) {
				System.out.println(parser.getCommand());
			}
			*/
			
			byte[] bytes = new byte[] {0x41, 0x42, 0x43, 0x0A};
			String str = new String(bytes);
			System.out.println("|" + str + "|");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
