
/** Game class for PacMan game
 * 
 * @author fry4, horn5
 *
 */

public class Game {
	
	public static void main(String[] args) {
		int width = Zen.getZenWidth();
		int height = Zen.getZenHeight();
		Ghost[] ghosts = {new Ghost(0, 0, "ghost_inky.png"), new ChaserGhost(width, height, "ghost_blinky.png")};
		Point[][] points;
		PacMan pac = new PacMan();
		int score = 0;
		int level = 1;
		boolean lost = false;
		
		Zen.setFont("Impact-60");
		Zen.setColor(255, 255, 255);
		Zen.drawText("U WANT TO PLAY GAME?", 45, 100);
		Zen.setFont("Impact-14");
		Zen.drawText("Y U NO CLICK MOUSE?", 260, 150);
		Zen.waitForClick();
		Zen.drawImage("intro_pic.png", 170, 200);
		Zen.sleep(3000);
		Zen.flipBuffer();
		
		points = Point.createGrid(5, 5, 40);
		
		while(Zen.isRunning() && !lost)	{
			
			int xVal = (width - points.length*40 + 40)/2;
			int yVal = (height - points[0].length*40 + 40)/2;
			
			Zen.setColor(0, 255, 0);
			Zen.drawLine(xVal - 40, yVal - 40, xVal - 40, yVal + points[0].length*40);
			Zen.drawLine(xVal - 40, yVal - 40, xVal + points.length*40, yVal - 40);
			Zen.drawLine(xVal + points.length*40, yVal - 40, xVal + points.length*40, yVal + points[0].length*40);
			Zen.drawLine(xVal - 40, yVal + points[0].length*40, xVal + points.length*40, yVal + points[0].length*40);
			
			// Draw grid
			Point.paintPoints(points, level);
			
			// Draw ghosts
			for(Ghost g : ghosts) g.paint();
			
			// Draw PacMan
			pac.paint();
			
			// Move ghosts
			for(Ghost g : ghosts) g.moveGhost(pac, xVal-40, xVal+points.length*40-30, yVal-40, yVal+points[0].length*40-30);
			
			// Move PacMan
			pac.movePacMan(xVal, yVal, points);
			
			// Check if PacMan is on a dot
			score = pac.isOnPoint(points, ghosts, level, score);
			
			// Check if PacMan is on a ghost
			score = pac.isOnGhost(ghosts, score, level, args);
			
			// If all points have been eaten, either creates a new grid and adds new ghosts or chaser ghosts,
			// or declares victory (based on the current level of game play)
			if(Point.isNull(points)) {
				level++;
				if(level != 4) {
					points = Point.createNewLevel(points);
					ghosts = Ghost.addGhost((level == 2 ? new Ghost(0, 0, "ghost_pinky.png") : new ChaserGhost(width, height, "ghost_clyde.png")), ghosts);
				}
				else { 
					Zen.flipBuffer();
					Zen.setFont("Impact-80");
					Zen.setColor(0, 255, 0);
					Zen.drawText("WIN", 265, 85);
					Zen.drawImage("winner_pic.png", 95, 100);
					Zen.setFont("Impact-18");
					Zen.setColor(255, 255, 255);
					Zen.drawText("CLICK TO PLAY AGAIN", 250, 460);
					Zen.flipBuffer();
					Game.main(args);
					Zen.waitForClick();
				}
			}
			
			// Colors background black and places the score and level indicators in their specified places
			Zen.flipBuffer();
			Zen.setColor(0,0,0);
			Zen.setFont("Helvetica-18");
			Zen.fillRect(0, 0, width, height);
			Zen.sleep(100);
			Zen.setColor(255,255,255);
			Zen.drawText("Score: " + score, 10, 20);
			if(level != 4) Zen.drawText("Level: " + level, width-70, 20);
		}	
	}
}