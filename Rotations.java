
public class Rotations {

	public static int[][] rotateLeft(int[][] source) {
		int width = source.length, height = source[0].length;
		int targWidth = height;
		int targHeight = width;
		int[][] target = new int[targWidth][targHeight];
		for(int targX = 0; targX < height; targX++)
			for(int targY = 0; targY < width; targY++) {
				int srcWidth = width - 1 - targY;
				int srcHeight = targX;
				target[targX][targY] = source[srcWidth][srcHeight];
			}
		return target;
	}
}
