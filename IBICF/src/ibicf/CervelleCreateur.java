package ibicf;
import java.util.Calendar;

import obstacle.Obstacle;
import option.ConstBonus;
import avion.Avion;
import avion.Boss;
import avion.ChasseurLeger;
import avion.ChasseurLourd;
import avion.ChasseurVrille;
import bosses.BossConfig;

public class CervelleCreateur extends Thread implements Runnable{
	
	//Creations
	  public static int espacement;
	  public static boolean shooting=false;
	  private static int cptVague=0;
	  private static int cptMaxVague=450-(ThePanel.level*10);
	  public static int cptPetageDavions=0;
	  
	  public static boolean  goBoss=false;
	  public static boolean bossLance=false;
	  private int delai=10;
	  private long tps1,tps2,tpsDetails;
	  private int delaiDetails=500;
	  public static int numMiniBoss=0;
	  
	public CervelleCreateur() {
		espacement=(ThePanel.getInstance().getWidth()/8);
//		initDetails();
		tpsDetails=Calendar.getInstance().getTimeInMillis();
	}
	
	public void run() {
		while (1==1) {
			if (ThePanel.gameState==2 && !ThePanel.isPaused)
			{
				tps1=Calendar.getInstance().getTimeInMillis();
				
			//Details map
				if (Calendar.getInstance().getTimeInMillis()-tpsDetails>delaiDetails)
				{
					tpsDetails=Calendar.getInstance().getTimeInMillis();
					delaiDetails=ConstBonus.aleatoire(4000, 8000);
					int newDetail= ConstBonus.aleatoire(1, 20);
					
					int coordX = ConstBonus.aleatoire(0, ThePanel.getInstance().getWidth()-10);
					Obstacle unDecors = new Obstacle(coordX,-500, "detail"+newDetail); 
				    ThePanel.listeDetails.add(unDecors);
					
				}
				
				
//			VAGUES D'AVIONS
				if (ThePanel.etatVol==0)
				{
					cptVague++;
					
//					MiniBoss
					if (numMiniBoss>0)
					{
						ajouterEnnemi(4,1,1);
						numMiniBoss--;
					}
					
					if (cptVague==cptMaxVague && !goBoss)
						{
							if (cptPetageDavions>ThePanel.AVIONSPARLVL && !goBoss){
								goBoss=true;
								bossLance=false;
							}
							else
							{
								vague();
								cptVague=0;
							}
						}
					else
						{
						
							if (goBoss && !bossLance){
								bossLance=true;
								goBoss=false;
								ajouterEnnemi(4,0,1);
							}
						}
				}
					
				
//			TIRS MONAVION
			   shoots();
			    
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
	
	private void shoots() {
		
		 if (shooting)ThePanel.MonAvion.shoot();
		    
		  for (int i = 0; i < ThePanel.listeEnnemis.size(); i++) 
		  {
			 Avion monEnnemi=ThePanel.listeEnnemis.get(i); 
			 monEnnemi.shootEnnemi();
		  }	  

	}
	
	public static void nextLevel() {
		
		ThePanel.etatVol=1;
		ThePanel.cptStats+=3;
		ThePanel.maPiste.setPosition(ThePanel.xCentre-(ThePanel.imsLoader.getImage("base").getWidth()/2),-ThePanel.getInstance().getHeight());
		bossLance=false;
		cptPetageDavions=0;
		ThePanel.level+=1;
		ThePanel.AVIONSPARLVL=20+(2*ThePanel.level);
		ConstBonus.resetCurrentBonus();
		cptVague=0;
		cptMaxVague-=10;
	}
	
	private void ajouterEnnemi(int TypeAvion,int Placement,int nbSerie) {
		//(int w, int h, int type,int largeurFen,int hauteurFen,int lVitesse,ThePanel monPan)
		Avion monEnnemi;
		int espaceVertical = 100;
		for (int i = 0; i < nbSerie; i++) {
				switch (TypeAvion) {
				case 1: // Chasseur lourd
					monEnnemi = new ChasseurLourd(espacement*Placement,-espaceVertical*i, TypeAvion);
					break;
				case 2: // Chasseur leger
					monEnnemi = new ChasseurLeger(espacement*Placement,-espaceVertical*i, TypeAvion);	
					break;
				case 3: // Chasseur vrille
					 monEnnemi = new ChasseurVrille(espacement*Placement,-espaceVertical*i, TypeAvion);	
					break;
				case 4: //BOSS
					//Placement ==> type 0:boss 1:miniboss1 2:miniboss2
					switch (Placement) {
					case 0:
						monEnnemi = new Boss(ThePanel.WIDTH/2,-200, new BossConfig(ThePanel.level,true));
						break;
					case 1:
						System.out.println("boss 1");
						monEnnemi = new Boss(espacement*Placement,-200, new BossConfig(ThePanel.level-1,false));
						break;
					default:
						monEnnemi = new Boss(ThePanel.WIDTH/2,-200, new BossConfig(ThePanel.level,false));
						break;
					}
					
					break;
				default:
					 monEnnemi = new ChasseurLourd(espacement*Placement,-espaceVertical*i, TypeAvion);
					break;
			}
			ThePanel.listeEnnemis.add(monEnnemi);	
		}
	}
	
private void vague() {
		
			int hasard = (int)((Math.random()*10)+2);
			switch (hasard) {
			case 2:
				//int TypeAvion,int  Placement  nbQuiSeSuivent
				ajouterEnnemi(3,3,6);
				break;
			case 3:
//				int TypeAvion,int  Placement  nbQuiSeSuivent
				ajouterEnnemi(3,2,2);
				ajouterEnnemi(3,4,2);	
				ajouterEnnemi(3,6,2);	
				break;
			case 4:
//				int TypeAvion,int  Placement  nbQuiSeSuivent
				ajouterEnnemi(2,2,2);
				ajouterEnnemi(2,6,2);
				break;
			case 5:
//				int TypeAvion,int  Placement  nbQuiSeSuivent
				ajouterEnnemi(2,1,1);
				ajouterEnnemi(2,3,1);	
				ajouterEnnemi(2,5,1);	
				ajouterEnnemi(2,7,1);	
				break;
			case 6:
//				int TypeAvion,int  Placement  nbQuiSeSuivent
				ajouterEnnemi(2,2,2);
				ajouterEnnemi(2,4,1);	
				ajouterEnnemi(2,6,2);
				break;
			case 7:
//				int TypeAvion,int  Placement  nbQuiSeSuivent
				ajouterEnnemi(1,2,1);
				ajouterEnnemi(1,6,1);		
				break;
			case 8:
//				int TypeAvion,int  Placement  nbQuiSeSuivent
				ajouterEnnemi(1,2,1);
				ajouterEnnemi(1,4,1);
				ajouterEnnemi(1,6,1);
				break;
			case 9:
//				int TypeAvion,int  Placement  nbQuiSeSuivent
				ajouterEnnemi(2,2,2);
				ajouterEnnemi(3,4,1);
				ajouterEnnemi(2,6,2);
				break;
			case 10:
//				int TypeAvion,int  Placement  nbQuiSeSuivent
				ajouterEnnemi(1,1,1);
				ajouterEnnemi(2,3,1);	
				ajouterEnnemi(2,5,1);
				ajouterEnnemi(1,7,1);	
				break;
			case 11:
//				int TypeAvion,int  Placement  nbQuiSeSuivent
				ajouterEnnemi(1,2,1);
				ajouterEnnemi(3,4,3);
				ajouterEnnemi(1,6,1);
				break;
			case 12:
//				int TypeAvion,int  Placement  nbQuiSeSuivent
				ajouterEnnemi(3,2,1);
				ajouterEnnemi(1,4,3);
				ajouterEnnemi(3,6,1);
				break;
			case 13:
//				int TypeAvion,int  Placement  nbQuiSeSuivent
				ajouterEnnemi(3,2,4);
				ajouterEnnemi(3,4,4);
				break;
			case 14:
//				int TypeAvion,int  Placement  nbQuiSeSuivent
				ajouterEnnemi(2,4,4);
				break;
			default:
//				int TypeAvion,int numAvion,int Placement,int Vitesse
				ajouterEnnemi(1,1,4);
				break;
			}

	}

	@Override
	public synchronized void start() {
		this.run();
	}
}
