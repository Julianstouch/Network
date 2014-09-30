package avion;
import ibicf.CervelleCreateur;
import ibicf.Coord;
import ibicf.ThePanel;
import tir.TemplateTir;
import deplacement.TemplateDeplacement;


public class ChasseurLourd extends Avion {

	Coord ctraj=null;
	
	/** Construction Chasseur lourd
	 * @param w : largeur
	 * @param h : hauteur
	 * @param type
	 * @param largeurFen
	 * @param hauteurFen
	 * @param lVitesse
	 * @param monPan
	 */
	
	public ChasseurLourd(int w, int h, int type) {
		super(w, h, "lourd"+ThePanel.level,1,false,ThePanel.difficulte*ThePanel.level*40,2,false);
		Vitesse=3;
		points=10;
		int position=getXPosn()/CervelleCreateur.espacement;
		
		tempDep=new TemplateDeplacement(position,false,Vitesse);
		setMesPts();
	}
	
	@Override
	public void move(boolean mLeft, boolean mRight, boolean mUp, boolean mDown) {
		if (tempDep.calculCoordMouv()!=null)
			ctraj=tempDep.calculCoordMouv();
			setAngletraj(-ctraj.getAngle());
			rotate(getAngletraj());
			templTir=new TemplateTir(2,this);
			setStep(ctraj.getXcoord(),ctraj.getYcoord(),false);
	}
	
}
