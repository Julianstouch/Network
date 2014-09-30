package ibicf;
import java.awt.Point;

public class Polygon extends java.awt.Polygon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean intersects(Polygon poly)
	{
		boolean coupe=false;
		Point monpoint=new Point();
		for (int i = 0; i < poly.xpoints.length; i++) {
			monpoint.x=poly.xpoints[i];
			monpoint.y=poly.ypoints[i];
			if (this.contains(monpoint)) coupe=true;
		}
		return coupe;
	}
}
