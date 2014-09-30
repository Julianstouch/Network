package projectile;

import avion.Avion;
import option.ConstBonus;
import tir.ConstTir;
import ibicf.Coord;
import ibicf.Explosion;
import ibicf.Sprite;
import ibicf.ThePanel;

public class Projectile extends Sprite{

	public int diago=0;
	public boolean isProjAmis=false;
	public Coord pointVisee;
	
	public int degat=10;
	
	protected Coord pointOrigine;
	private int vitesse;
	private boolean isVise;
	public Coord coordVisee;
	
	public Projectile(int ptype, int departX, int departY, int angle, boolean isVise, Integer vitDeplacement,int pdegats ,boolean isAmis, Coord avionViseCoord) 
	{
		super( departX, departY, ConstTir.getImage(ptype, isAmis));
		this.init(departX, departY, angle, isVise, vitDeplacement, pdegats, isAmis, avionViseCoord);
	}

	
	private void init(int pDepartX, int pDepartY, int diag, boolean pisVisee, int pVitesse,int pdegats, boolean isAmis,Coord avionVise)
	{
		pointOrigine=new Coord(pDepartX,pDepartY);
		diago=diag;
		isVise = pisVisee;
		vitesse = pVitesse;
		
		isProjAmis=isAmis;
		//setStep(0,0,false);
		setMesPts();
	    
	    degat = pdegats;
	    
	    if (isVise)
	    {
	    	pointVisee=avionVise;
	    	if (pointVisee!=null)
	    	{
	    		calculCoordTir();
	    		diago = getAngle(coordVisee.getXcoord(),coordVisee.getYcoord());
	    	}
	    }
	    else
	    {
	    	diago =diag;
	    	calculCoordTirAngle();	    	
	    }

	    	
	}
	
	protected void calculCoordTir()
	{
		int x,y;
		x=pointVisee.getXcoord()-pointOrigine.getXcoord();
		y=pointVisee.getYcoord()-pointOrigine.getYcoord();
	
		coordVisee=getCoord( x, y, vitesse);
	}
	
	public void calculCoordTirAngle()
	{
		coordVisee=getCoord(diago, vitesse);
	}
	
	public void modif (Integer pdiag, Integer pdegat, Integer pvitesse, String pimage)
	{
		if (pdiag != null)
			diago = pdiag;
		if (pdegat != null)
			degat = pdegat;
		if (pvitesse != null)
			vitesse = pvitesse;
		if (pimage != null)
			this.setImage(pimage);
	}
		
	protected void maxExplode(Avion monEnnemi) {
		for (int i = 0; i < 10; i++) {
			
			int posxAleatoire=(int)(Math.random()*(monEnnemi.getWidth()));
			int posyAleatoire=(int)(Math.random()*(monEnnemi.getHeight()));
			
			Explosion explo =new Explosion(monEnnemi,monEnnemi.getXPosn()+posxAleatoire,monEnnemi.getYPosn()+posyAleatoire);
			ThePanel.setExplo(explo);
			
		}
	}
	
	public boolean fragmentation() {
		//fragmentation
		if (isProjAmis)
		{
			if (pointOrigine.getYcoord()-getYPosn()>200)
				{
				Projectile projFrag;
				
				//for (int i = 0; i <= 360; i+=30) {
				for (int i = 120; i <= 240; i+=15) {
					projFrag=new Mitrailleuse(this.getXPosn()+this.getWidth()/2,this.getYPosn(),i,false,5,degat,true,null);
					ThePanel.setProj(projFrag);
				}
				return true;
				}
			else
			{
				return false;
			}
		}
		else
		{
			//fragmentation
			if (getYPosn()-pointOrigine.getYcoord()>200)
				{
				Projectile projFrag;
				
				//for (int i = 0; i <= 360; i+=30) {
				for (int i = 300; i <= 360; i+=15) 
				{
					projFrag=new Mitrailleuse(this.getXPosn()+this.getWidth()/2,this.getYPosn(),i,false,5,degat,false,null);
					ThePanel.setProj(projFrag);
				}
				for (int i = 15; i <= 60; i+=15) 
				{
					projFrag=new Mitrailleuse(this.getXPosn()+this.getWidth()/2,this.getYPosn(),i,false,5,degat,false,null);
					ThePanel.setProj(projFrag);
				}
				return true;
				}
			else
			{
				return false;
			}
		}
	}
	
	
	public void moveUp()
	  {	
			rotate(getAngle(coordVisee.getXcoord(), coordVisee.getYcoord()));
			setStepProj(coordVisee.getXcoord(), coordVisee.getYcoord());			
	  }

	public static int getAngle(double leX, double leY)
	 {
		 Double yop = Math.atan2(leX , leY);
		 Double finalangle = Math.toDegrees(yop);
		 int angle = 180 - finalangle.intValue(); 
		 return angle;
	 }
	
	public static Coord getCoord(double leX, double leY, int lePas)
	{
		
		Double angleTan2 = Math.atan2((leX) , (leY));
		Double angleDeg = Math.toDegrees(angleTan2);
		int angleDegok = angleDeg.intValue();
		angleDegok = angleDegok + ConstBonus.aleatoire(-10,10);
		Double angleFinal = Math.toRadians(angleDegok);
		Double yopX;
		Double yopY;
		if (angleDegok<90)
		{
			if (angleDegok<-90)
			{
				yopX = Math.cos(angleFinal-Math.PI/2);
				yopY = -Math.sin(angleFinal-Math.PI/2);
			}
			else
			{
				yopX = Math.sin(angleFinal);
				yopY = Math.cos(angleFinal);
			}
		}
		else
		{
			if (angleDegok<180)
			{
				yopX = Math.cos(angleFinal-Math.PI/2);
				yopY = -Math.sin(angleFinal-Math.PI/2);
			}
			else
			{
				yopX = Math.sin(Math.PI-angleFinal);
				yopY = -Math.cos(Math.PI-angleFinal);
			}
		}
		
		
		Double Xfinal = yopX*lePas;
		Double Yfinal = yopY*lePas;
		
		
		Coord maCoord=new Coord(Xfinal.intValue(),Yfinal.intValue());
		return maCoord;
	}
	public static Coord getCoord(int angle, int lePas)
	{

		//angleDegok = angleDegok + aleatoire(-10,10);
		
		Double angleFinal = Math.toRadians(angle);
		Double yopX;
		Double yopY;
		if (angle<90)
		{
			if (angle<-90)
			{
				yopX = Math.cos(angleFinal-Math.PI/2);
				yopY = -Math.sin(angleFinal-Math.PI/2);
			}
			else
			{
				yopX = Math.sin(angleFinal);
				yopY = Math.cos(angleFinal);
			}
		}
		else
		{
			if (angle<180)
			{
				yopX = Math.cos(angleFinal-Math.PI/2);
				yopY = -Math.sin(angleFinal-Math.PI/2);
			}
			else
			{
				yopX = Math.sin(Math.PI-angleFinal);
				yopY = -Math.cos(Math.PI-angleFinal);
			}
		}
		
		
		Double Xfinal = yopX*lePas;
		Double Yfinal = yopY*lePas;
		
		
		Coord maCoord=new Coord(Xfinal.intValue(),Yfinal.intValue());
		return maCoord;
	}
	
	public int getXimpact(){
		return getXPosn()+getWidth()/2;
	}
	public int getYimpact(){
		return getYPosn();
	}
	  
	public void stayStill()
	  // stop the ant moving
	  { setStep(0, 0,false); 
	  }
	
}
