//UIUC CS125 FALL 2011 MP. File: Example.java, CS125 Project: Pacmania, Version: 2011-10-18T07:44:33-0500.743401000


public class Example {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		while(Zen.isRunning()) {
			int x=Zen.getMouseX();
			int y=Zen.getMouseY();
			Zen.drawImage("ghost_inky.png", x, y);
		}
		// To clear the screen use
		// Zen.setColor(red, green, blue)
		//Zen.fillRect , Zen.getZenWidth
		
		// To use double buffering (and avoid flicker)
		//Zen.flipBuffer()
	}

}
