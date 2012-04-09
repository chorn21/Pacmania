/** Represents the dots that PacMan eats for points.
 * 
 * @author horn5
 *
 */
public class Point {
	public int x, y;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	// Dictates which points should be drawn as normal points and which should be drawn as power points
	// based on the current level of the game
	public static void paintPoints(Point[][] points, int level) {
		for(int i = 0; i < points.length; i++) {
			for(int j = 0; j < points[0].length; j++){
				if(points[i][j] != null){
					if(level == 1) {
						if((i == 0 || i == 4) && j == 2) points[i][j].powerPaint();
						else points[i][j].paint();
					}
					else if(level == 2) {
						if((i == 0 || i == 6) && (j == 0 || j == 6)) points[i][j].powerPaint();
						else points[i][j].paint();
					}
					else if(level == 3) {
						if((i == 0 || i == 4 || i == 8) && (j == 0 || j == 4 || j == 8)) points[i][j].powerPaint();
						else points[i][j].paint();
					}
				}
			}
		}
	}
	
	// Draws a point
	public void paint(){
		Zen.setColor(255,255,255);
		Zen.fillRect(x-3, y-3, 4, 4);
	}
	
	// Draws a power point
	public void powerPaint() {
		Zen.setColor(255, 255, 255);
		Zen.fillRect(x-3, y-3, 10, 10);
	}
	
	// Returns true if each Point in the given array is null
	public static boolean isNull(Point[][] points){
		for(int i = 0; i < points.length; i++)
			for(int j = 0; j < points[0].length; j++)
				if(points[i][j] != null) return false;
		return true;
	}
	
	// Sets up an array of points based on given lengths for the x and y directions and spacing between them
	public static Point[][] createGrid(int xLength, int yLength, int spacing) {
		Point[][] grid = new Point[xLength][yLength];
		for(int i = 0; i < xLength; i++)
			for(int j = 0; j < yLength; j++)
				grid[i][j] = new Point(i*spacing + (Zen.getZenWidth() - xLength*spacing + spacing)/2, 
									   j*spacing + (Zen.getZenHeight() - yLength*spacing + spacing)/2);
		return grid;
	}
	
	public static Point[][] createNewLevel(Point[][] points) {
		Point[][] nextLevel = new Point[points.length + 2][points[0].length + 2];
		for(int i = 0; i < nextLevel.length; i++)
			for(int j = 0; j < nextLevel[0].length; j++)
				nextLevel[i][j] = new Point(i*40 + (Zen.getZenWidth() - nextLevel.length*40 + 30)/2, j*40 + (Zen.getZenHeight() - nextLevel[0].length*40 + 30)/2);
		return nextLevel;
	}
}
