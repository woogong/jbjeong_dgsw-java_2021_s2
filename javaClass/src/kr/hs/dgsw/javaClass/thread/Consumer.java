package kr.hs.dgsw.javaClass.thread;

import java.util.List;

public class Consumer implements Runnable {
	
	private String name;
	
	private List<Integer> list;
	
	private Object monitor;

	public Consumer(String name, List<Integer> list, Object monitor) {
		this.name = name;
		this.list = list;
		this.monitor = monitor;
	}
	
	@Override
	public void run() {

		boolean flag = false;
		
		try {
			
			while (true) {
				synchronized (monitor) {
					monitor.wait();
				}
				
				synchronized (list) {
					if (list.size() > 0) {
						int value = list.remove(0);
						System.out.println(name + " : " + value);
						flag = true;
					}
					else {
						flag = false;
					}
				}
				
				if (flag) {
					Thread.sleep(3000L);
				}
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
