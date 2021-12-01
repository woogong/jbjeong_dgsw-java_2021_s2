package kr.hs.dgsw.javaClass.lab.patterns.decorator;

public class WindowExecutor {

	public static void main(String[] args) {
		Lcd window = new Window();
		window.draw();
		
		Lcd windowWithBorder = new BorderWindow(window);
		windowWithBorder.draw();
		
		Lcd windowScrollBarBorder = new HorizontalScrollBarWindow(new BorderWindow(window));
		windowScrollBarBorder.draw();
	}
	
}
