package kr.hs.dgsw.javaClass.server.chatting.client;

public interface UserInterface {

	public String getUserInput();
	
	public void showUserMessage(String id, String name, String message, boolean silent);
	
	public void showSystemMessage(String message);
	
	public void close();
	
}
