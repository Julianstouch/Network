package projectile;

import ibicf.Coord;

public class Missile extends Projectile {
	
	public Missile(int departX, int departY, int pangle, boolean pisVise, Integer pvitDeplacement,int pdegats,boolean pisAmis,Coord vise){
		super(2,departX-22,departY,pangle,pisVise,pvitDeplacement,pdegats,pisAmis,vise);
		// image 45/45 > x-22   y-0
	}
	
}
