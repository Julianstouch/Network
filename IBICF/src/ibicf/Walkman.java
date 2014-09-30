package ibicf;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Walkman {

	
	public KeyListener getKL()
	{
		KeyListener leKL = new KeyListener()
		{
	
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
			    if ((keyCode == KeyEvent.VK_ESCAPE) || (keyCode == KeyEvent.VK_Q) ||
			        (keyCode == KeyEvent.VK_END) ||
			        ((keyCode == KeyEvent.VK_C) && e.isControlDown()) ){
			    	ThePanel.horloge.stop();
			    	}
			    if (keyCode == KeyEvent.VK_P)
			    	{
			    		if (ThePanel.isPaused!=true)
			    			ThePanel.pauseGame();
			    		else
			    			ThePanel.resumeGame();
			    	}
			    if (keyCode == KeyEvent.VK_O)
			    	{
			    		CervelleMoveur.bossstop = !CervelleMoveur.bossstop;
			    	}
			    switch (ThePanel.gameState) {
				case 0:
					int tranche = ThePanel.tranche;
					
					//DEFILEMENT HAUT BAS
					if (keyCode == KeyEvent.VK_DOWN)
						{
						if (ThePanel.ySelecteur<(tranche*4))ThePanel.ySelecteur+=tranche;
						}
				     if (keyCode == KeyEvent.VK_UP)
				    	 {
				    	 if (ThePanel.ySelecteur>tranche)ThePanel.ySelecteur-=tranche;
				    	 }
				    
				     //AIRCRAFT
				     if (ThePanel.ySelecteur==(tranche*2))
				     {
				    	  if (keyCode == KeyEvent.VK_RIGHT)
							{
						    	if (ThePanel.aicraft<3)ThePanel.aicraft+=1;
							}
				    	  
					     if (keyCode == KeyEvent.VK_LEFT)
						     {
						    	 if (ThePanel.aicraft>1)ThePanel.aicraft-=1;
						     } 
					     ThePanel.MonAvion.setAvion(ThePanel.aicraft);
					     ThePanel.MonAvion.rotate(90);
				     }
				     
				    //DIFFICULTE
				     if (ThePanel.ySelecteur==(tranche*3))
				     {
				    	  if (keyCode == KeyEvent.VK_RIGHT)
							{
						    	if (ThePanel.difficulte<2)ThePanel.difficulte+=1;
							}
				    	  
					     if (keyCode == KeyEvent.VK_LEFT)
						     {
						    	 if (ThePanel.difficulte>0)ThePanel.difficulte-=1;
						     } 
				     }
				     
				     //RESOLUTION
				     if (ThePanel.ySelecteur==(tranche*4))
				     {
				    	  if (keyCode == KeyEvent.VK_RIGHT)
							{
						    	if (ThePanel.selResolution<2)
						    		{
						    		ThePanel.selResolution+=1;
						    		ThePanel.getInstance().changeRes(ThePanel.selResolution);
						    		}
						    	 
							}
				    	  
					     if (keyCode == KeyEvent.VK_LEFT)
						     {
						    	 if (ThePanel.selResolution>0)
						    		 {
						    		 ThePanel.selResolution-=1;
						    		 ThePanel.getInstance().changeRes(ThePanel.selResolution);
						    		 }
						     } 
					    
				     }
				   
				     if (keyCode == KeyEvent.VK_ENTER)
				     {
				    	 if (ThePanel.ySelecteur==tranche)
				    	 {
				    		 ThePanel.iniPartie();
				    	 }
				     }
					break;
				case 2:
					if (!ThePanel.isPaused && !ThePanel.gameOver ) {
				    	 
						if (ThePanel.etatVol==3)
						{
							if (keyCode == KeyEvent.VK_LEFT)
								{if (ThePanel.cptStats+1<=3)
								{
								    switch (ThePanel.ySelecteur) {
										case 0: // Puissance
											if (ThePanel.MonAvion.puissance-1>=0)
												{
												ThePanel.MonAvion.puissance-=1;
												ThePanel.cptStats+=1;
												}
											break;
										case 1: // Armure
											if (ThePanel.MonAvion.armure-1>=0)
												{
												ThePanel.MonAvion.armure-=1;
												ThePanel.cptStats+=1;
												}
											break;
										case 2: // Munitions
											if (ThePanel.MonAvion.munitions-1>=0)
												{
												ThePanel.MonAvion.munitions-=1;
												ThePanel.cptStats+=1;
												}
											break;
										default:
											ThePanel.MonAvion.puissance-=1;
											break;
										}
						      		}
								}
							
						    if (keyCode == KeyEvent.VK_RIGHT)
						      {if (ThePanel.cptStats-1>=0)
						      		{
								    switch (ThePanel.ySelecteur) {
										case 0: // Puissance
											if (ThePanel.MonAvion.puissance+1<=22)
												{
												ThePanel.MonAvion.puissance+=1;
												ThePanel.cptStats-=1;
												}
											break;
										case 1: // Armure
											if (ThePanel.MonAvion.armure+1<=22)
												{
												ThePanel.MonAvion.armure+=1;
												ThePanel.cptStats-=1;
												}
											break;
										case 2: // Munitions
											if (ThePanel.MonAvion.munitions+1<=22)
												{
												ThePanel.MonAvion.munitions+=1;
												ThePanel.cptStats-=1;
												}
											break;
										default:
											ThePanel.MonAvion.puissance+=1;
											break;
										}
						      		}
						      }
						    
						      if (keyCode == KeyEvent.VK_DOWN){if (ThePanel.ySelecteur+1<=2){ThePanel.ySelecteur+=1;}}
						      if (keyCode == KeyEvent.VK_UP){if (ThePanel.ySelecteur-1>=0){ThePanel.ySelecteur-=1;}}
						      if (keyCode == KeyEvent.VK_ENTER)ThePanel.etatVol=4;
						}
						else
						{
						      if (keyCode == KeyEvent.VK_LEFT)  ThePanel.MonAvion.left=true; 
						      if (keyCode == KeyEvent.VK_RIGHT) ThePanel.MonAvion.right=true;
						      if (keyCode == KeyEvent.VK_DOWN)ThePanel.MonAvion.down=true;
						      if (keyCode == KeyEvent.VK_UP)ThePanel.MonAvion.up=true;	
						      if (keyCode == KeyEvent.VK_SPACE) CervelleCreateur.shooting=true;
						}
						  
				      
				      if(!ThePanel.horloge.isRunning()){
				    	  ThePanel.horloge.start();
				      }
				    }
				    else
				    {
				    	if (keyCode == KeyEvent.VK_F2)
						  {
				    		ThePanel.gameOver=false;
				    		ThePanel.gameState=0;
						  }
				    }
					break;
				default:
					break;
				}
			    
			}
			
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (ThePanel.gameState==2) {
					if (keyCode == KeyEvent.VK_LEFT)   ThePanel.MonAvion.left=false; 
				    if (keyCode == KeyEvent.VK_RIGHT)  ThePanel.MonAvion.right=false;
				    if (keyCode == KeyEvent.VK_DOWN)   ThePanel.MonAvion.down=false;
				    if (keyCode == KeyEvent.VK_UP)	 ThePanel.MonAvion.up=false;
				    if (keyCode == KeyEvent.VK_SPACE) CervelleCreateur.shooting=false;
				}
					
			}
			
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
				
			};
		
		return leKL;		
	}
	
	
	
	
}
