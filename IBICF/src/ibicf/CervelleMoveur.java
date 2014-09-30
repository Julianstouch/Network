package ibicf;

import java.util.ArrayList;
import java.util.Calendar;

import obstacle.Obstacle;
import option.Option;
import projectile.MissileFrag;
import projectile.Projectile;
import avion.Avion;

public class CervelleMoveur extends Thread implements Runnable {
	
	//Mouvements
	  private int delai=10;
	  public static boolean bossstop=false;
	  private static boolean resetTir = false; 
	  private long tps1,tps2;
	  private int decalTpsSol=0;
	  private int mvtAvion=0;
	  
	public CervelleMoveur() {
	}
	
	
	public void run() {
		while (1==1) {
			if (ThePanel.gameState==2 && !ThePanel.isPaused)
			{
				tps1=Calendar.getInstance().getTimeInMillis();
				
//				MAJ DETAILS SOL		
				decalTpsSol++;
				if (decalTpsSol>=4){decalTpsSol=0;ThePanel.ySol+=1;}
				
//				MAJ DETAILS MAP			
				for (int i = 0; i < ThePanel.listeDetails.size(); i++) 
				{
					Obstacle encours = ThePanel.listeDetails.get(i);
					encours.decalageEnCours++;
					if (encours.decalageEnCours>=encours.decalage)
					{
						encours.decalageEnCours=0;
						encours.locy+=encours.vitesse;
					}
				}
				
				//BASE
				if (ThePanel.etatVol==1 || ThePanel.etatVol==2)
				{
					ThePanel.maPiste.setStepProj(0, 1);
					if (ThePanel.etatVol==2)
					{
						ThePanel.MonAvion.centrer();
						mvtAvion++;
						if (mvtAvion>8){ThePanel.MonAvion.retrecir(1);mvtAvion=0;}
						//System.out.println(ThePanel.etatVol);
						if(ThePanel.maPiste.getYPosn()>=-15){ThePanel.MonAvion.vie=ThePanel.MonAvion.vieMax;ThePanel.etatVol=3;}
					}
				}
				
				if (ThePanel.etatVol==4)
				{
					ThePanel.maPiste.setStepProj(0, 1);
					ThePanel.MonAvion.centrer();
					mvtAvion++;
					if (mvtAvion>8){ThePanel.MonAvion.agrandir(1);mvtAvion=0;}
					
					if(ThePanel.maPiste.getYPosn()>ThePanel.getInstance().getHeight())ThePanel.etatVol=0;
				}
				
//				MAJ MONAVION
				if (ThePanel.MonAvion.getYPosn()<ThePanel.maPiste.getYPosn()+ThePanel.maPiste.getHeight() || ThePanel.maPiste.getYPosn()+ThePanel.maPiste.getHeight()>ThePanel.getInstance().getHeight()/2)
				{
					if (ThePanel.etatVol==1)ThePanel.etatVol=2;
				}
				
				if (ThePanel.etatVol==0 || ThePanel.etatVol==1 || ThePanel.etatVol==4 )
				{
					ThePanel.MonAvion.move();
				}

//				MAJ ENNEMIS
				ennemisBougent();
				
//				MAJ DES TIRS
			    tir();
				
//				MAJ DES IMPACTS
				impacts();
			    
//				MAJ DES EXPLOS
				explo();
				
//				MAJ DES OPTIONS
				optioner();
				
				if (synchro())try {sleep(delai);}catch (Exception e) {System.out.println(e.getMessage());}
				
			}
			
		}
	}
	
	private boolean synchro()
	{
		tps2=Calendar.getInstance().getTimeInMillis();
		if (tps2-tps1>delai)
			{return false;}
		else
			{return true;}
	}
	
	private void ennemisBougent() {
		
		ArrayList<Avion> avionAsup=new ArrayList<Avion>();
		for (int i = 0; i < ThePanel.listeEnnemis.size(); i++) {
			Avion monEnnemi=ThePanel.listeEnnemis.get(i);
			if (monEnnemi!=null){
				
				if (monEnnemi.isBoss && bossstop)
				{
					// boss bouge pas
				}
				else
					monEnnemi.move(false,false,false,false);

				
					if ((!monEnnemi.isBoss && !monEnnemi.isMiniBoss)&& (monEnnemi.getXPosn()>ThePanel.getInstance().getWidth()|| monEnnemi.getXPosn()<-50 || monEnnemi.getYPosn()>ThePanel.getInstance().getHeight())){
						avionAsup.add(monEnnemi);
					}

				
				if (ThePanel.MonAvion.getMyPolygon().intersects(monEnnemi.getMyPolygon()))
				{				
					ThePanel.MonAvion.touche(null);
					
					if (!monEnnemi.isBoss)
					{
						Explosion exploGen =new Explosion(monEnnemi);
						ThePanel.setExplo(exploGen);
						avionAsup.add(monEnnemi);
					}
						
				}			
			}
		}
		
		if (avionAsup.size()>0)
		{
				ThePanel.listeEnnemis.removeAll(avionAsup);
		}
	}


private void tir(){
	  ArrayList<Projectile> tirAsup = new ArrayList<Projectile>();
	  Projectile MonProj;
	  
		   	for (int i = 0; i < ThePanel.listeProj.size(); i++) 
		   	{
				MonProj=(Projectile) ThePanel.listeProj.get(i);
				MonProj.moveUp();
				
				if (MonProj.getXPosn()>ThePanel.getInstance().getWidth() || MonProj.getXPosn()<0 || MonProj.getYPosn()<0 || MonProj.getYPosn()>ThePanel.getInstance().getHeight())
	   			{
	   				tirAsup.add(MonProj);
	   				
	   			}
				else
				{
					if (MonProj instanceof MissileFrag) 
					{
						if (MonProj.fragmentation())
							tirAsup.add(MonProj);
					}
					else
					{
						// ---
						Avion monAvion=ThePanel.getInstance().getMonAvion();
						
						// tir amis
						if (MonProj.isProjAmis)
						{
							for (int j = 0; j < ThePanel.listeEnnemis.size(); j++) 
							{
								Avion monEnnemi = ThePanel.listeEnnemis.get(j);
								if (monEnnemi!=null && !monEnnemi.isdead)
								{						
									if (monEnnemi.getMyPolygon().intersects(MonProj.getMyPolygon()))
									{
										monEnnemi.touche(MonProj);
										tirAsup.add(MonProj);
									}
								}
							}
							
						}
						else
						{
							if (monAvion.getMyPolygon().intersects(MonProj.getMyPolygon()))
							{
								if(monAvion.touche(MonProj)) 
									tirAsup.add(MonProj); // y'a eu une collision
									
								if (monAvion.getVie()<=0)
								{
									if (ThePanel.MonAvion.listDeBonus.get("nbVies")<0)
										ThePanel.gameOver();
						    		else
						    		{
						    			monAvion.vie=monAvion.vieMax;
						    			setResetTir(true); // on lui precise qu'il devra vider la liste des tirs
										break;
						    		}
									
								}
							}
						}
					}
				}
			}	
		 
		   	
			 if (tirAsup.size()>0)
			 {	  
				  if (resetTir) // on vient de perdre une vie, on reset les tir
				  {
					  for (int i = 0; i < ThePanel.listeProj.size(); i++) {
							Projectile monprojo = ThePanel.listeProj.get(i);
							try 
							{
								monprojo.finalize();
							} 
							catch (Throwable e) 
							{	
								System.err.println("Erreur");
							}
					  }
					  ThePanel.listeProj.clear();
					  setResetTir(false);
				  }
				  else
				  {
					  ThePanel.listProjOQP = true;
					  ThePanel.listeProj.removeAll(tirAsup);
					  ThePanel.listProjOQP = false;
						 for (int i = 0; i < tirAsup.size(); i++) 
						 {
							Projectile monprojo = tirAsup.get(i);
							try 
							{
								monprojo.finalize();
							} 
							catch (Throwable e) 
							{	
								System.err.println("Erreur");
							} 
						 }
				  }
			  }
}


private void explo(){
			  long delaiexplo;
			  
			  Explosion MonExplo;
			  ArrayList<Explosion> exploAsup=new ArrayList<Explosion>();
			  
			  for (int i = 0; i < ThePanel.listeExplosion.size(); i++) 
			  {
				  delaiexplo=Calendar.getInstance().getTimeInMillis();
				  MonExplo=ThePanel.listeExplosion.get(i);
				  if (MonExplo!=null){
					  if (delaiexplo-MonExplo.getDelai()>50)
						{
					    		if (MonExplo.stade!=7)
					    		{
					    			ThePanel.listeExplosion.get(i).stadesuivant();
					    			ThePanel.listeExplosion.get(i).setDelai(delaiexplo);
					    		}
					    		else
					    		{
					    			exploAsup.add(MonExplo);
					    		}	    			
						}
				  }
			  }

				  if (exploAsup.size()>0)
				  {	  
					  ThePanel.listeExplosion.removeAll(exploAsup);
				  }
	  }

private void impacts(){
	  long delaiexplo;
	  
	  Impact monImpact;
	  
	  ArrayList<Impact> ImpactAsup=new ArrayList<Impact>();
	  
			  for (int i = 0; i < ThePanel.listeImpact.size(); i++) 
			  {
				  delaiexplo=Calendar.getInstance().getTimeInMillis();
				  
				  monImpact=ThePanel.listeImpact.get(i);
				  if (monImpact!=null){
					  if (delaiexplo-monImpact.getDelai()>50)
						{
					    		if (monImpact.stade!=5)
					    		{
					    			ThePanel.listeImpact.get(i).stadesuivant();
					    			ThePanel.listeImpact.get(i).setDelai(delaiexplo);
					    		}
					    		else
					    		{
					    			ImpactAsup.add(monImpact);
					    		}	    			
						}
				  }
			  }
			  if (ImpactAsup!=null){
				  if (ImpactAsup.size()>0)
				  {	  	  
					  ThePanel.listeImpact.removeAll(ImpactAsup);
					  
				  }
			  }
	  }


	private void optioner() {
		  Option monOption; 
		  ArrayList<Option> optionAsup=new ArrayList<Option>();
		  
			   	for (int i = 0; i < ThePanel.listeOption.size(); i++) {
					monOption=ThePanel.listeOption.get(i);
					monOption.moveup();
					monOption.testPris();
					
					if (monOption.getXPosn()>ThePanel.getInstance().getWidth() || monOption.getXPosn()<0 || monOption.getYPosn()<0 || monOption.getYPosn()>ThePanel.getInstance().getHeight())
		   			{
						optionAsup.add(monOption);
						
		   			}
								
				}
			   	
			   	ThePanel.listeOption.removeAll(optionAsup);
			   	for (int i = 0; i < optionAsup.size(); i++) {
				   	monOption=optionAsup.get(i);
				   	try 
					{
						monOption.finalize();
					} 
					catch (Throwable e) 
					{	
						System.err.println("Erreur");
					}
			   	}
	}

	@Override
	public synchronized void start() {
		this.run();
	}


	public static boolean isResetTir() {
		return resetTir;
	}


	public static void setResetTir(boolean resetTir) {
		CervelleMoveur.resetTir = resetTir;
	}
}
