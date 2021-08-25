package kr.hs.dgsw.javaClass.thread;

public class SynchronizedStudy extends Thread {

	private Printer printer;
	
	private int value;
	
	public SynchronizedStudy(Printer printer, int value) {
		this.printer = printer;
		this.value = value;
	}
	
	@Override
	public void run() {
		//printer.print(getId());
		
		for (int i = 0 ; i < 10000 ; i++) {
			printer.add(value);
		}
		
		System.out.println(getId() + "  " + printer.count);
	}
	
	
	public static class Printer {
		private int count = 0;
		
		public void print(long id) {
			for (int i = 0 ; i < 10 ; i++) {
				System.out.println(id + "  " +  i);
			}
		}
		
		public void add(int value) {
			this.count += value;
		}
	}
	
	public static void main(String[] args) {
		Printer printer = new Printer();
		
		SynchronizedStudy t1 = new SynchronizedStudy(printer, 1);
		SynchronizedStudy t2 = new SynchronizedStudy(printer, -1);
		
		t1.start();
		t2.start();
	}
	
}
