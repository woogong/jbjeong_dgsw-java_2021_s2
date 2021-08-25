package kr.hs.dgsw.javaClass.thread;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerDoer {

	public static void main(String[] args) {
		
		Object monitor = new Object();
		List<Integer> list = new ArrayList<Integer>();
		
		Producer producer = new Producer(list, monitor);
		Consumer consumer1 = new Consumer("1번", list, monitor);
		Consumer consumer2 = new Consumer("2번", list, monitor);
		Consumer consumer3 = new Consumer("3번", list, monitor);
		
		new Thread(producer).start();
		new Thread(consumer1).start();
		new Thread(consumer2).start();
		new Thread(consumer3).start();
		
		
	}
	
}
