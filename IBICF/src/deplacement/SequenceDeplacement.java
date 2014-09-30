package deplacement;

import ibicf.Coord;

import java.util.ArrayList;
import java.util.List;

import projectile.Projectile;


public class SequenceDeplacement {
private int tempPhase;
protected int timerTemp=1000;
public int angleTemp=0;
public int vitesse=2;
	
	private int limiteViePhase;
	
	private List<Deplacement> maListeDeplacement = new ArrayList<Deplacement>();
	
	public SequenceDeplacement()
	{
	}
	
	public SequenceDeplacement(int ppv,int lvitesse)
	{
		this.limiteViePhase = ppv;
		this.vitesse=lvitesse;
	}
	
	
	public int getLimiteViePhase() {
		return limiteViePhase;
	}

	public void setLimiteViePhase(int limiteViePhase) {
		this.limiteViePhase = limiteViePhase;
	}


	public int getTempPhase() {
		return tempPhase;
	}

	public void setTempPhase(int tempPhase) {
		this.tempPhase = tempPhase;
	}

	public List<Deplacement> getMaListeDeplacement() {
		return maListeDeplacement;
	}
	public void setQuartDeTourDroite(int pasAngle,int pasTimer){
		int timerretour=this.timerTemp;
		for (int i = 0; i <= 90; i+=pasAngle) {
			timerretour+=pasTimer;
			this.maListeDeplacement.add(new Deplacement(vitesse,timerretour,this.angleTemp+i,false));
		}	
		this.timerTemp=timerretour;
		this.angleTemp+=90;
		
	}
	public void setDemiTourDroite(int pasAngle,int pasTimer){
		int timerretour=this.timerTemp;
		for (int i = 0; i <= 180; i+=pasAngle) {
			timerretour+=pasTimer;
			this.maListeDeplacement.add(new Deplacement(vitesse,timerretour,this.angleTemp+i,false));			
		}
		this.timerTemp=timerretour;
		this.angleTemp+=180;
		
	}
	public void setVirageDroite(int pasAngle,int pasTimer){
		int timerretour=this.timerTemp;
		for (int i = 0; i <= 45; i+=pasAngle) {
			timerretour+=pasTimer;
			this.maListeDeplacement.add(new Deplacement(vitesse,timerretour,this.angleTemp+i,false));
		}
		this.timerTemp=timerretour;
		this.angleTemp+=45;
		
	}
	public void setQuartDeTourGauche(int pasAngle,int pasTimer){
		int timerretour=this.timerTemp;
		for (int i = 0; i <= 90; i+=pasAngle) {
			timerretour+=pasTimer;
			this.maListeDeplacement.add(new Deplacement(vitesse,timerretour,this.angleTemp-i,false));	
		}
		this.timerTemp=timerretour;
		this.angleTemp-=90;
	}
	public void setDemiTourGauche(int pasAngle,int pasTimer){
		int timerretour=this.timerTemp;
		for (int i = 0; i <= 180; i+=pasAngle) {
			timerretour+=pasTimer;
			this.maListeDeplacement.add(new Deplacement(vitesse,timerretour,this.angleTemp-i,false));
		}
		this.timerTemp=timerretour;
		this.angleTemp-=180;
		
	}
	public void setVirageGauche(int pasAngle,int pasTimer){
		int timerretour=this.timerTemp;
		for (int i = 0; i <= 45; i+=pasAngle) {
			timerretour+=pasTimer;
			this.maListeDeplacement.add(new Deplacement(vitesse,timerretour,this.angleTemp-i,false));					
		}
		this.timerTemp=timerretour;
		this.angleTemp-=45;
	}
	public void setToutDroit(int pasTimer){
		int timerretour=this.timerTemp+pasTimer;
		this.maListeDeplacement.add(new Deplacement(vitesse,timerretour,this.angleTemp,false));
		this.timerTemp=timerretour;
		
	}

	public void setAllerDelaAla(Coord coordDepart,Coord coordDestination,int pasTimer){
		Coord coordTemp;
		int angleCoordDestination;
		int timerretour=this.timerTemp+pasTimer;
		coordTemp=Projectile.getCoord(coordDestination.getXcoord()-coordDepart.getXcoord(), coordDestination.getYcoord()-coordDepart.getYcoord(), 3);
		angleCoordDestination=Projectile.getAngle(coordTemp.getXcoord(), -coordTemp.getYcoord());
		this.maListeDeplacement.add(new Deplacement(vitesse,timerretour,angleCoordDestination,false));
		this.timerTemp=timerretour;
	}
	
	
}
