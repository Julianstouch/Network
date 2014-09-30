package ibicf;
import java.util.ArrayList;
import java.util.Calendar;

import avion.Avion;


public class Explosion extends Sprite {
	public int stade=1;
	private long delai;
	private Avion lAvion;
	ArrayList<Avion> pListeAvions;
	
	public Explosion(Avion avionExplose) {
		super(avionExplose.getXPosn()-avionExplose.getWidth()/2, avionExplose.getYPosn()+avionExplose.getHeight()/2, "ex1");
		delai=Calendar.getInstance().getTimeInMillis();
		lAvion=avionExplose;
		int newX=(lAvion.getXPosn()+(lAvion.getWidth()/2))-(getWidth()/2);
		int newY=(lAvion.getYPosn()+(lAvion.getHeight()/2))-(getHeight()/2);
		rotate((int)(Math.random()*360));
		this.setPosition(newX,newY);
	}
	
	public Explosion(Avion avionExplose, int lX,int lY) {
		super(lX, lY, "ex1");
		delai=Calendar.getInstance().getTimeInMillis();
		lAvion=avionExplose;
		rotate((int)(Math.random()*360));
	}
	
	public Explosion(int dx,int dy) {
		super(dx,dy, "ex1");
		delai=Calendar.getInstance().getTimeInMillis();
	}
	public void stadesuivant(){

		switch (stade){
		case 1:this.setImage("ex1");
			   stade=2;
			   break;
		case 2:this.setImage("ex2");
			   stade=3;
			   break;
		case 3:this.setImage("ex3");
				stade=4;
			   break;
		case 4:this.setImage("ex4");
				stade=5;
				break;
		case 5:this.setImage("ex5");
				stade=6;
				break;
		case 6:stade=7;
				ThePanel.listeEnnemis.remove(lAvion);
				break;
		}
		
		rotate((int)(Math.random()*360));
		//setTaille(lAvion.getWidth(),lAvion.getHeight());
		
		int newX=(lAvion.getXPosn()+(lAvion.getWidth()/2))-(getWidth()/2);
		int newY=(lAvion.getYPosn()+(lAvion.getHeight()/2))-(getHeight()/2);
		
		//rotate((int)(Math.random()*360));
		this.setPosition(newX,newY);
		
		
	}
	public long getDelai(){
		return delai;
	}
	public void setDelai(long del)
	{
		delai=del;
	}
}
