package kr.hs.dgsw.javaClass.tcpServer;

public enum ErrorCode {
	PORT_ALREADY_OCCUPIED("1001", "Port가 이미 사용중입니다."),
	SERVER_SOCKET_FAIL("1002", "서버 소켓 오류 발생"),
	STREAM_CREATION_FAIL("1003", "Input/output stream 생성 실패"),
	MESSAGE_SENDING_FAIL("1004", "메시지 송신 실패"),
	SERVER_SOCKET_CHANNEL_FAIL("1005", "서버 소켓 채널 오류 발생"),
	SERVER_SOCKET_CLOSE_FAIL("2001", "서버 소켓 close 실패"),
	SERVER_SOCKET_CHANNEL_CLOSE_FAIL("2002", "서버 소켓 close 실패"),
	COMMON_ERROR("9999");
	
	
	private String code;
	
	private String message;
	
	private ErrorCode() {
		this("9999");
	}
	
	private ErrorCode(String code) {
		this(code, null);
	}
	
	private ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
