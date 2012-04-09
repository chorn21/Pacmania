import java.awt.event.KeyEvent;

/** Pacman class for Pacman game
 * 
 * @author fry4, horn5
 *
 */

public class PacMan {
	private int x, y;
	public String imageFile = "pacman_right.png";

	public PacMan(){
		x = Zen.getZenWidth()/2-17;
		y = Zen.getZenHeight()/2-17;
	}
	
	// Getters
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	// Draw PacMan
	public void paint() {
		
		Zen.drawImage(imageFile, x, y);
	}
	
	// Move PacMan in specified direction
	public void move(int a, int b){
		x += a*10; 
		y += b*10;
	}
	
	// Moves PacMan based on user input while keeping PacMan within the given bounds
	public void movePacMan(int xBound, int yBound, Point[][] points) {
		if(Zen.isVirtualKeyPressed(KeyEvent.VK_UP) && getY() > yBound-32){
			imageFile = "pacman_up.png";
			move(0,-1);
		}
		if(Zen.isVirtualKeyPressed(KeyEvent.VK_LEFT) && getX() > xBound-32){
			imageFile = "pacman_left.png";
			move(-1,0);
		}
		if(Zen.isVirtualKeyPressed(KeyEvent.VK_DOWN) && getY() < yBound + points[0].length*40 - 28){
			imageFile = "pacman_down.png";
			move(0,1);
		}
		if(Zen.isVirtualKeyPressed(KeyEvent.VK_RIGHT) && getX() < xBound + points.length*40 - 28){
			imageFile = "pacman_right.png";
			move(1,0);
		}
	}
	
	// If PacMan is touching an edible ghost, he eats the ghost.  If the ghost is not edible,
	// PacMan is eaten and the game is over
	public int isOnGhost(Ghost[] ghosts, int score, int level, String[] args) {
		for(int i=0; i<ghosts.length; i++){
			if(ghosts[i] != null && onGhost(ghosts[i])){
				if(ghosts[i].isEdible()) {
					ghosts[i].getEaten((int)(Math.random()*1000), (int)(Math.random()*1000), i);
					score += 10;
				}
				else {
					Zen.flipBuffer();
					Zen.setFont("Impact-80");
					Zen.setColor(255, 0, 0);
					Zen.drawText("FAIL", 260, 85);
					Zen.setFont("Impact-24");
					Zen.setColor(255, 255, 255);
					Zen.drawText("Score: " + score, 275, 120);
					if(i % 2 == 0) {
						if(level == 1) Zen.drawImage("level1_ghost.png", 225, 160);
						else if(level == 2) Zen.drawImage("level2_ghost.png", 205, 150);
						else Zen.drawImage("level3_ghost.png", 195, 150);
					}
					else {
						if(level == 1) Zen.drawImage("level1_chaser.png", 210, 160);
						else if(level == 2) Zen.drawImage("level2_chaser.png", 205, 150);
						else Zen.drawImage("level3_chaser.png", 205, 150);
					}
					Zen.setFont("Impact-18");
					Zen.setColor(255, 255, 255);
					Zen.drawText("CLICK TO PLAY AGAIN", 250, 460);
					Zen.flipBuffer();
					Game.main(args);
					Zen.waitForClick();
				}
			}
		}
		return score;
	}
	
	// Returns true if PacMan is touching a ghost
	public boolean onGhost(Ghost g){
		if((Math.abs(x-g.getX()) <= 17.0 && Math.abs(y-g.getY()) <= 17.0))
			return true;
		return false;
	}
	
	// If PacMan is on a power point, the ghosts' 'edible' variable is toggled.  The variable 'score' is
	// incremented by one
	public int isOnPoint(Point[][] points, Ghost[] ghosts, int level, int score) {
		for(int i = 0; i < points.length; i++){
			for(int j = 0; j < points[0].length; j++) {
				if(points[i][j] != null && onPoint(points[i][j])){
					for(int k = 0; k < ghosts.length; k++) {
						if(level == 1) 
							if((i == 0 || i == 4) && j == 2) 
								ghosts[k].toggleEdible(k);
						if(level == 2)
							if((i == 0 || i == 6) && (j == 0 || j == 6))
								ghosts[k].toggleEdible(k);
						if(level == 3)
							if((i == 0 || i == 4 || i == 8) && (j == 0 || j == 4 || j == 8))
								ghosts[k].toggleEdible(k);
					}
					score += 1;
					points[i][j] = null;
				}
			}
		}
		return score;
	}
	
	// Dictates whether or not PacMan is on a point
	public boolean onPoint(Point p){
		if(Math.abs((x+17) - p.x) <= 10 && Math.abs((y+17) - p.y) <= 10 )
			return true;
		return false;
	}
	
	// Forbids PacMan from running out of bounds
	public boolean isInBounds() {
		if(x <= 0 || x >= Zen.getZenWidth() || y <= 0 || y >= Zen.getZenHeight()) return false;
		return true;
	}
	
}
