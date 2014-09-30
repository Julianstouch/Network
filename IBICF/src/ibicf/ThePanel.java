package ibicf;

import image.ImageSFXs;
import image.ImagesLoader;
import image.ImagesPlayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import obstacle.Obstacle;
import option.ConstBonus;
import option.Option;
import projectile.MissLocker;
import projectile.Projectile;
import avion.Avion;


public class ThePanel extends JPanel implements ActionListener
{
  private static final long serialVersionUID = 1L;
  
  private static ThePanel instance;
  private static final String IMS_INFO = "imsInfo.txt";
  private final String IMS_MENU = "imsMenu.txt";
  
  public static boolean isPaused = false;
  public static boolean gameOver = false;
  private static Font msgsFont;
  public static Graphics dbg; 
  private Image dbImage = null;
  public static BufferedImage bgImage = null;
  public static BufferedImage btnNouveau = null;
  public static BufferedImage btnAircraft = null;
  public static BufferedImage btnDifficulte = null;
  public static BufferedImage btnResolution = null;
  public static BufferedImage btnQuitter = null;
  public static BufferedImage chiffres = null;
  public static BufferedImage resolution = null;
  public static BufferedImage interface1 = null;
  public static BufferedImage interface2 = null;
  public static BufferedImage interface4 = null;
  public static BufferedImage interface5 = null;
  public static BufferedImage interface6 = null;
  public static BufferedImage interface7 = null;
  public static int cptStats=0;
  public static Sprite maPiste = null;
  
  public static int level=1;
  public static int difficulte=1;
  public static int selResolution=1;
  public static int aicraft=1;
  public static int AVIONSPARLVL=20;
  
  public static Timer horloge;
  public static ImagesLoader imsLoader;
  public static ImagesPlayer imsPlayer;
  public static ImagesLoader imsLoaderMenu;
  public static ImageSFXs effetsSpeciaux;
  public static float coeffResX;
  public static float coeffResY;
  public static int tranche;
  public static int etatVol=0;//0:Vol  1:FinDeLvl 2:Atterri  3:Bonus 4:Decolle
  
  public static int ySol=0;
  public static int score=0;
  
  public static Avion MonAvion=null;
  public static Image vie=null;
  public static ArrayList<Avion> listeEnnemis = new ArrayList<Avion>();
  public static ArrayList<Obstacle> listeDetails = new ArrayList<Obstacle>();
  public static ArrayList<Explosion> listeExplosion=null; 
  public static ArrayList<Impact> listeImpact=null; 
  public static ArrayList<Option> listeOption=null; 
  public static ArrayList<Projectile> listeProj=null;
  public static boolean listProjOQP = false;
  public static int xCentre;
  public static int yCentre;
  public static int gameState=0;
  public static int ySelecteur=0;
  public static String imageLoadee="";
  public static  int FPS;
  
	public static ThePanel getInstance()
	{
		return instance;
	}
  
  public ThePanel(int FPS,int width, int height,int selRes)
  {
	  
	instance = this; 
	
	setSize(width, height);
	
	new ConstBonus();
	
	effetsSpeciaux = new ImageSFXs();
	
	coeffResX=1280/getInstance().getWidth();
	coeffResY= 990/getInstance().getHeight();
	xCentre=getInstance().getWidth()/2;
	yCentre=getInstance().getHeight()/2;
	
    setDoubleBuffered(true);
    //setBackground(Color.blue);
   // setPreferredSize( new Dimension(PWIDTH, getInstance().getHeight()));
    setFocusable(true);
    //requestFocus();
    
    imsLoaderMenu = new ImagesLoader(IMS_MENU); 
    
    bgImage=imsLoaderMenu.getImage("bg");
	btnNouveau=imsLoaderMenu.getImage("bt1");
	btnQuitter=imsLoaderMenu.getImage("bt2");
	btnDifficulte=imsLoaderMenu.getImage("bt3");
	btnAircraft=imsLoaderMenu.getImage("bt4");
	btnResolution=imsLoaderMenu.getImage("bt5");
	interface4=imsLoaderMenu.getImage("interface4");
	interface5=imsLoaderMenu.getImage("interface5");
	interface6=imsLoaderMenu.getImage("interface6");
	
	chiffres=imsLoaderMenu.getImage("diff",difficulte);
	resolution=imsLoaderMenu.getImage("reso",selResolution);
	
	MonAvion = new Avion((getInstance().getWidth()/2)-140, getInstance().getHeight()-150,"avion1",4,true,100,1,false,imsLoaderMenu);
	MonAvion.rotate(90);
	ConstBonus.initTotalBonus();
	
	tranche=(getHeight()/7);
	ySelecteur=tranche*1; 

	msgsFont = new Font("Blippo", Font.BOLD,24);
	
	horloge = new Timer(FPS, getInstance());
	horloge.start();  
	
  }
  
  public static void iniPartie() {
	  	gameState=1;
	  	//level=1;
	  	etatVol=0;
	  	ySelecteur=0;
	  	imsLoader = new ImagesLoader(IMS_INFO);
		bgImage=imsLoader.getImage("space");
		interface1=imsLoader.getImage("interface1");
		interface2=imsLoader.getImage("interface2");
		interface4=imsLoader.getImage("interface4");
		interface5=imsLoader.getImage("interface5");
		interface6=imsLoader.getImage("interface6");
		interface7=imsLoader.getImage("interface7");
		
		System.out.println(xCentre+"   "+(imsLoader.getImage("base").getWidth()/2));
		maPiste=new Sprite(xCentre-(imsLoader.getImage("base").getWidth()/2),-getInstance().getHeight(),"base");
		
		MonAvion.setPosition(xCentre,ThePanel.getInstance().getHeight()-MonAvion.getHeight());
		MonAvion.setWeapon(1);
		MonAvion.monBouclier = new Sprite(MonAvion.getXPosn(),MonAvion.getYPosn(),"bulle1");
		MonAvion.monBouclierReflect = new Sprite(MonAvion.getXPosn(),MonAvion.getYPosn(),"bulle2");
		MonAvion.rotate(0);
		
		vie=imsLoader.getImage("lif");
		
		listeExplosion = new ArrayList<Explosion>();
		listeImpact = new ArrayList<Impact>();
		listeProj=new ArrayList<Projectile>();
		listeOption=new ArrayList<Option>();
		msgsFont = new Font("Blippo", Font.BOLD,18);
	    
		Runner.getInstance().startingBlock();
		
		gameState=2;
}
  private void menuRender() {
	  
	  	dbg.drawImage(bgImage, 0,0,getInstance().getWidth(),getInstance().getHeight(),this); 
	  	//dbg.drawImage(bgImage, 0,0,1280,1024,this); 
	  	
	  	//Nouveau
	  	dbg.drawImage(btnNouveau,xCentre-(btnNouveau.getWidth()/2),tranche*1,this); 

	  	//Aircraft
	  	dbg.drawImage(btnAircraft,xCentre-(btnAircraft.getWidth()/2),tranche*2,this);
	  		
	  	if (ySelecteur==tranche*2)
	  	{
	  		int xStats=xCentre-(btnAircraft.getWidth()/2)+btnAircraft.getWidth()+20;
	  		int yStats=tranche*2;
	  		
		  		dbg.drawImage(interface4,xStats,yStats,(int)(interface4.getWidth()*coeffResX),(int)(interface4.getHeight()*coeffResY),this);
			  		
		  			//Puissance
			  		dbg.setColor(Color.red);
			  		dbg.fillRect(xStats+9,yStats+(int)(6*coeffResX),MonAvion.getPuissance()*10,(int)(24*coeffResX));
			  		//Armure
			  		dbg.setColor(Color.green);
			  		dbg.fillRect(xStats+9,yStats+(int)(41*coeffResX),MonAvion.getArmure()*10,(int)(24*coeffResX));
			  		//Munitions
			  		dbg.setColor(Color.blue);
			  		dbg.fillRect(xStats+9,yStats+(int)(78*coeffResX),MonAvion.getMunitions()*10,(int)(24*coeffResX));
		  	
			  	dbg.drawImage(interface5,xStats,yStats,(int)(interface5.getWidth()*coeffResX),(int)(interface5.getHeight()*coeffResY),this);
		  		
	  	}
	  		
	  		
	  	MonAvion.setPosition(xCentre-(btnDifficulte.getWidth()/2)-160, ySelecteur);
	  	MonAvion.drawSprite(dbg);
	  	
	  	//Difficulte
	  	dbg.drawImage(btnDifficulte,xCentre-(btnDifficulte.getWidth()/2),tranche*3,this);
	  	chiffres=imsLoaderMenu.getImage("diff",difficulte);
	  	dbg.drawImage(chiffres,xCentre-(btnDifficulte.getWidth()/2)+btnDifficulte.getWidth()+35,tranche*3+36,this);
	  	
	  	//Resolution
	  	dbg.drawImage(btnResolution,xCentre-(btnResolution.getWidth()/2),tranche*4,this);
	  	resolution=imsLoaderMenu.getImage("reso",selResolution);
	  	dbg.drawImage(resolution,xCentre-(btnResolution.getWidth()/2)+btnResolution.getWidth()+15,(tranche*4)+36,this);
	  	
	  	//Quitter
	  	dbg.drawImage(btnQuitter,xCentre-(btnQuitter.getWidth()/2),tranche*5,this);
	  	
  }
  
  public void loadingRender(String maChaine) {
	  	dbg.drawImage(bgImage, 0,0,getInstance().getWidth(),getInstance().getHeight(),this);
	  	msgsFont = new Font("SansSerif", Font.BOLD,12);
	  	dbg.setFont(msgsFont);
	  	dbg.setColor(Color.green);
	  	dbg.drawString("Loading data : "+ maChaine, xCentre-100,getInstance().getHeight()-40);
	  	paintScreen();
}
  
  private void gameRender()
  {
     //System.out.println("Ennemis : "+listeEnnemis.size()+" Projectiles : "+listeProj.size()+" Options : "+listeOption.size()+" Impacts : "+listeImpact.size()+" Explosions : "+listeExplosion.size());
    if (ySol>=bgImage.getHeight())ySol=0;
    
    //Paysage
	    dbg.drawImage(bgImage, 0, ySol-getInstance().getHeight(),getInstance().getWidth(),getInstance().getHeight(),this); 
	    dbg.drawImage(bgImage, 0, ySol,getInstance().getWidth(),getInstance().getHeight(),this);
	    
	//Details
	    drawDecors(0);
	    
	//Base
	    if (etatVol!=0)maPiste.drawSprite(dbg);
	    
	    if (etatVol==3) 
	    	//Bonus a modifier
	    {
	    	int xStats=xCentre-interface4.getWidth()/2;
	    	int yStats=maPiste.getYPosn()+maPiste.getHeight()/2+50;
	    	
	    	dbg.drawImage(interface4,xStats,yStats,null);
	    	
	    	//Puissance
	  		dbg.setColor(Color.red);
	  		dbg.fillRect(xStats+9,yStats+(int)(6*coeffResX),MonAvion.getPuissance()*10,(int)(24*coeffResX));
	  		//Armure
	  		dbg.setColor(Color.green);
	  		dbg.fillRect(xStats+9,yStats+(int)(41*coeffResX),MonAvion.getArmure()*10,(int)(24*coeffResX));
	  		//Munitions
	  		dbg.setColor(Color.blue);
	  		dbg.fillRect(xStats+9,yStats+(int)(78*coeffResX),MonAvion.getMunitions()*10,(int)(24*coeffResX));
	    	
	  		dbg.drawImage(interface5,xStats,yStats,null);
	    	dbg.drawImage(interface6,xCentre-interface5.getWidth()/2,yStats+(ySelecteur*interface6.getHeight()),null);
	    	dbg.drawImage(interface7,xStats+interface4.getWidth()-5,yStats,null);
	    	dbg.drawImage(imsLoaderMenu.getImage("nbs",cptStats),xStats+interface4.getWidth(),yStats,null);
	    }
	    
    //PROJECTILES
	    if (listeProj!=null)
	    {
	        for (int i = 0; i < listeProj.size(); i++) {
	        	Projectile MonProj=listeProj.get(i);
	        	if (MonProj!=null)
	        		{
	        			MonProj.drawSprite(dbg);
	        		}
	    	}
	    }
    
	    drawDecors(1);
	    
    //ENNEMIS
	    for (int i = 0; i < listeEnnemis.size(); i++) {
	    	Avion monEnnemi=listeEnnemis.get(i);
	    	if (monEnnemi!=null)
	    		{
	    		monEnnemi.drawSprite(dbg);
	    		}
		}
    
	    drawDecors(2);

    //  CIBLAGE    
	    if (listeProj!=null)
	    {
	        for (int i = 0; i < listeProj.size(); i++) {
	        	Projectile MonProj=listeProj.get(i);
	        	if (MonProj!=null && MonProj instanceof MissLocker)
	        		{
	        			if (((MissLocker)MonProj).etat == 1 && ((MissLocker)MonProj).maCible != null)
	        					{
	        						dbg.setColor(Color.red);
	        						int coordx = ((MissLocker)MonProj).maCible.locx + ((MissLocker)MonProj).maCible.getWidth()/2;
	        						int coordy = ((MissLocker)MonProj).maCible.locy + ((MissLocker)MonProj).maCible.getHeight()/2;
	        						dbg.drawOval(coordx-10, coordy-10, 20, 20);
	        						dbg.drawLine(coordx-15, coordy, coordx+15, coordy);
	        						dbg.drawLine(coordx, coordy-15, coordx, coordy+15);
	        					}
	        			
	        			else
	        			{
	        				if (((MissLocker)MonProj).etat == 2)
	        				{
	        					dbg.setFont(new Font("SansSerif", Font.BOLD,10));
	        			    	dbg.setColor(Color.red);
	        			    	dbg.drawString("Target lost",((MissLocker)MonProj).locx,((MissLocker)MonProj).locy+15);
	        				}
	        			}
	        		}
	        		}
	        		
	    	}
	    
    //ARMES
	    for (int i = 0; i < ConstBonus.listNumTirBonus.size(); i++) 
		{
			int numeroBonus = ConstBonus.listNumTirBonus.get(i);
			  if (MonAvion.listDeBonus.get("Tir"+numeroBonus) > 0)
				  dbg.drawImage(imsLoader.getImage("wea"+numeroBonus), MonAvion.getXPosn(),MonAvion.getYPosn(),MonAvion.getWidth(),MonAvion.getHeight(),null);
		}
    
	    
    //MON AVION
	    		MonAvion.drawSprite(dbg);
	    	
	// ++ BOUCLIERS
	    if (MonAvion.listDeBonus.get("vieBouclier")>0)
	    {
	    	dbg.drawImage(MonAvion.monBouclier.getImage(), MonAvion.getXPosn(), MonAvion.getYPosn()
	        			,MonAvion.getWidth(),MonAvion.getHeight(),null);
	    }
	    if (MonAvion.listDeBonus.get("reflectBouclier")>0)
	    {
	    	dbg.drawImage(MonAvion.monBouclierReflect.getImage(), MonAvion.getXPosn(), MonAvion.getYPosn()
	        			,MonAvion.getWidth(),MonAvion.getHeight(),null);
	    }
    
    
    
    //IMPACTS
	    for (int i = 0; i < listeImpact.size(); i++) 
		  {
	    	Impact MonImpact;
	    	MonImpact=listeImpact.get(i);
	    	if (MonImpact!=null)MonImpact.drawSprite(dbg);
		  }
	    
	    //	  OPTIONS
	    for (int i = 0; i < ThePanel.listeOption.size(); i++) 
		  {
	    	Option monOption;
	    	monOption=ThePanel.listeOption.get(i);
	    	monOption.drawSprite(dbg);
		  }
	    
	    //EXPLOSIONS
	    for (int i = 0; i < listeExplosion.size(); i++) 
		  {
	    	Explosion MonExplo;
	    	MonExplo=listeExplosion.get(i);
	    	if (MonExplo!=null)MonExplo.drawSprite(dbg);
		  }
    
	    drawDecors(3);
	    
    //INFO A LECRAN
    reportStats();
    
    if (gameOver)
      gameOverMessage();
    if (isPaused)
     isPausedMessage();
  }

  public void changeRes(int newRes) {
	  int tempY=ySelecteur/tranche;
	  
		  switch (newRes) {
			case 0: //800*600
				Runner.getInstance().setSize(800, 590);
				setSize(793, 566);
				break;
			case 1: //1024*768
				Runner.getInstance().setSize(1024, 758);
				setSize(1017, 734);
				break;
			case 2: //1280*1024
				Runner.getInstance().setSize(1280, 1014);
				setSize(1273,980);
				break;
			default:
				break;
			}
		  
	  xCentre=getWidth()/2;
	  yCentre=getHeight()/2;
	  
	  tranche=(getHeight()/7);
	  ySelecteur=tranche*tempY; 
	  
	  coeffResX= (float)getWidth()/1273;
	  coeffResY= (float)getHeight()/980;
	  
  }
  
  private void drawDecors(int niveau) 
  {
	  for (int i = 0; i < listeDetails.size(); i++) 
	    {
	    	Obstacle obsEnCours = listeDetails.get(i);
	    	if (obsEnCours.niveauProfondeur == niveau)
	    		obsEnCours.drawSprite(dbg);
		}
  }

private void reportStats()
  {
    if (!gameOver && !isPaused)    // stop incrementing the timer once the game is over
    {	
    	
    	//Interface
    	int yInterface=getInstance().getHeight()-interface2.getHeight()-10;
    	int xInterface1=getInstance().getWidth()-interface1.getWidth()-10;
    	int xInterface2=10;
    	
    	dbg.drawImage(interface1,xInterface1,yInterface,(int)(interface1.getWidth()*coeffResX),(int)(interface1.getHeight()*coeffResY),null);
    	dbg.drawImage(interface2,xInterface2,yInterface,(int)(interface2.getWidth()*coeffResX),(int)(interface2.getHeight()*coeffResY),null);
    	
    	dbg.setFont(msgsFont);
	    
		//vie
    	dbg.setColor(Color.green);
    	float barreVie=((float)MonAvion.getVie()/100)*(interface1.getWidth()-12);
    	dbg.fillRect(xInterface2+6,yInterface+43,(int)barreVie,14);
		
		//bouclier
		if (MonAvion.listDeBonus.get("vieBouclier")>0)
		{
			Color maCouleuBouclier = new Color(0,146,245);
			dbg.setColor(maCouleuBouclier);
			float barreBouclier=((float)(MonAvion.listDeBonus.get("vieBouclier"))/100)*(interface1.getWidth()-12);
			dbg.fillRect(xInterface2+6,yInterface+23,(int)barreBouclier,14);
		}
		if (MonAvion.listDeBonus.get("reflectBouclier")>0)
		{
			Color maCouleuBouclier = new Color(255,110,94);
			dbg.setColor(maCouleuBouclier);
			float barreBouclier2=((float)(MonAvion.listDeBonus.get("reflectBouclier"))/100)*(interface1.getWidth()-12);
			dbg.fillRect(xInterface2+6,yInterface+3,(int)barreBouclier2,14);
		}
		
		//bonus
		double reste=0;
		int cptBonus=0;
		int largeurBonus=6;
		for (int i = 0; i < ConstBonus.listNumTirBonus.size(); i++) 
		{
			int numeroBonus = ConstBonus.listNumTirBonus.get(i);
			reste=MonAvion.listDeBonus.get("Tir"+numeroBonus);
			
			  if (reste> 0)
			  {
				  dbg.setColor(ConstBonus.listeBonus.get(numeroBonus).laColor);
				  
				  double pourcent = ((reste/ConstBonus.listeBonus.get(numeroBonus).cap)*100)/(200/(interface1.getHeight()-2));
				  
				  dbg.fillRect(xInterface2+206+(cptBonus*largeurBonus),getInstance().getHeight()-10-((int)pourcent),largeurBonus-1,(int)pourcent);
				  cptBonus++;
			  }
		}

		//Vie boss 
		if (CervelleCreateur.bossLance)
		{
			for (int i = 0; i < listeEnnemis.size(); i++) {
				Avion monEnnemi=listeEnnemis.get(i);
		    	if (monEnnemi.isBoss)
		    		{
		    		dbg.setColor(Color.green);
		    		dbg.drawRect(getInstance().getWidth()-120,30,100,20);
		    		dbg.setColor(Color.red);
			    		double pourcentage=((double)monEnnemi.vie/(double)monEnnemi.vieMax)*100;
			    		dbg.fillRect(getInstance().getWidth()-119,31,(int)pourcentage-1,19);
			    		monEnnemi.getVie();
		    		}
			}
		}
		
		dbg.setColor(Color.red);
		//score
		dbg.drawString(" Score:"+score,xInterface1+90,yInterface+17);
		//level
		dbg.drawString(" Level: "+level,xInterface1+5,yInterface+17);
		
		//vies
		for (int i = 0; i < MonAvion.listDeBonus.get("nbVies"); i++) {
			dbg.drawImage(vie,xInterface1+7+(i*24),yInterface+23,this);
		}
    }
  }

  private void gameOverMessage()
  {
    String msg = "Game Over";

    dbg.setColor(Color.red);
    dbg.setFont(msgsFont);
    dbg.drawString(msg, xCentre, yCentre);
	msg = "Appuyez sur F2 pour recommencer";
	dbg.drawString(msg, xCentre-100, yCentre+20);
  }  // end of gameOverMessage()
  private void isPausedMessage()
  {
    String msg = "Paused";

    dbg.setColor(Color.red);
    dbg.setFont(msgsFont);
    dbg.drawString(msg, xCentre, yCentre);
  } 

  private void paintScreen()
  // use active rendering to put the buffered image on-screen
  { 
    Graphics g;
    try {
      g = this.getGraphics();
      if ((g != null) && (dbImage != null))
        g.drawImage(dbImage, 0, 0, null);
      	//Toolkit.getDefaultToolkit().sync();
      	g.dispose();
    }
    catch (Exception e)
    { //System.out.println("Graphics context error: " + e);  
    }
  }

	public void actionPerformed(ActionEvent arg0) {
		if (dbImage == null){
		      dbImage = createImage(getInstance().getWidth(), getInstance().getHeight());
		      if (dbImage == null) {
		        return;
		      }
		      else
		        dbg = dbImage.getGraphics();
		    }
		
		switch (gameState) {
		case 0: //Menu
			menuRender();
			break;
		case 1: //Loading
			break;
		case 2: //Jeu
			gameRender();
			break;
		default:
			break;
		}
		paintScreen();
	}

	public BufferedImage getBgImage() {
		return bgImage;
	}

	  
	  public static void gameOver()
	  { 
		gameOver=true;
	    horloge.stop();
	  }

	  public boolean getGameOver()
	  {
		  return gameOver;
	  }
	  
	  public static void resumeGame()
	  {  
		  horloge.start(); 
		  isPaused = false;  
	  } 

	  public static void pauseGame()
	  { 
		  horloge.stop(); 
		  isPaused = true;   
	  } 

	  public static void stopGame() 
	  {  horloge.stop();  }
	
public Avion getMonAvion() {
	return MonAvion;
}

public void setMonAvion(Avion monAvion) {
	MonAvion = monAvion;
}

public static void setProj(Projectile Proj) {
	  listeProj.add(Proj);
}
public static ArrayList<Projectile> getListProj(){
	  return listeProj;
}

public static ArrayList<Explosion> getListExplo(){
	  return listeExplosion;
}
public static void setListExplo(ArrayList<Explosion> newList){
	  listeExplosion.addAll(newList);
}
public static void setExplo(Explosion uneExplo){
	  listeExplosion.add(uneExplo);
}

public static void setImpact(Impact unImpact){
	  listeImpact.add(unImpact);
}

public static void setListeProj(ArrayList<Projectile> listeProj) {
	  listeProj.addAll(listeProj);
}
public static ArrayList<Avion> getListeEnnemis() {
	return listeEnnemis;
	}

public static void reinitnextlevel() {
	listeEnnemis = new ArrayList<Avion>();
	listeExplosion = new ArrayList<Explosion>();
	listeImpact = new ArrayList<Impact>();
	listeOption = new ArrayList<Option>();
	listeProj = new ArrayList<Projectile>();
	horloge.stop();
	horloge = new Timer(FPS, getInstance());
	horloge.start();  
}

public static ImageSFXs getImageSFXs() 
{
	return effetsSpeciaux;
}

}
