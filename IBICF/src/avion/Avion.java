package avion;
import ibicf.CervelleCreateur;
import ibicf.Coord;
import ibicf.Explosion;
import ibicf.Impact;
import ibicf.Sprite;
import ibicf.ThePanel;
import image.ImagesLoader;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Calendar;
import java.util.HashMap;

import option.ConstBonus;
import option.Option;
import projectile.Laser;
import projectile.LaserWave;
import projectile.MissLocker;
import projectile.Missile;
import projectile.MissileFrag;
import projectile.Mitrailleuse;
import projectile.Projectile;
import tir.Rafale;
import tir.SequenceTir;
import tir.TemplateTir;
import tir.Tir;
import deplacement.TemplateDeplacement;

public class Avion extends Sprite {
	  private int XSTEP = 1;  
	  private int YSTEP = 1; 
	  public boolean left = false;
	  public boolean right = false;
	  public boolean up = false;
	  public boolean down = false;
	  
	  public int vie = 50;
	  public int vieMax = 100;
	  private int lnumWeapon=1;
	  public boolean principal;
	  
	  public int puissance; 
	  public int armure;
	  public int munitions;
	  
	  public long delai,delaistock=0;
	  public boolean explo=false;
	  protected int Vitesse;
	  protected int points;
	  public boolean isBoss;
	  public boolean isMiniBoss;
	  public TemplateTir templTir;
	  public TemplateDeplacement tempDep=null;
	  public Sprite monBouclier=null;
	  public Sprite monBouclierReflect=null;
	  public int angleBouclier=0;
	  
	  public boolean isdead = false;
	  //	pour decollage atterrissage     
	  public int etatVol=0; //0 :Vol 1 :Atterri 2 :Decolle
	  private boolean isInvincible=false;
	  private long dureeInvincible=200;
	  private long cptInvincible=0;
	  protected Integer angletraj=null;
	  
	  public HashMap<String, Long> listDelaiAmis = new HashMap<String, Long>();
	  public HashMap<String, Integer> listDeBonus = new HashMap<String, Integer>();
	  
	  
	  public Avion(int w, int h, String imageAvion,int lvitesse,boolean cavionPrincipal,int lVie,int template,boolean pisboss) 
	  { 
		  super( w, h,imageAvion);
		  construction(w,h,imageAvion,lvitesse,cavionPrincipal,lVie, template,pisboss);
		  
		  if (cavionPrincipal)
			  setMesPts();
	  } 
	  
	  public Avion(int w, int h, String imageAvion,int lvitesse,boolean cavionPrincipal,int lVie,int template,boolean pisboss,ImagesLoader monImgLoader) 
	  { 
		  super( w, h,imageAvion,monImgLoader);
		  construction(w,h,imageAvion,lvitesse,cavionPrincipal,lVie, template,pisboss);
		  
		  if (cavionPrincipal)
			  setMesPts();
	  } 
	  
	  public Avion(int w, int h, String imageAvion)
	  {
		  super( w, h,imageAvion);
	  }
	  
	  private void construction(int w, int h,String imageAvion,int lvitesse,boolean cavionPrincipal,Integer v,int temp,boolean pisboss)
	  {
		  // partie commune
		  principal=cavionPrincipal;
		  		  
		  setVitesse(lvitesse);
		  isBoss=pisboss;
		  isMiniBoss = false;
		  
		  if (!isBoss)
			  templTir=new TemplateTir(temp,this);
		  else
			  templTir=new TemplateTir(temp,this,isBoss);
		  
		  // sp�cif
		  if (!cavionPrincipal)
		  	 {setVie(v);}
		  else
			  {
			  setAvion(1);
			  setVie(100);
			  initBonus();
			  listDeBonus.put("nbVies",3);
		  	  centrer();
		  	  }
		  
		  
	  }
	  
	  public void setAvion(int numAvion) {
		  
		  switch (numAvion) {
			case 1:
				puissance=1; 
				armure=10;
				munitions=2;
				break;
			case 2:
				puissance=10; 
				armure=2;
				munitions=1;
				break;
			case 3:
				puissance=4; 
				armure=5;
				munitions=4;
				break;
			default:
				break;
			}
		  
		  setImage("avion"+numAvion);
		  
	}
	  
	  public int getPuissance() {
		return puissance;
	}
	  
	  public int getArmure() {
		return armure;
	}
	  
	  public int getMunitions() {
		return munitions;
	}
	  
	  public void setPuissance(int puissance) {
		this.puissance = puissance;
	}
	  public void setArmure(int armure) {
		this.armure = armure;
	}
	  
	  public void setMunitions(int munitions) {
		this.munitions = munitions;
	}
	  
	  @Override
	public void drawSprite(Graphics g) {
		    if (isActive()) {
		        if (image == null) {   // the sprite has no image
		          g.setColor(Color.yellow);   // draw a yellow circle instead
		          g.fillOval(locx, locy, SIZE, SIZE);
		          g.setColor(Color.black);
		        }
		        else {
		        	
		          if (isLooping)
		          image = player.getCurrentImage();
		          
		          if (isInvincible)
		          {
		        	  cptInvincible++;
		        	  dureeInvincible-=1;
		        	  
		        	  	if (cptInvincible>=15)
		        	  		
		        		  {if (cptInvincible>=30)cptInvincible=0;}
			        	  else
			        	  {
			        			 
			        		    //AVION
			        		    g.drawImage(image, locx, locy,width,height, null);
			        	  
			        	  }
		        	  	
		        	  	if (dureeInvincible<=0)
	        			 {dureeInvincible=200;
	        			 isInvincible=false;}
		        	  	
		          }
		          else
		          {
		        	  g.drawImage(image, locx, locy,width,height, null);
		          }
		        }
		      }
	}
	  
	  public void initBonus() 
	  {
		  listDeBonus.put("vieBouclier",0);
		  listDeBonus.put("reflectBouclier",0);
		  for (int i = 0; i < ConstBonus.listNumTirBonus.size(); i++) {
			  listDeBonus.put("Tir"+(ConstBonus.listNumTirBonus.get(i)),0);
		}
	}
	  
	  public void eparpilleBonus() 
	  {
		  listDeBonus.put("vieBouclier",0);
		  for (int i = 0; i < ConstBonus.listNumTirBonus.size(); i++) {
			  if (listDeBonus.get("Tir"+ConstBonus.listNumTirBonus.get(i))>0)
			  {
				  listDeBonus.put("Tir"+(ConstBonus.listNumTirBonus.get(i)),0);
				  Option.genereBonus(ConstBonus.listNumTirBonus.get(i));
			  }
		}
	}

	public int getPoints() {
			return points;
		  }
		  
	  public int getVitesse() {
			return Vitesse;
		}
	  	  
	  public void setExplo()
	  {
		  explo=true; 
	  }
	  
		public static void main(String[] args) {	 
			
		}
		
		private void setVie(int pvie) {
			vie = pvie;
			vieMax=vie;
		}
		
		
		public void shootEnnemi(){
			Coord avionViseCoord;
			avionViseCoord=new Coord(ThePanel.MonAvion.getXPosn()+ThePanel.MonAvion.getWidth()/2,ThePanel.MonAvion.getYPosn()+ThePanel.MonAvion.getHeight()/2);
			
			Float pourcentVie = Float.valueOf(vie)/Float.valueOf(vieMax);
			pourcentVie = pourcentVie * 100;
			int pourcentvitint = pourcentVie.intValue();
			SequenceTir seqEnCours = templTir.getCurrent(pourcentvitint);
						
			int currentGroupe = 0;
			
			for (int i = 0; i < seqEnCours.getMaListeTir().size(); i++) 
			{
				Tir monTir = seqEnCours.getMaListeTir().get(i);
				
				// on recup la possible rafale
				Rafale rafale = seqEnCours.getMaListeRafale().get(monTir.getGroupe());
				
				// on recup le temps actuel
				long tempsNow = Calendar.getInstance().getTimeInMillis();

				// on recup le temps que doit etre executer le prochain tir
				Long futurTir = listDelaiAmis.get(monTir.getType()+"-"+monTir.getGroupe());

				// premier tir , on test s'il y a une rafale avec un temps initial a rajout� au premier tir, sinon, on tir de suite.
				if (futurTir == null)
				{
					if (rafale != null && rafale.getInitialdelai() > 0)
					{
						futurTir = tempsNow+rafale.getInitialdelai();
						listDelaiAmis.put(monTir.getType()+"-"+monTir.getGroupe(),futurTir);
					}
					else
					{
						futurTir = 0L;
					}
				}
				
				// si le temps du prochain tir est arrive, ou que le tir fait parti du meme groupe que le precedent
				if ((futurTir < tempsNow) || monTir.getGroupe() == currentGroupe)
				{
					Projectile projAvion ;
					int placementX = this.getXPosn()+this.getWidth()/2+monTir.getDecalagex();
					int placementY = this.getYPosn()+this.getHeight()+monTir.getDecalagey();
					switch (monTir.getType()) 
					{
						case 1:projAvion=new Mitrailleuse(placementX,placementY,monTir.getAngle(),monTir.isAutoGuide(),monTir.getVitDeplacement(),monTir.getDegats(),false,avionViseCoord);
							break;
						case 2:projAvion=new Missile(placementX,placementY,monTir.getAngle(),monTir.isAutoGuide(),monTir.getVitDeplacement(),monTir.getDegats(),false,avionViseCoord);
							break;
						case 3:projAvion=new Laser(placementX,placementY,monTir.getAngle(),monTir.isAutoGuide(),monTir.getVitDeplacement(),monTir.getDegats(),false,avionViseCoord);
							break;
						case 4:projAvion=new MissLocker(placementX,placementY,monTir.getAngle(),monTir.isAutoGuide(),monTir.getVitDeplacement(),monTir.getDegats(),false,avionViseCoord);
							break;
						case 5:projAvion=new MissileFrag(placementX,placementY,monTir.getAngle(),monTir.isAutoGuide(),monTir.getVitDeplacement(),monTir.getDegats(),false,avionViseCoord,this);
							break;
						case 6:projAvion=new LaserWave(placementX,placementY,monTir.getAngle(),monTir.isAutoGuide(),monTir.getVitDeplacement(),monTir.getDegats(),false,avionViseCoord);
							break;
						default :projAvion=new Mitrailleuse(placementX,placementY,monTir.getAngle(),monTir.isAutoGuide(),monTir.getVitDeplacement(),monTir.getDegats(),false,avionViseCoord);
						break;
					}
					
					int delaiRafale = 0;
					// une fois le tir fait, on planifie le prochain tir
					if (rafale != null) 
						{
							// dans le doute, on vire le delai initial
							rafale.setInitialdelai(0);
							// on passe au tir suivant dans la rafale
							rafale.setNbEncours(rafale.getNbEncours()+1);
							
							// si on a atteind le dernier tir de la rafale, on planifira le prochain tir avec le delai de la rafale.
							// et on reset le numero du groupe pour evit� qu'on ne retire aussitot la rafale sans le test du delai
							if (rafale.getNbEncours() > rafale.getNbMax())
							{
								rafale.setNbEncours(1);
								delaiRafale = rafale.getDelai();
								currentGroupe = 0;
							}						
							else
							{
								currentGroupe = monTir.getGroupe();
							}
						}
					else
						{
							currentGroupe = monTir.getGroupe();
						}
						// on ajoute le tir, et on planifir le prochain avec le temps actuel + la cadence de tir + le possible delai de rafale.
						ThePanel.setProj(projAvion);
						listDelaiAmis.put(monTir.getType()+"-"+monTir.getGroupe(),tempsNow+(monTir.getCadence())+delaiRafale);
						
						// on memorise le groupe actuel qui vient de tirer 
						
				}
			}
				
	  }
	  
		
		
		public void shoot()
		{
			delai=Calendar.getInstance().getTimeInMillis();
//			if (delaistock==0){
//				delaistock=delai;
//			}
			SequenceTir seqEnCours;
			seqEnCours = templTir.getCurrentAmis(lnumWeapon);
			
			executeBonus(seqEnCours);
			
			int currentGroupe = 0;
			
			for (int i = 0; i < seqEnCours.getMaListeTir().size(); i++) 
			{
				Tir monTir = seqEnCours.getMaListeTir().get(i);
				delai=Calendar.getInstance().getTimeInMillis();
				Long lastTir = listDelaiAmis.get(monTir.getType()+"-"+monTir.getGroupe());
				if (lastTir == null) lastTir = 0L;
				
				//if (monTir.getTempsdernier()==0){
				//	monTir.setTempsdernier(delai);
				//}
					if ((lastTir < delai) || monTir.getGroupe() == currentGroupe)
					{
						Projectile projAvion ;
						int  typetest = monTir.getType();
						//if ((""+typetest).length() > 1)
						//	typetest = Integer.valueOf((""+typetest).substring(0,1));
						
						int placementX = this.getXPosn()+this.getWidth()/2+monTir.getDecalagex();
						int placementY = this.getYPosn()+monTir.getDecalagey();
						
						switch (typetest) 
						{
											
							case 1:projAvion=new Mitrailleuse(placementX,placementY,monTir.getAngle(),monTir.isAutoGuide(),monTir.getVitDeplacement(),monTir.getDegats()+ThePanel.level+puissance,true,null);
								break;
							case 2:projAvion=new Missile(placementX,placementY,monTir.getAngle(),monTir.isAutoGuide(),monTir.getVitDeplacement(),monTir.getDegats()+puissance,true,null);
								break;
							case 3:projAvion=new Laser(placementX,placementY,monTir.getAngle(),monTir.isAutoGuide(),monTir.getVitDeplacement(),monTir.getDegats()+puissance,true,null);
								break;
							case 4:projAvion=new MissLocker(placementX,placementY,monTir.getAngle(),monTir.isAutoGuide(),monTir.getVitDeplacement(),monTir.getDegats()+puissance,true,null);
								break;
							case 5:projAvion=new MissileFrag(placementX,placementY,monTir.getAngle(),monTir.isAutoGuide(),monTir.getVitDeplacement(),monTir.getDegats()+puissance,true,null,this);
								break;
							case 6:projAvion=new LaserWave(placementX,placementY,monTir.getAngle(),monTir.isAutoGuide(),monTir.getVitDeplacement(),monTir.getDegats()+puissance,true,null);
								break;
								
							default :projAvion=new Mitrailleuse(placementX,placementY,monTir.getAngle(),monTir.isAutoGuide(),monTir.getVitDeplacement(),monTir.getDegats()+puissance,true,null);
							break;
						}
						majBonus(monTir.getGroupe());
						
						if (!ThePanel.listProjOQP)
						{
							ThePanel.setProj(projAvion);
							listDelaiAmis.put(monTir.getType()+"-"+monTir.getGroupe(),delai+ (monTir.getCadence()));
							currentGroupe = monTir.getGroupe();
						}
					}
			}
		}
			
		public SequenceTir executeBonus(SequenceTir maSequence)
		{
			for (int i = 0; i < ConstBonus.listNumTirBonus.size(); i++) 
			{
				int numeroBonus = ConstBonus.listNumTirBonus.get(i);
				  if (listDeBonus.get("Tir"+numeroBonus) > 0)
					  maSequence = templTir.getCurrentAmisBonus(numeroBonus, maSequence);
			}
			return maSequence;
		}	
		
		public void majBonus(int currentGroupe)
		{
			if (currentGroupe < 100)
				listDeBonus.put("Tir"+currentGroupe,listDeBonus.get("Tir"+currentGroupe)-1);
		}
		
	  
	  public void setWeapon(int numWeapon){
		  		lnumWeapon=numWeapon;
	  }
	  public int getWeapon(){
	  		return lnumWeapon;
	  }
		
	  public int getVie() {
			return vie;
		}
		
		private void setVitesse(int mvitesse) {
			XSTEP=mvitesse;
			YSTEP=mvitesse;
		}
			
		public boolean touche(Projectile monProj){
			
			Impact impacted =new Impact(this);
			ThePanel.setImpact(impacted);
			int degats;
			boolean asupprimer = true;
			
			if (monProj==null)
				{degats=20;}
			else
				{degats=monProj.degat;}
			
			if (principal)
			{
				
				if (vie > 0 && !isInvincible)
				{
					// on reduit le bouclier
					
					
					// s'il est pass� en n�gatif, on bascule le negatif sur les PV, et on le remet a 0
					// sinon c'est que le boubou a absorb� tout 
					if (listDeBonus.get("reflectBouclier")>0 && (monProj!=null))
					{
							listDeBonus.put("reflectBouclier",listDeBonus.get("reflectBouclier") - 1);
							monProj.isProjAmis=true;
//							if (monProj.diago < 180 )
//								monProj.diago+=180;
//							else
//								monProj.diago-=180
							if (monProj.diago==0)
								monProj.diago=180;
							else
								monProj.diago=-monProj.diago;
							
							
							monProj.calculCoordTirAngle();
							System.out.println(monProj.diago+" coord:x: "+monProj.coordVisee.getXcoord()+" coord:y: "+monProj.coordVisee.getYcoord());
							
							asupprimer = false;
					}
					else
					{
						if (armure<degats)
						{listDeBonus.put("vieBouclier",listDeBonus.get("vieBouclier") - (degats-armure));}
						else
						{listDeBonus.put("vieBouclier",listDeBonus.get("vieBouclier") - (degats));}
					}
					
					
					if (listDeBonus.get("vieBouclier")<0)
					{
						vie+=listDeBonus.get("vieBouclier");
						listDeBonus.put("vieBouclier",0);
					}
					
				}
				if (vie <= 0)
				{
					listDeBonus.put("nbVies",listDeBonus.get("nbVies")-1);
					setInvincible(true);
					eparpilleBonus();
					setPosition((ThePanel.getInstance().getWidth()/2)-50, ThePanel.getInstance().getHeight()-150);
				}
				
			}
			else
			{
				if (vie>0){
					vie-=(degats-armure);
					if (vie<=0 && !isdead)
						{
							if (isBoss && CervelleCreateur.bossLance)
							{
								CervelleCreateur.nextLevel();
							}
						
						isdead = true;
						Explosion exploGen =new Explosion(this);
						ThePanel.setExplo(exploGen);
						Option.genereBonus(this);
						ThePanel.score+=getPoints();
						CervelleCreateur.cptPetageDavions++;
						
						//MiniBoss 1
						if (CervelleCreateur.cptPetageDavions==(ThePanel.AVIONSPARLVL/3) && ThePanel.level>1)
							{
								CervelleCreateur.numMiniBoss++;
							}
						//MiniBoss 2
							if (CervelleCreateur.cptPetageDavions==(ThePanel.AVIONSPARLVL/3)*2 && ThePanel.level>2)
							{
								CervelleCreateur.numMiniBoss++;
							}
						
						}
				}
			}
			return asupprimer;
		}

		
		
		public void move(boolean mLeft,boolean mRight,boolean mUp, boolean mDown)
		{
		}
		
		public void move()
		{
			int x=0;
			int y=0;
			int xAvance=0;
			int yAvance=0;
			 
					if (!CervelleCreateur.shooting)
					{xAvance=XSTEP*2;yAvance=YSTEP*2;}
					else
					{xAvance=XSTEP;	yAvance=YSTEP;}
					
					
						if (left) {x-=xAvance;}
						if (right) {x+=xAvance;}
						if (up) {
							y-=yAvance;
							}
						if (down) {
							y+=yAvance;
							}
						
					setStep(x,y,principal);

			  
			  angleBouclier++;
				if (angleBouclier>=360)angleBouclier=0;
				
				if (listDeBonus.get("vieBouclier")>0)
				{
					monBouclier.locx=getXPosn();
					monBouclier.locy=getYPosn();
					monBouclier.rotate(angleBouclier);
				}
				
				if (listDeBonus.get("reflectBouclier")>0)
				{
					monBouclierReflect.locx=getXPosn();
					monBouclierReflect.locy=getYPosn();
					monBouclierReflect.rotate(angleBouclier);
				}
					
		}

		public boolean getIsInvincible() {
			return isInvincible;
		}

		public void setInvincible(boolean isInvincible) {
			this.isInvincible = isInvincible;
		}

		public Integer getAngletraj() {
			return angletraj;
		}

		public void setAngletraj(Integer angletraj) {
			this.angletraj = angletraj;
		}
	  
}
