package kr.hs.dgsw.javaClass.lab.patterns.decorator;

public class HorizontalScrollBarWindow extends WindowDecorator {

	public HorizontalScrollBarWindow(Lcd lcd) {
		super(lcd);
	}

	@Override
	public void draw() {
		System.out.println("add horizontal scroll bar");
		super.draw();
	}
}
