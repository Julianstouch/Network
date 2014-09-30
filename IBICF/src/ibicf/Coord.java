package ibicf;

public class Coord {

	private int xcoord;
	private int ycoord;
	private int angle;
	
	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public Coord(int x,int y) {
		xcoord=x;
		ycoord=y;
	}

	public int getXcoord() {
		return xcoord;
	}
	public int getYcoord() {
		return ycoord;
	}
}
