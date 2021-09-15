package kr.hs.dgsw.javaClass.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class FileChannelStudy {

	public static void main(String[] args) {
		try {
			//studyWrite();
			//studyRead();
			copy2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void copy() throws Exception {
		Path src = Paths.get("C:\\study\\nio", "크롱.jfif");
		Path target = Paths.get("C:\\study\\nio", "크롱_copy.jfif");

		FileChannel srcChannel =
				FileChannel.open(src, StandardOpenOption.READ);
		
		FileChannel targetChannel = 
				FileChannel.open(target, 
						StandardOpenOption.CREATE,
						StandardOpenOption.WRITE);
		
		ByteBuffer buffer = ByteBuffer.allocateDirect(100);
		
		while (true) {
			int count = srcChannel.read(buffer);
			
			if (count < 0) {
				break;
			}
			
			buffer.flip();
			
			targetChannel.write(buffer);
			
			buffer.clear();
		}
		
		
		targetChannel.close();
		srcChannel.close();
	}
	
	public static void copy2() throws Exception {
		Path src = Paths.get("C:\\study\\nio", "크롱.jfif");
		Path target = Paths.get("C:\\study\\nio", "크롱_copy2.jfif");

		Files.copy(src, target, 
				StandardCopyOption.REPLACE_EXISTING);
	}
	
	public static void studyWrite() throws Exception {
		Path path = Paths.get("C:\\study\\nio\\abcd.txt");
		FileChannel channel = 
				FileChannel.open(path, 
						StandardOpenOption.CREATE,
						StandardOpenOption.WRITE);
		
		String data = "대구소프트웨어고등학교";
		byte[] bytes = data.getBytes("UTF-8");
		ByteBuffer buffer = ByteBuffer.wrap(bytes);

		channel.write(buffer);

		channel.close();
	}
	
	public static void studyRead() throws Exception {
		Path path = Paths.get("C:\\study\\nio\\abcd.txt");
		FileChannel channel = 
				FileChannel.open(path, 
						StandardOpenOption.READ);
		
		ByteBuffer buffer = ByteBuffer.allocate(5);
		byte[] bytes = new byte[5];
		String data = "";
		byte[] all = new byte[0];
		
		while (true) {
			int count = channel.read(buffer);
			
			if (count < 0) {
				break;
			}
			
			buffer.flip();
			buffer.get(bytes, 0, count);
			
			all = concatArray(all, Arrays.copyOf(bytes, count));
			
			//data += new String(bytes, 0, count, "UTF-8");
			
			buffer.clear();
		}
		
		data = new String(all, "UTF-8"); 
		
		channel.close();
		System.out.println("data : " + data);
	}
	
	public static byte[] concatArray(byte[] one, byte[] other) {
		byte[] result = new byte[one.length + other.length];
		System.arraycopy(one, 0, result, 0, one.length);
		System.arraycopy(other, 0, result, one.length, other.length);
		
		return result;
	}
	
	public static void printStatus(Buffer buffer, String note) {
		System.out.println(
				String.format("%s : %d %d %d", 
						note,
						buffer.position(),
						buffer.limit(),
						buffer.capacity()));
	}

	public static void studyWrite1() throws Exception {
		
		Path path = Paths.get("C:\\study\\nio\\dgsw.txt");
		FileChannel channel = 
				FileChannel.open(path, 
						StandardOpenOption.CREATE,
						StandardOpenOption.WRITE);

		String data = "대구소프트웨어고등학교";
		Charset charset = Charset.forName("UTF-8");
		ByteBuffer buffer = charset.encode(data);
		
		int count = channel.write(buffer);
		System.out.println(
				String.format("%s 파일에 %d bytes을 기록하였습니다.", 
						path.getFileName(), count));
		
		channel.close();
	}
	
	public static void studyRead1() throws Exception {
		
		Path path = Paths.get("C:\\study\\nio\\dgsw.txt");
		FileChannel channel = 
				FileChannel.open(path, 
						StandardOpenOption.READ);
		
		ByteBuffer buffer = ByteBuffer.allocate(10);
		Charset charset = Charset.forName("UTF-8");
		
		int count;
		String data = "";
		
		while (true) {
			count = channel.read(buffer);
			
			if (count < 0) {
				break;
			}
			
			buffer.flip();
			
			data += charset.decode(buffer).toString();
			buffer.clear();
		}
		
		channel.close();
		
		System.out.println("읽은 문자열 : " + data);
		
	}
	
	
	
}
