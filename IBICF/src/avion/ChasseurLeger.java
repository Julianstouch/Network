package avion;
import ibicf.CervelleCreateur;
import ibicf.Coord;
import ibicf.ThePanel;
import deplacement.TemplateDeplacement;

public class ChasseurLeger extends Avion {

	Integer angletraj=null;
	Coord ctraj=null;
	
	public ChasseurLeger(int w, int h,  int type) {
		
		super(w, h, "leger"+ThePanel.level,1,false,ThePanel.difficulte*ThePanel.level*20,1,false);
		Vitesse=4;
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
}
