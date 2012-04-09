
/** ChaserGhost class for PacMan game
 * 
 * @author fry4, horn5
 *
 */

public class ChaserGhost extends Ghost {
	
	public ChaserGhost(int x, int y, String image){
		super(x, y, image); 
		edible = false;
	}
	
	// Moves toward PacMan
	public void move(PacMan pac, int a, int b, int c, int d) {
			if(pac.getX() > x) 
				x+=5;
			else
				x-=5;
			if(pac.getY() > y)
				y+=5;
			else
				y-=5;
	}
	
	// Moves ghost away from PacMan when the ghost is edible
	public void edibleMove(PacMan pac, int lowerX, int upperX, int lowerY, int upperY) {
		if(x > lowerX) {
			if(x < upperX) {
				if(pac.getX() > x) x-=5;
				else x+=5;
			}
			else x-=5;
		}
		else x+=5;
		if(y > lowerY) {
			if(y < upperY) {
				if(pac.getY() > y) y-=5;
				else y+=5;
			}
			else y-=5;
		}
		else y+=5;
	}

	// Toggles edible
	public void toggleEdible(int i) {
		if(edible) imageFile = i == 1 ? "ghost_blinky.png" : "ghost_clyde.png";
		else imageFile = "ghost_edible.png";
		edible = !edible;
	}
}
