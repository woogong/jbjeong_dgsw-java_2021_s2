package kr.hs.dgsw.javaClass.thread;

import java.util.Scanner;

public class WaitThread extends Thread {

	private Object object;
	
	public WaitThread(Object object) {
		this.object = object;
	}
	
	@Override
	public void run() {

		try {
			System.out.println(getId() + "  나를 깨워 줘.");
			synchronized (object) {
				object.wait();
			}
			System.out.println(getId() + "  아 잘잤다!");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Object object = new Object();
		
		WaitThread t1 = new WaitThread(object);
		t1.start();

		WaitThread t2 = new WaitThread(object);
		t2.start();

		Scanner scanner = new Scanner(System.in);
		
		scanner.nextLine();
		
		synchronized (object) {
			object.notifyAll();
		}
		
		scanner.close();
		
	}
	
}
