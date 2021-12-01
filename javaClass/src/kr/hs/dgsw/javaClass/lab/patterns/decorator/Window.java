package kr.hs.dgsw.javaClass.lab.patterns.decorator;

public class Window implements Lcd {

	@Override
	public void draw() {
		System.out.println("Basic window");
	}

}
