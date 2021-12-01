package kr.hs.dgsw.javaClass.server.chatting.client.packet;

public interface PacketBuilder {

	/**
	 * 주어진 사용자 입력을 이용해서 네트워크를 통해 전송할 packet을 만든다.
	 * 
	 * @param commandLine 사용자 입력 값
	 * @param bytes 전송할 패킷이 저장될 byte 배열
	 * @return 유효한 데이터의 길이
	 */
	public int buildPacket(String commandLine, byte[] bytes);
	
}
