package kr.hs.dgsw.javaClass.server.chatting.client;

import java.util.Scanner;

public class UserInputByScanner implements UserInterface {

	private Object monitor = new Object();

	private Scanner scanner;
	
	private Thread inputThread;
	
	private String inputLine;
	
	private boolean running = true;
	
	private String quitWord;
	
	
	public UserInputByScanner(String quitWord) {
		this.quitWord = quitWord;
		this.scanner = new Scanner(System.in);
		startThread();
	}
	
	@Override
	public String getUserInput() {

		synchronized (monitor) {
			try {
				monitor.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return inputLine;
	}
	
	@Override
	public void close() {
		System.out.println("close called");
		running = false;
		
		scanner.close();
	}
	
	@Override
	public void showSystemMessage(String message) {
		String str = String.format("[시스템] %s", message);
		
		System.out.println(str);
		
	}
	
	@Override
	public void showUserMessage(String id, String name, String message, boolean silent) {
		String silentWord = silent ? "(귀속말)" : "";
		
		String str = String.format("-%s%s- %s", name, silentWord, message);
		
		System.out.println(str);
	}
	
	private void startThread() {
		UserInputReader userInputReader = new UserInputReader();
		inputThread = new Thread(userInputReader);
		inputThread.setDaemon(true);
		
		inputThread.start();
	}
	
	private class UserInputReader implements Runnable {
		@Override
		public void run() {

			while (running) {
				inputLine = scanner.nextLine();
				
				synchronized (monitor) {
					monitor.notifyAll();
				}
				
				if (inputLine.equals(quitWord)) {
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		try {
			UserInterface userInput = new UserInputByScanner("quit");
			
			while (true) {
				String input = userInput.getUserInput();
				
				if ("quit".equals(input)) {
					userInput.close();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
