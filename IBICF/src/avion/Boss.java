package avion;
import bosses.BossConfig;
import ibicf.Coord;
import ibicf.ThePanel;
import deplacement.TemplateDeplacement;


public class Boss extends Avion {
	Integer angletraj=null;
	Coord ctraj=null;
	
	public Boss(int w, int h, BossConfig bossConfig)  {
			
		super(w, h, bossConfig.name,bossConfig.vitesse,false,bossConfig.pv,bossConfig.numero,true);
		
		isBoss=true;
		
		if (!bossConfig.isBossFin)
			{
				isBoss=false;
				isMiniBoss=true;
				setTaille((width/8)*6, (height/8)*6);
			}
		
		Coord coordBoss=new Coord(getXPosn(),getYPosn());
		tempDep=new TemplateDeplacement(true,coordBoss);
		setMesPts();
		
		points=215;
		Vitesse=2;
	}
	
	public void move(boolean mLeft, boolean mRight, boolean mUp, boolean mDown) {
		ctraj=tempDep.calculCoordMouv();
		if (ctraj!=null)
		{
			setStep(ctraj.getXcoord(),ctraj.getYcoord(),false);
			
		}
		else
		{	
			Coord coordBoss=new Coord(getXPosn(),getYPosn());
			
			tempDep=new TemplateDeplacement(true,coordBoss);
		}
	}	
}
