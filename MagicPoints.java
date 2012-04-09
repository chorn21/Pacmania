
public class MagicPoints extends Point{
	public int x, y;
	
	public MagicPoints(int x, int y) {
		super(x,y);
	}
	
	public void paint() {
		Zen.setColor(255, 255, 255);
		Zen.fillRect(x-3, y-3, 12, 12);
	}

}
