/**
 * Ghost class for PacMan game.
 * 
 * @author horn5
 */

public class Ghost {
	protected int x, y;
	protected boolean edible = false;
	protected String imageFile;
	
	// Ghost constructor
	public Ghost(int a, int b, String image) {
		x = a;
		y = b;
		imageFile = image;
	}
	
	// Copy constructor; makes a copy of the given ghost
	public Ghost(Ghost other) {
		x = other.x;
		y = other.y;
		imageFile = other.imageFile;
		edible = other.edible;
	}
	
	// Getters
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean isEdible() {
		return edible;
	}
	
	public String getImage() {
		return imageFile;
	}

	// Draw the Ghost
	public void paint()	{
		Zen.drawImage(imageFile,x,y);
	}
	
	// When ghost is eaten, it returns to user-specified location and becomes non-edible again.
	public void getEaten(int a, int b, int i){
		// Make sure that the ghost is actually edible when this method is called.
		if(edible) {
			toggleEdible(i);
			x = a;
			y = b;
		}
	}
	
	// Moves ghost
	public void moveGhost(PacMan pac, int lowerX, int upperX, int lowerY, int upperY) {
		if(this != null) {
			if(isEdible()) 
				edibleMove(pac, lowerX, upperX, lowerY, upperY);
			else move(pac, lowerX, upperX, lowerY, upperY);
		}
	}
	
	// Ghost randomly moves in the x and y directions, remaining within the user-specified bounds
	public void move(PacMan pac, int lowerX, int upperX, int lowerY, int upperY){
			if(x > lowerX) {
				if(x < upperX) {
					if(Math.random() > 0.5) x+=10;
					else x-=10;
				}
				else x-=10;
			}
			// If the ghost is against a wall, it will move away from the wall
			else x+=10;
			if(y > lowerY) {
				if(y < upperY) {
					if(Math.random() > 0.5) y+=10;
					else y-=10;
				}
				else y-=10;
			}
			// If the ghost is against a wall, it will move away from the wall
			else y+=10;
	}
	
	// Toggles the 'edible' variable (specific occurrences dictated in the Game class)
	public void toggleEdible(int i) {
		if(edible) imageFile = (i == 0 ? "ghost_inky.png" : "ghost_pinky.png");
		else imageFile = "ghost_edible.png";
		edible = !edible;
	}
	
	// When the ghost is edible, it moves away from the PacMan while still remaining within the
	// user-specified bounds
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
	
	// Creates and returns a new array of ghosts that is identical to the given array with the given
	// ghost parameter added to the end
	public static Ghost[] addGhost(Ghost g, Ghost[] list) {
		Ghost[] addedGhost = new Ghost[list.length + 1];
		for(int i = 0; i < list.length; i++) addedGhost[i] = list[i];
		addedGhost[addedGhost.length - 1] = g;
		return addedGhost;
	}
}
