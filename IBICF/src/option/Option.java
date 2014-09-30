package option;
import ibicf.Sprite;
import ibicf.ThePanel;

import java.util.HashMap;

import avion.Avion;


public class Option extends Sprite {

	private int type=1;
	private int sensx=2;
	private int sensy=2;
	private int directionx=1;
	private int directiony=1;
	
	public Option(int x, int y) {
		
		super(x, y,"op1");
		int cptType=0; 
		int droprate=0;
		setMesPts();
		
		int hasardOption  = ConstBonus.aleatoire(0, ConstBonus.currentDropRate);
		
		 for (int i = 0; i < ConstBonus.listeBonusTotal.size(); i++) 
		 {
			 if (ThePanel.level >= ConstBonus.listeBonusTotal.get(i+1).leveldrop)
			 {	 
				 droprate = ConstBonus.listeBonusTotal.get(i+1).droprate;
				 if (hasardOption>=cptType && hasardOption<cptType+droprate) 
					 {
					 	type=i+1;
					 	break;
					 }
				 cptType+=droprate;
			 }
		}
		//type=12;	
		setImage("op"+type);
		
		if ((int)(Math.random()*2)==1){directionx=-1;sensx=1;}
		sensy=1;
		//if ((int)(Math.random()*2)==1){directiony=-1;sensy=1;}
	}
	
public Option(int ltype) {
		
		super(ThePanel.xCentre, ThePanel.yCentre,"op1");
		setMesPts();

		type=ltype;	
		
		setImage("op"+type);
		
		if ((int)(Math.random()*2)==1){directionx=-1;sensx=1;}
		sensy=1;
	}
	
	public void moveup() {
		setStepProj(sensx*directionx,sensy*directiony);
//		angle++;if (angle>360)angle=0;
//		rotate(angle);
	}
	
	public void testPris() {
		
		HashMap<String, Integer> bonusAvion = ThePanel.MonAvion.listDeBonus;
		HashMap<Integer, Bonus> laLB = ConstBonus.listeBonus;
		
		if (ThePanel.MonAvion.getMyPolygon().intersects(this.getMyRectangle()))
		{
			// si c'est un tir
			if (ConstBonus.listNumTirBonus.contains(type))
			{
				bonusAvion.put("Tir"+type,bonusAvion.get("Tir"+type)+laLB.get(type).valeur > laLB.get(type).cap ? laLB.get(type).cap : bonusAvion.get("Tir"+type)+laLB.get(type).valeur);
			}
			else
			{
				switch (type) {
				
				case 9:		// bonus regen pv
					ThePanel.MonAvion.vie=laLB.get(type).valeur;
					break;
				case 10:	// bouclier
					bonusAvion.put(laLB.get(type).nom,bonusAvion.get(laLB.get(type).nom)+laLB.get(type).valeur > laLB.get(type).cap ? laLB.get(type).cap : bonusAvion.get(laLB.get(type).nom)+laLB.get(type).valeur);
					break;
				case 11:	// nb vie
					bonusAvion.put(laLB.get(type).nom,bonusAvion.get(laLB.get(type).nom)+laLB.get(type).valeur > laLB.get(type).cap ? laLB.get(type).cap : bonusAvion.get(laLB.get(type).nom)+laLB.get(type).valeur);
					break;
				case 13:	// bouclier reflct
					bonusAvion.put(laLB.get(type).nom,bonusAvion.get(laLB.get(type).nom)+laLB.get(type).valeur > laLB.get(type).cap ? laLB.get(type).cap : bonusAvion.get(laLB.get(type).nom)+laLB.get(type).valeur);
					break;
				default:
					break;
				}
			}
			
				
				ThePanel.listeOption.remove(this);
			}

	}

	public static void genereBonus(Avion lavionMort) 
	{
		int hasardOption = ConstBonus.aleatoire(0, 4);
		//System.out.println(hasardOption);
		//hasardOption = 4;
		if (hasardOption>3 || lavionMort.isBoss)
		{
			Option monOption = new Option(lavionMort.getXPosn(),lavionMort.getYPosn());
			ThePanel.listeOption.add(monOption);
		}
		
	}
	
	public static void genereBonus(int type) 
	{
			Option monOption = new Option(type);
			ThePanel.listeOption.add(monOption);
	}
}
