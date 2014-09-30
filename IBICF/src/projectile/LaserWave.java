package projectile;
import ibicf.Coord;

public class LaserWave extends Projectile {
	
	public LaserWave(int departX, int departY, int pangle, boolean pisVise, Integer pvitDeplacement,int pdegats,boolean pisAmis,Coord vise){
		super(7,departX-20, departY, pangle, pisVise, pvitDeplacement, pdegats ,pisAmis,vise);
		width=width/3;
	}
	
	@Override
	public void moveUp() {
		super.moveUp();
		width+=2;
		locx = locx - 1;
	}
	
}
