package kr.hs.dgsw.javaClass.thread;

public class Calculater {
	
	private int sum = 0;
	
	
	public void add(int value) {
		// 아주 많은 일을 한다
		
		synchronized (this) {
			int a = sum + value;
			sum = a;
			
		}
		
		// 무지 많은 일을 한다.
	}
	
	
	public int getSum() {
		return sum;
	}
}
