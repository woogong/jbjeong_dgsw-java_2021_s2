package kr.hs.dgsw.javaClass.lab.patterns.decorator;

public class BorderWindow extends WindowDecorator {

	public BorderWindow(Lcd window) {
		super(window);
	}
	
	@Override
	public void draw() {
		System.out.println("add Border");
		super.draw();
	}
}
