package kr.hs.dgsw.javaClass.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class BufferStudy {

	public static void main(String[] args) {
		try {
			studyBuffer();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void studyBuffer() throws Exception {
		
		ByteBuffer buffer1 = ByteBuffer.allocate(10);
		
		byte[] bytes = new byte[] {1, 2, 3, 4, 5};
		ByteBuffer buffer2 = ByteBuffer.wrap(bytes);
		
		printStatus(buffer1, "buffer1 생성");
		printStatus(buffer2, "buffer2 생성");
		
		// buffer1에 추가한다.
		buffer1.put((byte)55);
		buffer1.put((byte)-12);
		printStatus(buffer1, "buffer1에 2byte 추가");
		
		buffer1.put(new byte[] {15, 16, 17, 18});
		printStatus(buffer1, "buffer1에 4byte 추가");

		byte value = buffer2.get();
		System.out.println("읽은 값 : " + value);
		printStatus(buffer2, "buffer2에에서 1byte 읽음");

		byte[] bytes1 = new byte[3];
		buffer2.get(bytes1);
		printStatus(buffer2, "buffer2에에서 3byte 읽음");

		printStatus(buffer1, "buffer1 flip() 수행 전");
		buffer1.flip();
		printStatus(buffer1, "buffer1 flip() 수행 후");
		value = buffer1.get();
		System.out.println("읽은 값 : " + value);
		printStatus(buffer1, "buffer1에에서 1byte 읽음");
		
		printStatus(buffer1, "buffer1 rewind() 수행 전");
		buffer1.rewind();
		printStatus(buffer1, "buffer1 rewind() 수행 전");

		/*
		printStatus(buffer1, "buffer1 clear() 수행 전");
		buffer1.clear();
		printStatus(buffer1, "buffer1 clear() 수행 전");
		*/
		
		buffer1.get(bytes1);
		printStatus(buffer1, "buffer1 상태");
		buffer1.mark();
		
		buffer1.get();
		printStatus(buffer1, "buffer1 reset() 수행 전");
		buffer1.reset();
		printStatus(buffer1, "buffer1 reset() 수행 후");
		
		printStatus(buffer1, "buffer1 compact() 수행 전");
		buffer1.compact();
		printStatus(buffer1, "buffer1 compact() 수행 후");
		buffer1.rewind();
		printStatus(buffer1, "buffer1 rewind() 수행 후");
		value = buffer1.get();
		System.out.println("읽은 값 : " + value);
		
	}
	
	public static void printStatus(Buffer buffer, String note) {
		System.out.println(
				String.format("%s : %d %d %d", 
						note,
						buffer.position(),
						buffer.limit(),
						buffer.capacity()));
	}
	
	
	public static void studyBuffer1() throws Exception {
		System.out.println("Buffer 공부");

		byte[] bytes = new byte[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		
		// 버퍼를 할당한다.
		ByteBuffer buffer1 = ByteBuffer.allocate(100);
		
		// 배열의 값을 buffer에 넣는다.
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		
		// 문자열
		CharBuffer charBuffer = CharBuffer.wrap("Hello world");
		
		
		System.out.println(String.format("buffer 저장 용량 : %d", buffer.capacity()));
		System.out.println(String.format("buffer1 저장 용량 : %d", buffer1.capacity()));
		System.out.println(String.format("charBuffer 저장 용량 : %d", charBuffer.capacity()));
		
		// position, limit, capacity, mark
		System.out.println(String.format("position : %d", buffer1.position()));
		System.out.println(String.format("limit : %d", buffer1.limit()));
		System.out.println(String.format("capacity : %d", buffer1.capacity()));
		//System.out.println(String.format("mark : %d", buffer.mark()));

		System.out.println("buffer1에 2byte을 추가합니다.");
		// buffer1 에 2byte을 저장한다.
		buffer1.put((byte)20);
		buffer1.put((byte)30);

		System.out.println(String.format("position : %d", buffer1.position()));
		System.out.println(String.format("limit : %d", buffer1.limit()));
		System.out.println(String.format("capacity : %d", buffer1.capacity()));

		// 읽어 봅시다.
		System.out.println("flip");
		buffer1.flip();
		System.out.println(String.format("position : %d", buffer1.position()));
		System.out.println(String.format("limit : %d", buffer1.limit()));
		System.out.println(String.format("capacity : %d", buffer1.capacity()));
		
		byte value = buffer1.get();
		System.out.println(String.format("읽음 : %d", value));
		System.out.println(String.format("position : %d", buffer1.position()));
		System.out.println(String.format("limit : %d", buffer1.limit()));
		System.out.println(String.format("capacity : %d", buffer1.capacity()));
		
		// rewind
		// clear
		// compact
		
		System.out.println("------------------------------------------------");
		System.out.println("");
	}

	
	
}
