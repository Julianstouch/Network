package projectile;

import avion.Avion;
import ibicf.Coord;

public class MissileFrag extends Projectile {
	
	public MissileFrag(int departX, int departY, int pangle, boolean pisVise, Integer pvitDeplacement,int pdegats,boolean pisAmis,Coord vise,Avion lAvion){
		super(5,departX-22,departY,pangle,pisVise,pvitDeplacement,pdegats,pisAmis,vise);
		// image 45x45 > x-22 y-0
	}
}
