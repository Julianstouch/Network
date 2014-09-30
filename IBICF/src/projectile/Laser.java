package projectile;

import ibicf.Coord;

public class Laser extends Projectile {
	
	public Laser(int departX, int departY, int pangle, boolean pisVise, Integer pvitDeplacement,int pdegats,boolean pisAmis,Coord vise){
		super(3,departX-81, departY-40, pangle, pisVise, pvitDeplacement, pdegats ,pisAmis,vise);
		// image du lase  163/163, pour le centrer, il faut prend x-81 (163/2) et y-20
	}

}
