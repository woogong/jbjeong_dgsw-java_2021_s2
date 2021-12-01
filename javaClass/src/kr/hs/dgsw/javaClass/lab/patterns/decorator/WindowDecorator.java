package kr.hs.dgsw.javaClass.lab.patterns.decorator;

public class WindowDecorator implements Lcd {

	private Lcd window;
	
	public WindowDecorator(Lcd lcd) {
		this.window = lcd;
	}
	
	@Override
	public void draw() {
		window.draw();
	}

}
