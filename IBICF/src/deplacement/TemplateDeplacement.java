package deplacement;

import ibicf.Coord;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import option.ConstBonus;


public class TemplateDeplacement {
private long debutdep;
private int dernierDep=0;
	
	private List<SequenceDeplacement> unTemplate = new ArrayList<SequenceDeplacement>();
	public SequenceDeplacement getCurrent(int pv)
	{
		SequenceDeplacement celuiRetourne = null;
		
		for (int i = 0; i < this.unTemplate.size(); i++) 
		{
			SequenceDeplacement current = this.unTemplate.get(i);
			if (pv <= current.getLimiteViePhase())
			{
				celuiRetourne = current; 
			}
			else
			{
				break;
			}
		}
		
		return celuiRetourne;
	}
	public Coord calculCoordMouv()
	{
		int i=dernierDep;
		Coord coordMouv=null;
		while (i<getCurrent(100).getMaListeDeplacement().size())
		{
			long markMouv=Calendar.getInstance().getTimeInMillis();
			if ((markMouv-debutdep)<getCurrent(100).getMaListeDeplacement().get(i).getDureeDeplacement())
			{
				//System.out.println(" i: "+i+" coord: "+getCurrent(100).getMaListeDeplacement().get(i).getCoordTrajectoire().getXcoord()+","+getCurrent(100).getMaListeDeplacement().get(i).getCoordTrajectoire().getYcoord()+" angle: "+getCurrent(100).getMaListeDeplacement().get(i).getAngle()+" duree : "+getCurrent(100).getMaListeDeplacement().get(i).getDureeDeplacement());
				coordMouv=getCurrent(100).getMaListeDeplacement().get(i).getCoordTrajectoire();	
				coordMouv.setAngle(getCurrent(100).getMaListeDeplacement().get(i).getAngle());
				dernierDep=i;
				break;
			}
			i++;
		}
		return coordMouv;
	}
	public Coord calculCoordMouv(Coord Boss)
	{
		int i=dernierDep;
		Coord coordMouv=null;
		while (i<getCurrent(100).getMaListeDeplacement().size())
		{
			long markMouv=Calendar.getInstance().getTimeInMillis();
			if ((markMouv-debutdep)<getCurrent(100).getMaListeDeplacement().get(i).getDureeDeplacement())
			{
				coordMouv=getCurrent(100).getMaListeDeplacement().get(i).getCoordTrajectoire();	
				coordMouv.setAngle(getCurrent(100).getMaListeDeplacement().get(i).getAngle());
				dernierDep=i;
				break;
			}
			i++;
		}
		return coordMouv;
	}
	public TemplateDeplacement (boolean isBoss,Coord coordBoss){
		debutdep=Calendar.getInstance().getTimeInMillis();
		SequenceDeplacement maSequence = new SequenceDeplacement(100,2);
		
		Coord coordDestination=new Coord(500,50);
		  int point = ConstBonus.aleatoire(1,4);
		  switch (point) 
		  {
		  case 1:coordDestination=new Coord(100,55);
			break;
		  case 2:coordDestination=new Coord(500,60);
				break;
		  case 3:coordDestination=new Coord(900,50);
				break;
		  case 4:coordDestination=new Coord(400,200);
			break;
		}
		  //Coord coordDepart;
		  	maSequence.setAllerDelaAla(coordBoss, coordDestination, ConstBonus.aleatoire(300, 2000));
		 
		this.unTemplate.add(maSequence);
	
	}
	public TemplateDeplacement(int pTemplate,boolean isBoss,int lvitesse)
	{
		debutdep=Calendar.getInstance().getTimeInMillis();
		SequenceDeplacement maSequence = new SequenceDeplacement(100,lvitesse);
		if (!isBoss)
		{
			if (lvitesse<=3)
			{
				switch (pTemplate) 
				{
					case 1:
					{
							maSequence.setToutDroit(1000);
							maSequence.setQuartDeTourDroite(1,50);
							//maSequence.setToutDroit(2000);
						break;
					}
					case 2:
					{
							maSequence.setToutDroit(1000);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setToutDroit(2000);
							break;
					}
					case 3:
					{
							maSequence.setToutDroit(500);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setToutDroit(3000);
						break;
					}
					case 4:
					{
							maSequence.setToutDroit(500);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setToutDroit(3000);
						break;
					}
					case 5:
					{
							maSequence.setToutDroit(2000);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setToutDroit(2000);
							
						break;
					}
					case 6:
					{
							maSequence.setToutDroit(1000);
							maSequence.setQuartDeTourGauche(1,50);
						break;
					}
					case 7:
					{
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setToutDroit(5000);
						break;
					}
					
				}
			}
			if (lvitesse<=6 && lvitesse>3)
			{
				switch (pTemplate) 
				{
					case 1:
					{
							maSequence.setToutDroit(800);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setToutDroit(200);
							maSequence.setQuartDeTourGauche(5,50);
						break;
					}
					case 2:
					{
							maSequence.setToutDroit(400);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setToutDroit(400);
							maSequence.setQuartDeTourDroite(5,50);
							break;
					}
					case 3:
					{
							maSequence.setToutDroit(200);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setToutDroit(200);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setToutDroit(200);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setToutDroit(200);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setToutDroit(2000);
						break;
					}
					case 4:
					{

							maSequence.setToutDroit(200);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setToutDroit(200);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setToutDroit(200);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setToutDroit(200);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setToutDroit(2000);
						break;
					}
					case 5:
					{
						maSequence.setToutDroit(400);
						maSequence.setQuartDeTourGauche(5,50);
						maSequence.setQuartDeTourGauche(5,50);
						maSequence.setQuartDeTourGauche(5,50);
						maSequence.setQuartDeTourGauche(5,50);
						maSequence.setToutDroit(400);
						maSequence.setQuartDeTourGauche(5,50);
						break;
					}
					case 6:
					{
						maSequence.setToutDroit(800);
						maSequence.setQuartDeTourGauche(5,50);
						maSequence.setQuartDeTourGauche(5,50);
						maSequence.setQuartDeTourDroite(5,50);
						maSequence.setToutDroit(200);
						maSequence.setQuartDeTourDroite(5,50);
						break;
					}
					case 7:
					{
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setToutDroit(5000);
						break;
					}
					
				}
			}
			if (lvitesse>6)
			{
				switch (pTemplate) 
				{
					case 1:
					{
							maSequence.setToutDroit(100);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setToutDroit(100);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setToutDroit(100);
						break;
					}
					case 2:
					{
							maSequence.setToutDroit(150);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setToutDroit(150);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setToutDroit(150);
							break;
					}
					case 3:
					{
							maSequence.setToutDroit(80);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setToutDroit(80);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setToutDroit(80);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setQuartDeTourDroite(5,50);
							maSequence.setToutDroit(80);
						break;
					}
					case 4:
					{

							maSequence.setToutDroit(80);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setToutDroit(80);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setToutDroit(80);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setToutDroit(80);
						break;
					}
					case 5:
					{
							maSequence.setToutDroit(150);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setToutDroit(150);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setToutDroit(150);
						break;
					}
					case 6:
					{
						maSequence.setToutDroit(100);
						maSequence.setQuartDeTourGauche(5,50);
						maSequence.setToutDroit(100);
						maSequence.setQuartDeTourGauche(5,50);
						maSequence.setQuartDeTourGauche(5,50);
						maSequence.setQuartDeTourGauche(5,50);
						maSequence.setQuartDeTourGauche(5,50);
						maSequence.setToutDroit(100);
						break;
					}
					case 7:
					{
							maSequence.setQuartDeTourGauche(5,50);
							maSequence.setToutDroit(5000);
						break;
					}
					
				}
			}
			}
			
		this.unTemplate.add(maSequence);
	}
	public TemplateDeplacement (int ptemplate,boolean isBoss,Coord coordDepart,Coord coordDestination)
	{
		debutdep=Calendar.getInstance().getTimeInMillis();
		SequenceDeplacement maSequence = new SequenceDeplacement(100,2);
		
		switch (ptemplate)
		{ 
		  case 1:
		  { 
			  maSequence.setAllerDelaAla(coordDepart, coordDestination, 3000);
			  break;		 
		  }	
		  case 2:
		  {
				maSequence.setVirageDroite(5,50);
				maSequence.setToutDroit(1000);
				maSequence.setVirageGauche(5,50);
				maSequence.setDemiTourGauche(5,50);
				maSequence.setToutDroit(1000);
				maSequence.setDemiTourGauche(5,50);
			  break;			  
		  }	
		}
		this.unTemplate.add(maSequence);
		
	}
	public List<SequenceDeplacement> getUnTemplate() {
		return unTemplate;
	}
}
