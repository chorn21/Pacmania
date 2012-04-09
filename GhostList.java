/**
 * Ghost class for PacMan game.
 * 
 * @author horn5
 */

public class GhostList {
	
	private Ghost[] list = new Ghost[0];
	
	// Gets the ghost at index 'i' of the specified array
	public Ghost getGhost(int i) {
		return list[i];
	}
	
	// Returns the size of the specified array of ghosts
	public int getSize() {
		return list.length;
	}
	
	// Default constructor
	public GhostList() { }
	
	// Copy constructor; performs a deep copy of the given array if the given boolean value is 'true', 
	// otherwise performs a shallow copy
	public GhostList(GhostList other, boolean deepCopy) {
		if(deepCopy) {
			list = new Ghost[other.getSize()];
			for(int i = 0; i < list.length; i++) list[i] = new Ghost(other.list[i]);
		} 
		else list = other.list;
	}
	
	// Adds a ghost to the end of the current list
	public void addGhost(Ghost g) {
		Ghost[] addedGhost = new Ghost[list.length + 1];
		for(int i = 0; i < list.length; i++) 
			addedGhost[i] = list[i];
		addedGhost[addedGhost.length - 1] = g;
		list = addedGhost;
	}
}