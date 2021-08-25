package kr.hs.dgsw.javaClass.thread;

import java.util.List;
import java.util.Random;

public class Producer implements Runnable {
	
	private List<Integer> list;
	
	private Object monitor;

	public Producer(List<Integer> list, Object monitor) {
		this.list = list;
		this.monitor = monitor;
	}

	
	@Override
	public void run() {
		Random random = new Random(System.currentTimeMillis());
		
		long delay = 0;
		
		try {
			while (true) {
				delay = random.nextInt(1700) + 300L;
				Thread.sleep(delay);
				
				synchronized (list) {
					int value = random.nextInt(1000);
					System.out.println("생산 : " + value + "   재고 : " + list.size());
					list.add(value);
				}
				
				synchronized (monitor) {
					monitor.notifyAll();
				}
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
