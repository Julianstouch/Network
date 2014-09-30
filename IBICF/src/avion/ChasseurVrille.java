package avion;

import deplacement.TemplateDeplacement;
import ibicf.CervelleCreateur;
import ibicf.Coord;
import ibicf.ThePanel;


public class ChasseurVrille extends Avion {

	int sens;
	int angle;
	Integer angletraj=null;
	Coord ctraj=null;
	
	public ChasseurVrille(int w, int h,int type) {
		
		super(w, h, "vrille"+ThePanel.level,1,false,ThePanel.difficulte*ThePanel.level*10,1,false);
//		Vitesse=ThePanel.level+1;
//		if (Vitesse>9)Vitesse=9;
		Vitesse=7;
		points=10;
		setMesPts();
	}
	
	@Override
	public void move(boolean mLeft, boolean mRight, boolean mUp, boolean mDown) {
		
		if (tempDep==null)
			{
				setStep(0,Vitesse,false);
				if (getYPosn()>0)
					{
					int position=getXPosn()/CervelleCreateur.espacement;
					tempDep=new TemplateDeplacement(position,false,Vitesse);
					}
			}
		else
		{
			if (tempDep.calculCoordMouv()!=null)
				ctraj=tempDep.calculCoordMouv();
				angletraj=-ctraj.getAngle();
				rotate(angletraj);
				setStep(ctraj.getXcoord(),ctraj.getYcoord(),false);
		}
	}
	
	public static void main(String[] args) {


	}

}
