package ibicf;
import java.util.Calendar;

import avion.Avion;

public class Impact extends Sprite {
	public int stade=1;
	private long delai;
	private int newX;
	private int newY;
	private int posxAleatoire;
	private int posyAleatoire;
	private Avion lAvion;
	
	public Impact(Avion avionImpact) {
		super(avionImpact.getXPosn()-avionImpact.getWidth()/2, avionImpact.getYPosn()+avionImpact.getHeight()/2, "imp1");
		delai=Calendar.getInstance().getTimeInMillis();
		lAvion=avionImpact;
		
		posxAleatoire=(int)(Math.random()*(lAvion.getWidth()));
		posyAleatoire=(int)(Math.random()*(lAvion.getHeight()));
		
		positionner();
		rotate((int)(Math.random()*360));
			
	}
	public Impact(int dx,int dy) {
		super(dx,dy, "imp1");
		delai=Calendar.getInstance().getTimeInMillis();
	}
	public void stadesuivant(){

		switch (stade){
		case 1:this.setImage("imp1");
			   stade=2;
			   break;
		case 2:this.setImage("imp2");
			   stade=3;
			   break;
		case 3:this.setImage("imp3");
				stade=4;
			   break;
		case 4:stade=5;
				//pListeAvions.remove(lAvion);
				break;
		}
		
		positionner();
		
		rotate((int)(Math.random()*360));
		this.setPosition(newX,newY);
		
		
	}
	
	private void positionner() {
		newX=(lAvion.getXPosn()+posxAleatoire);
		newY=(lAvion.getYPosn()+posyAleatoire);
		setPosition(newX,newY);
	}
	
	public long getDelai(){
		return delai;
	}
	public void setDelai(long del)
	{
		delai=del;
	}
}
