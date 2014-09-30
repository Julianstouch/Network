package projectile;

import avion.Avion;
import ibicf.Coord;
import ibicf.ThePanel;

public class MissLocker extends Projectile {


	public Avion maCible=null;
	private double x,y;
	private double tempDistance;
	private int vitesse=5;
	public int etat=0;
	
	public MissLocker(int departX, int departY, int pangle, boolean pisVise, Integer pvitDeplacement,int pdegats,boolean pisAmis,Coord vise) {
		super(4,departX-22,departY,pangle, pisVise,pvitDeplacement, pdegats ,pisAmis,vise);
		// image = 45x45 >  x-22 et y-0
		
		//monAvion=lAvion;
		trouverCible();
		if (maCible == null) 
			etat = 0; // no target
		else
			etat = 1;// targeting!
		majCible();
	}
	@Override
	public void moveUp() {
		super.moveUp();
			
		if (maCible!=null)
		{
			
			if (!ThePanel.listeEnnemis.contains(maCible))
			{
				maCible = null;
			}
			else
			{	
			// calcul du centre de la cible
			int coordx = maCible.locx + maCible.getWidth()/2 - 10;
			int coordy = maCible.locy + maCible.getHeight()/2 - 10;
			
			//if (getAngle(coordVisee.getXcoord(), coordVisee.getYcoord())>30)
			//{
				coordVisee=getCoord(coordx-getXPosn(),coordy-getYPosn(), vitesse);			
				rotate(getAngle(coordVisee.getXcoord(), coordVisee.getYcoord()));
			}
		}
		else
		{
			if (etat==1)etat = 2;
		}
	}
	
	private void majCible() {
		
		if (maCible!=null)
		{
			coordVisee=getCoord(maCible.getXPosn()-getXPosn(), maCible.getYPosn()-getYPosn(), vitesse);			
			rotate(getAngle(coordVisee.getXcoord(), coordVisee.getYcoord()));
		}
		else
		{
			Coord coordv=new Coord(0,-vitesse);
			coordVisee=coordv;
		}
	}
	
	private void trouverCible() {
		double distance=100000;
		Avion lAvion;
		for (int i = 0; i < ThePanel.listeEnnemis.size(); i++) {
			lAvion=ThePanel.listeEnnemis.get(i);
			if (lAvion!=null)
			{
				x=(double)(getXPosn()-(lAvion.getXPosn()+lAvion.getWidth()/2));
				y=(double)(getYPosn()-lAvion.getYPosn());
				tempDistance=Math.hypot(x,y);
				if (tempDistance<distance && lAvion.getYPosn()<getYPosn() && (lAvion.getYPosn()+lAvion.getHeight()>0))
				{
						distance=tempDistance;
						maCible=lAvion;
				}
			}
		}
	}
		

}