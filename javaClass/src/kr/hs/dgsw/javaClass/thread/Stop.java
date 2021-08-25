package kr.hs.dgsw.javaClass.thread;

public class Stop extends Thread {

	@Override
	public void run() {
		
		while (true) {
			// do something
		}
		
		/*
		try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public static void main(String[] args) {
		Stop t1 = new Stop();
		t1.start();
		System.out.println("스레드를 시작했습니다.");
	
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t1.interrupt();
		System.out.println("스레드를 종료했습니다.");
	}
}
