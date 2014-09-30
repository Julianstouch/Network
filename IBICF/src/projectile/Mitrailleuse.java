package projectile;

import ibicf.Coord;

public class Mitrailleuse extends Projectile{
	
	
	public Mitrailleuse(int departX, int departY, int angle, boolean isVise, Integer vitDeplacement,int pdegats, boolean isAmis, Coord avionViseCoord) {
		super(1,departX-3,departY,angle,isVise,vitDeplacement,pdegats,isAmis,avionViseCoord);
	}
	
}
