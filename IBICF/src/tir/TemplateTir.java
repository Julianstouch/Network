package tir;


import ibicf.ThePanel;

import java.util.ArrayList;
import java.util.List;

import option.ConstBonus;

import avion.Avion;

public class TemplateTir 
{
	
	private List<SequenceTir> unTemplate = new ArrayList<SequenceTir>();

	// recupe la bonne sequence de tir en fonction de pv
	public SequenceTir getCurrent(int pv)
	{
		SequenceTir celuiRetourne = null;
		
		for (int i = 0; i < this.unTemplate.size(); i++) 
		{
			SequenceTir current = this.unTemplate.get(i);
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
	
	
	public SequenceTir getCurrentAmis(int pTemplate)
	{
		SequenceTir maSequence = new SequenceTir();
		return getCurrentAmis( pTemplate,  maSequence);
	}
	
	
	public SequenceTir getCurrentAmis(int pTemplate, SequenceTir maSequence)
	{
		
		switch (pTemplate) 
		{
			case 1:
			{
					Tir tir1 = new Tir(1,150,5,180,8,false,100);
					maSequence.getMaListeTir().add(tir1);
					break;
					
			}
//			case 2:
//			{
//						Tir tir1 = new Tir(1,300,10,180,5,false,1);
//						Tir tir2 = new Tir(1,300,10,135,5,false,1);
//						Tir tir3 = new Tir(1,300,10,225,5,false,1);
//						
//					maSequence.getMaListeTir().add(tir1);
//					maSequence.getMaListeTir().add(tir2);
//					maSequence.getMaListeTir().add(tir3);
//					break;
//			}
//			case 3:
//			{
//						Tir tir1 = new Tir(2,800,25,180,ThePanel.MonAvion.getWidth()-40,-70,3,false,1);
//						Tir tir2 = new Tir(2,800,25,180,20,-70,3,false,1);
//					maSequence.getMaListeTir().add(tir1);
//					maSequence.getMaListeTir().add(tir2);
//					break;
//			}
//			case 4:
//			{
//						Tir tir1 = new Tir(3,300,50,180,0,-ThePanel.MonAvion.getHeight()-40,6,false,1);
//					maSequence.getMaListeTir().add(tir1);
//					break;
//			}
//			case 5:
//			{
//						Tir tir1 = new Tir(4,500,100,180,3,false,1);
//					maSequence.getMaListeTir().add(tir1);
//					break;
//			}
//			default:
//			{
//					Tir tir1 = new Tir(1,150,10,180,8,false,1);
//					maSequence.getMaListeTir().add(tir1);
//					break;
//			}
		}
		
		//executeBonus(maSequence);
		
		return (maSequence);

	}
	
	public SequenceTir getCurrentAmisBonus(int pGroupe, SequenceTir maSequence)
	{
		
		switch (pGroupe) 
		{
			case 1:
			{
						Tir tir1 = new Tir(1,75,5,180,0,-10,8,false,pGroupe);
						maSequence.getMaListeTir().add(tir1);
						break;
					
			}
			case 2:
			{
					Tir tir1 = new Tir(1,200,10,180,-15,29,7,false,pGroupe);
					Tir tir2 = new Tir(1,200,10,180,15,29,7,false,pGroupe);
				
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					break;
			}
			case 3:
			{
					Tir tir1 = new Tir(1,200,10,160,15,29,7,false,pGroupe);
					Tir tir2 = new Tir(1,200,10,200,-15,29,7,false,pGroupe);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					break;
			}
			case 4:
			{
					Tir tir1 = new Tir(2,400,25,180,30,52,5,false,pGroupe);
					Tir tir2 = new Tir(2,400,25,180,-30,52,5,false,pGroupe);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
				break;
			}
			case 5:
			{
					Tir tir1 = new Tir(3,1000,40,180,0,30,12,false,pGroupe);
					maSequence.getMaListeTir().add(tir1);
					break;
			}
			case 6:
			{
					Tir tir1 = new Tir(3,1000,40,170,0,75,12,false,pGroupe);
					Tir tir2 = new Tir(3,1000,40,190,0,75,12,false,pGroupe);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					break;
			}
			case 7:
			{
					Tir tir1 = new Tir(4,1000,60,180,40,55,4,true,pGroupe);
					maSequence.getMaListeTir().add(tir1);
					break;
			}
			case 8:
			{
					Tir tir1 = new Tir(5,600,5,180,-40,55,5,false,pGroupe);
					maSequence.getMaListeTir().add(tir1);
					break;
			}
			case 12:
			{
					Tir tir1 = new Tir(6,200,80,180,0,25,12,false,pGroupe);
					maSequence.getMaListeTir().add(tir1);
					break;
			}

		}
		
		
		return (maSequence);

	}
	
	public TemplateTir(int pTemplate,Avion lAvion)
	{
		SequenceTir maSequence = new SequenceTir(100);
		switch (pTemplate)
		{
		case 0:
		{
			
				Tir tir1 = new Tir(1,1200,10,0,0,false,1);
				Tir tir2 = new Tir(1,1200,10,0,45,false,1);
				Tir tir3 = new Tir(1,1200,10,0,315,false,1);
			maSequence.getMaListeTir().add(tir1);
			maSequence.getMaListeTir().add(tir2);
			maSequence.getMaListeTir().add(tir3);
			break;
		}
		case 1:
		{
			int cadence = ConstBonus.aleatoire(1500, 4000);
			int isvisee = ConstBonus.aleatoire(1, 2);
				Tir tir1 = new Tir(1,cadence,5,0,5,(isvisee==1),1);
				maSequence.getMaListeTir().add(tir1);
			break;		  
		}	
		case 2:
		{
			Tir tir1;
			int cadence = ConstBonus.aleatoire(2000, 5000);
			int isvisee = ConstBonus.aleatoire(1, 2);
			if (lAvion.getAngletraj()!=null)
			{
				tir1 = new Tir(2,cadence,20,lAvion.getAngletraj(),5,true,1);
			}
			else
			{	
				tir1 = new Tir(2,cadence,20,0,5,(isvisee==1),1);		
			}
			maSequence.getMaListeTir().add(tir1);
			//maSequence.getMaListeTir().add(tir2);
			break;		  
		}	
		}
		this.unTemplate.add(maSequence);
	}
		
	// IS A BOSS
	public TemplateTir(int pTemplate,Avion lAvion,boolean isBoss)
		{
		
		if (pTemplate < 100)
		{
			switch (pTemplate) 
			{
				case 0:
				{
					SequenceTir maSequence = new SequenceTir(100);
							Tir tir1 = new Tir(1,300,10,0,90,0,5,false,1);
							Tir tir2 = new Tir(1,300,10,0,33,0,5,false,1);
							Tir tir3 = new Tir(1,300,10,0,20,120,5,false,1);
							Tir tir4 = new Tir(1,300,10,315,110,120,5,false,1);
							Tir tir5 = new Tir(1,300,10,45,20,120,5,false,1);
							Tir tir6 = new Tir(1,300,10,0,110,120,5,false,1);
							Tir tir7 = new Tir(1,300,10,315,20,120,5,false,1);
							Tir tir8 = new Tir(1,300,10,45,110,120,5,false,1);
						maSequence.getMaListeTir().add(tir1);
						maSequence.getMaListeTir().add(tir2);
						maSequence.getMaListeTir().add(tir3);
						maSequence.getMaListeTir().add(tir4);
						maSequence.getMaListeTir().add(tir5);
						maSequence.getMaListeTir().add(tir6);
						maSequence.getMaListeTir().add(tir7);
						maSequence.getMaListeTir().add(tir8);
						this.unTemplate.add(maSequence);
					SequenceTir maSequence2 = new SequenceTir(40);
						Tir tir12 = new Tir(1,200,10,0,5,false,1);
						Tir tir22 = new Tir(1,200,10,35,5,false,1);
						Tir tir32 = new Tir(1,200,10,325,5,false,1);
					maSequence.getMaListeTir().add(tir12);
					maSequence.getMaListeTir().add(tir22);
					maSequence.getMaListeTir().add(tir32);
					this.unTemplate.add(maSequence2);
					break;
				}
				
			  case 1:
			  {
			  SequenceTir maSequence = new SequenceTir(100);
			  // 2 missile frontal
					Tir tir1 = new Tir(2,2000,30,0,-30,-20,3,false,1);
					Tir tir2 = new Tir(2,2000,30,0,30,-20,3,false,1);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
				// 4 tir en V	
					Tir tir4 = new Tir(1,800,10,330,30,-20,5,false,2);
					Tir tir7 = new Tir(1,800,10,330,-30,-20,5,false,2);
					
					Tir tir5 = new Tir(1,800,10,30,30,-20,5,false,2);
					Tir tir8 = new Tir(1,800,10,30,-30,-20,5,false,2);
					
					Rafale rafale2 = new Rafale(48,1000);
					maSequence.getMaListeRafale().put(2,rafale2);
					maSequence.getMaListeTir().add(tir4);
					maSequence.getMaListeTir().add(tir5);
				
					maSequence.getMaListeTir().add(tir7);
					maSequence.getMaListeTir().add(tir8);
				this.unTemplate.add(maSequence);
				
				SequenceTir maSequence2 = new SequenceTir(40);
				// 2 tir en croisé
						Tir tir22 = new Tir(1,100,10,35,-35,-20,5,false,3);
						Tir tir32 = new Tir(1,100,10,325,35,-20,5,false,3);
						Rafale rafale3 = new Rafale(20,1500);
						maSequence2.getMaListeRafale().put(3,rafale3);
						maSequence2.getMaListeTir().add(tir22);
						maSequence2.getMaListeTir().add(tir32);
				// 1 laser tres rapide		
						Tir tir42=  new Tir(3,200,70,0,0,0,12,false,4);
						Rafale rafale4 = new Rafale(3,3000);
					maSequence2.getMaListeRafale().put(4,rafale4);
					maSequence2.getMaListeTir().add(tir42);
					this.unTemplate.add(maSequence2);
					break;
				  	  }	
			  case 2:
			  {
				  SequenceTir maSequence2 = new SequenceTir(100);
				  //1 tir autovisé
				  Tir tir21 = new Tir(1,150,10,0,-70,-30,8,true,3);
				  Tir tir22 = new Tir(1,150,10,0,70,-30,8,true,3);
				  Rafale rafale1 = new Rafale(6,1000);
				  maSequence2.getMaListeRafale().put(3, rafale1);
				  maSequence2.getMaListeTir().add(tir21);
				  maSequence2.getMaListeTir().add(tir22);
				
				  Tir tir23 = new Tir(3,2000,60,0,0,-15,11,false,4);
				  maSequence2.getMaListeTir().add(tir23);
					
				  this.unTemplate.add(maSequence2);
				  
				  // type,cadence,dgt,angle,x,y,vit,auto,grp)
				  SequenceTir maSequence = new SequenceTir(50);
				  //1 tir autovisé
				  Tir tir11 = new Tir(1,800,10,0,0,-20,7,true,1);
				  Tir tir112 = new Tir(1,800,10,0,0,-20,6,true,1);
				  Tir tir113 = new Tir(1,800,10,0,0,-20,5,true,1);
				  maSequence.getMaListeTir().add(tir11);
				  maSequence.getMaListeTir().add(tir112);
				  maSequence.getMaListeTir().add(tir113);
				  
					Tir tir12 = new Tir(3,1300,40,0,-60,-25,5,false,2);
					Tir tir13 = new Tir(3,1300,40,0,60,-25,5,false,2);
					
					maSequence.getMaListeTir().add(tir12);
					maSequence.getMaListeTir().add(tir13);
					this.unTemplate.add(maSequence);
					
				
					break;		
			  }	
			  case 3:
			  {
				  SequenceTir maSequence = new SequenceTir(100);
				  	Tir tir1 = new Tir(1,200,10,0,0,-20,5,true,1);
					Tir tir2 = new Tir(1,1200,5,45,0,-20,3,false,2);
					Tir tir3 = new Tir(1,1200,5,315,0,-20,3,false,2);
					Tir tir4 = new Tir(1,1200,5,30,0,-20,3,false,2);
					Tir tir5 = new Tir(1,1200,5,330,0,-20,3,false,2);
					Rafale rafale = new Rafale(4,2000);
					maSequence.getMaListeRafale().put(1, rafale);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					maSequence.getMaListeTir().add(tir3);
					maSequence.getMaListeTir().add(tir4);
					maSequence.getMaListeTir().add(tir5);
					
					this.unTemplate.add(maSequence);
					
					SequenceTir maSequence2 = new SequenceTir(20);
				  	Tir tir21 = new Tir(1,500,12,0,0,-20,5,true,3);
					Tir tir23 = new Tir(1,800,12,345,0,-20,5,false,4);
					Tir tir24 = new Tir(1,800,12,15,0,-20,5,false,4);
					
					maSequence2.getMaListeTir().add(tir21);
					maSequence2.getMaListeTir().add(tir23);
					maSequence2.getMaListeTir().add(tir24);
					
					Tir tir26 = new Tir(3,150,40,0,-56,-76,8,false,5);
					Tir tir27 = new Tir(3,150,40,0,56,-76,8,false,5);
					Rafale rafale2 = new Rafale(6,1500);
					maSequence2.getMaListeRafale().put(5, rafale2);
					maSequence2.getMaListeTir().add(tir26);
					maSequence2.getMaListeTir().add(tir27);
					
					this.unTemplate.add(maSequence2);
					
					break;		  
			  }	
			  case 4:
			  {
				  SequenceTir maSequence = new SequenceTir(100);
				  Tir tir1 = new Tir(1,100,10,90,80,-70,8,false,1);
				  Tir tir2 = new Tir(1,100,10,80,80,-70,8,false,1);
				  Tir tir3 = new Tir(1,100,10,70,80,-70,8,false,1);
				  Tir tir4 = new Tir(1,100,10,270,-80,-70,8,false,1);
				  Tir tir5 = new Tir(1,100,10,280,-80,-70,8,false,1);
				  Tir tir6 = new Tir(1,100,10,290,-80,-70,8,false,1);
				  
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					maSequence.getMaListeTir().add(tir3);
					maSequence.getMaListeTir().add(tir4);
					maSequence.getMaListeTir().add(tir5);
					maSequence.getMaListeTir().add(tir6);
				
					Tir tir7 = new Tir(2,1000,50,0,-16,-20,10,true,2);
					Tir tir8 = new Tir(2,1000,50,0,16,-20,10,true,2);
					
					maSequence.getMaListeTir().add(tir7);
					maSequence.getMaListeTir().add(tir8);
					
					this.unTemplate.add(maSequence);
					
					SequenceTir maSequence2 = new SequenceTir(50);
					  Tir tir21 = new Tir(1,100,10,60,80,-70,8,false,3);
					  Tir tir22 = new Tir(1,100,10,50,80,-70,8,false,3);
					  Tir tir23 = new Tir(1,100,10,40,80,-70,8,false,3);
					  Tir tir24 = new Tir(1,100,10,300,-80,-70,8,false,3);
					  Tir tir25 = new Tir(1,100,10,310,-80,-70,8,false,3);
					  Tir tir26 = new Tir(1,100,10,320,-80,-70,8,false,3);
					  
						maSequence2.getMaListeTir().add(tir21);
						maSequence2.getMaListeTir().add(tir22);
						maSequence2.getMaListeTir().add(tir23);
						maSequence2.getMaListeTir().add(tir24);
						maSequence2.getMaListeTir().add(tir25);
						maSequence2.getMaListeTir().add(tir26);
					
						Tir tir27 = new Tir(2,900,50,0,-16,-20,11,true,4);
						Tir tir28 = new Tir(2,900,50,0,16,-20,11,true,4);
						
						maSequence2.getMaListeTir().add(tir27);
						maSequence2.getMaListeTir().add(tir28);
						
						this.unTemplate.add(maSequence2);
					
					SequenceTir maSequence3 = new SequenceTir(20);
					  Tir tir31 = new Tir(1,100,10,50,80,-70,8,false,5);
					  Tir tir32 = new Tir(1,100,10,40,80,-70,8,false,5);
					  Tir tir33 = new Tir(1,100,10,30,80,-70,8,false,5);
					  Tir tir34 = new Tir(1,100,10,310,-80,-70,8,false,5);
					  Tir tir35 = new Tir(1,100,10,320,-80,-70,8,false,5);
					  Tir tir36 = new Tir(1,100,10,330,-80,-70,8,false,5);
					  
						maSequence3.getMaListeTir().add(tir31);
						maSequence3.getMaListeTir().add(tir32);
						maSequence3.getMaListeTir().add(tir33);
						maSequence3.getMaListeTir().add(tir34);
						maSequence3.getMaListeTir().add(tir35);
						maSequence3.getMaListeTir().add(tir36);
					
						Tir tir37 = new Tir(2,900,50,0,0,-20,12,true,6);
						//Tir tir38 = new Tir(2,800,50,0,60,-20,12,true,6);
						
						maSequence3.getMaListeTir().add(tir37);
						//maSequence3.getMaListeTir().add(tir38);
						
						this.unTemplate.add(maSequence3);
					break;		  
			  }	
			  case 5:
			  {
				  SequenceTir maSequence = new SequenceTir(100);
				  	Tir tir1 = new Tir(1,100,5,315,-55,-75,4,false,1);
					Tir tir2 = new Tir(1,100,5,45,55,-75,4,false,1);
					Rafale rafale1 = new Rafale(2,2000);
					maSequence.getMaListeRafale().put(1, rafale1);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					
					Tir tir3 = new Tir(1,100,5,330,-55,-75,4,false,2);
					Tir tir4 = new Tir(1,100,5,30,55,-75,4,false,2);
					Rafale rafale2 = new Rafale(2,2000,200);
					maSequence.getMaListeRafale().put(2, rafale2);
					maSequence.getMaListeTir().add(tir3);
					maSequence.getMaListeTir().add(tir4);
					
					Tir tir5 = new Tir(1,100,5,345,-55,-75,4,false,3);
					Tir tir6 = new Tir(1,100,5,15,55,-75,4,false,3);
					Rafale rafale3 = new Rafale(2,2000,400);
					maSequence.getMaListeRafale().put(3, rafale3);
					maSequence.getMaListeTir().add(tir5);
					maSequence.getMaListeTir().add(tir6);
					
					Tir tir7 = new Tir(1,100,5,0,-55,-75,4,false,4);
					Tir tir8 = new Tir(1,100,5,0,55,-75,4,false,4);
					Rafale rafale4 = new Rafale(2,2000,600);
					maSequence.getMaListeRafale().put(4, rafale4);
					maSequence.getMaListeTir().add(tir7);
					maSequence.getMaListeTir().add(tir8);
					
					Tir tir9 = new Tir(1,100,5,15,-55,-75,4,false,5);
					Tir tir10 = new Tir(1,100,5,345,55,-75,4,false,5);
					Rafale rafale5 = new Rafale(2,2000,800);
					maSequence.getMaListeRafale().put(5, rafale5);
					maSequence.getMaListeTir().add(tir9);
					maSequence.getMaListeTir().add(tir10);
					
					Tir tir11 = new Tir(1,100,5,30,-55,-75,4,false,6);
					Tir tir12 = new Tir(1,100,5,330,55,-75,4,false,6);
					Rafale rafale6 = new Rafale(2,2000,1000);
					maSequence.getMaListeRafale().put(6, rafale6);
					maSequence.getMaListeTir().add(tir11);
					maSequence.getMaListeTir().add(tir12);
					
					Tir tir13 = new Tir(1,100,5,45,-55,-75,4,false,7);
					Tir tir14 = new Tir(1,100,5,315,55,-75,4,false,7);
					Rafale rafale7 = new Rafale(2,2000,1200);
					maSequence.getMaListeRafale().put(7, rafale7);
					maSequence.getMaListeTir().add(tir13);
					maSequence.getMaListeTir().add(tir14);
					//70-150 70-150
					Tir tir20 = new Tir(2,3000,40,0,7,-25,5,false,8);
					Tir tir21 = new Tir(2,3000,40,0,-7,-25,5,false,8);
					Rafale rafale8 = new Rafale(2,0,4000);
					maSequence.getMaListeRafale().put(8, rafale8);
					maSequence.getMaListeTir().add(tir20);
					maSequence.getMaListeTir().add(tir21);
					
					this.unTemplate.add(maSequence);
					
					SequenceTir maSequence2 = new SequenceTir(50);
					Tir tir26 = new Tir(2,1800,25,15,60,-80,7,false,9);
					Tir tir28 = new Tir(2,1800,25,355,50,-80,5,false,9);
					
					Tir tir27 = new Tir(2,1800,25,5,-50,-80,5,false,9);
					Tir tir29 = new Tir(2,1800,25,350,-60,-80,7,false,9);
					maSequence2.getMaListeTir().add(tir26);
					maSequence2.getMaListeTir().add(tir28);
					maSequence2.getMaListeTir().add(tir27);					
					maSequence2.getMaListeTir().add(tir29);
					
					Tir tir30 = new Tir(1,200,5,0,7,-12,10,false,10);
					Tir tir31 = new Tir(1,200,5,0,-7,-12,10,false,10);
					Rafale rafale10 = new Rafale(8,2500);
					maSequence2.getMaListeRafale().put(10, rafale10);
					maSequence2.getMaListeTir().add(tir30);
					maSequence2.getMaListeTir().add(tir31);
					
					this.unTemplate.add(maSequence2);
					break;		  
			  }	
			  case 6:
			  {
				  SequenceTir maSequence = new SequenceTir(100);
				  	Tir tir1 = new Tir(1,1600,3,0,13,-5,5,false,1);
					Tir tir2 = new Tir(1,1600,3,0,0,-5,5,false,1);
					Tir tir3 = new Tir(1,1600,3,0,-13,-5,5,false,1);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					maSequence.getMaListeTir().add(tir3);
					
					Tir tir4 = new Tir(1,500,1,0,-65,-90,8,true,2);
					Tir tir5 = new Tir(1,500,1,0,65,-90,8,true,2);
					maSequence.getMaListeTir().add(tir4);
					maSequence.getMaListeTir().add(tir5);
					
					Tir tir6 = new Tir(1,400,1,0,-30,-30,7,true,3);
					Tir tir7 = new Tir(1,400,1,0,30,-30,7,true,3);
					maSequence.getMaListeTir().add(tir6);
					maSequence.getMaListeTir().add(tir7);
					
					this.unTemplate.add(maSequence);
					
					SequenceTir maSequence2 = new SequenceTir(20);
				  	Tir tir21 = new Tir(1,800,3,0,13,-5,10,false,4);
					Tir tir22 = new Tir(1,800,3,0,0,-5,8,false,4);
					Tir tir23 = new Tir(1,800,3,0,-13,-5,10,false,4);
					maSequence2.getMaListeTir().add(tir21);
					maSequence2.getMaListeTir().add(tir22);
					maSequence2.getMaListeTir().add(tir23);
					
					Tir tir24 = new Tir(1,300,2,0,-65,-90,11,true,5);
					Tir tir25 = new Tir(1,300,2,0,65,-90,11,true,5);
					maSequence2.getMaListeTir().add(tir24);
					maSequence2.getMaListeTir().add(tir25);
					
					Tir tir26 = new Tir(1,200,2,0,-30,-30,10,true,6);
					Tir tir27 = new Tir(1,200,2,0,30,-30,10,true,6);
					maSequence2.getMaListeTir().add(tir26);
					maSequence2.getMaListeTir().add(tir27);
					
					this.unTemplate.add(maSequence2);
					
					
					break;		  
			  }	
			  case 7:
			  {
				  SequenceTir maSequence = new SequenceTir(100);
				  	Tir tir1 = new Tir(5,2000,10,0,-55,-70,5,false,1);
					Tir tir2 = new Tir(5,2000,10,0,55,-70,5,false,1);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);


					Tir tir7 = new Tir(2,1400,35,0,20,-50,6,true,2);
					Tir tir8 = new Tir(2,1400,35,0,-20,-50,6,true,2);
					
					maSequence.getMaListeTir().add(tir7);
					maSequence.getMaListeTir().add(tir8);	
					
					Tir tir3 = new Tir(1,600,15,160,45,-150,5,false,3);
					Tir tir4 = new Tir(1,600,15,200,45,-150,5,false,3);
					
					Tir tir5 = new Tir(1,600,15,200,-45,-150,5,false,3);
					Tir tir6 = new Tir(1,600,15,160,-45,-150,5,false,3);
					maSequence.getMaListeTir().add(tir3);
					maSequence.getMaListeTir().add(tir4);
					maSequence.getMaListeTir().add(tir5);
					maSequence.getMaListeTir().add(tir6);
					
					
					this.unTemplate.add(maSequence);
					
				SequenceTir maSequence2 = new SequenceTir(40);
					Tir tir21 = new Tir(6,1000,40,340,-55,-70,8,false,10);
					Tir tir22 = new Tir(6,1000,40,20,55,-70,8,false,10);
					Tir tir23 = new Tir(6,1000,40,0,0,-20,8,false,10);
					Rafale rafale1 = new Rafale(12,2000);
					maSequence2.getMaListeRafale().put(10, rafale1);
					
					maSequence2.getMaListeTir().add(tir21);
					maSequence2.getMaListeTir().add(tir22);
					maSequence2.getMaListeTir().add(tir23);
					
				this.unTemplate.add(maSequence2);
				break;		  
			  }	
			  case 8:
			  {
				  SequenceTir maSequence = new SequenceTir(100);
				  	Tir tir1 = new Tir(2,600,60,0,-50,-60,10,true,1);
					Tir tir2 = new Tir(2,600,60,0,50,-60,10,true,1);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					
					Tir tir3 = new Tir(2,400,60,0,-65,-80,11,true,2);
					Tir tir4 = new Tir(2,400,60,0,65,-80,11,true,2);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					
					Tir tir5 = new Tir(2,500,60,0,-87,-120,12,true,3);
					Tir tir6 = new Tir(2,500,60,0,87,-120,12,true,3);
					
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					maSequence.getMaListeTir().add(tir3);
					maSequence.getMaListeTir().add(tir4);
					maSequence.getMaListeTir().add(tir5);
					maSequence.getMaListeTir().add(tir6);
					
					this.unTemplate.add(maSequence);
					break;		  
			  }	
			  case 9:
			  {
				  SequenceTir maSequence = new SequenceTir(100);
				  	Tir tir1 = new Tir(2,1500,20,0,ThePanel.MonAvion.getWidth()-40,0,3,false,1);
					Tir tir2 = new Tir(2,1500,20,0,20,0,3,false,1);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					this.unTemplate.add(maSequence);
					break;		  
			  }	
			  case 10:
			  {
				  SequenceTir maSequence = new SequenceTir(100);
				  	Tir tir1 = new Tir(2,1500,20,0,ThePanel.MonAvion.getWidth()-40,0,3,false,1);
					Tir tir2 = new Tir(2,1500,20,0,20,0,3,false,1);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					this.unTemplate.add(maSequence);
					break;		  
			  }	
			}
		}
		else // is mini boss
		{
			switch (pTemplate) 
			{
				case 100:
				{
					SequenceTir maSequence = new SequenceTir(100);
							Tir tir1 = new Tir(1,300,10,0,90,0,5,false,1);
							Tir tir2 = new Tir(1,300,10,0,33,0,5,false,1);
							Tir tir3 = new Tir(1,300,10,0,20,120,5,false,1);
							Tir tir4 = new Tir(1,300,10,315,110,120,5,false,1);
							Tir tir5 = new Tir(1,300,10,45,20,120,5,false,1);
							Tir tir6 = new Tir(1,300,10,0,110,120,5,false,1);
							Tir tir7 = new Tir(1,300,10,315,20,120,5,false,1);
							Tir tir8 = new Tir(1,300,10,45,110,120,5,false,1);
						maSequence.getMaListeTir().add(tir1);
						maSequence.getMaListeTir().add(tir2);
						maSequence.getMaListeTir().add(tir3);
						maSequence.getMaListeTir().add(tir4);
						maSequence.getMaListeTir().add(tir5);
						maSequence.getMaListeTir().add(tir6);
						maSequence.getMaListeTir().add(tir7);
						maSequence.getMaListeTir().add(tir8);
						this.unTemplate.add(maSequence);
					SequenceTir maSequence2 = new SequenceTir(40);
						Tir tir12 = new Tir(1,200,10,0,5,false,1);
						Tir tir22 = new Tir(1,200,10,35,5,false,1);
						Tir tir32 = new Tir(1,200,10,325,5,false,1);
					maSequence.getMaListeTir().add(tir12);
					maSequence.getMaListeTir().add(tir22);
					maSequence.getMaListeTir().add(tir32);
					this.unTemplate.add(maSequence2);
					break;
				}
				
			  case 101:
			  {
			  SequenceTir maSequence = new SequenceTir(100);
			  // 2 missile frontal
					Tir tir1 = new Tir(2,4000,30,0,-30,-20,3,false,1);
					Tir tir2 = new Tir(2,4000,30,0,30,-20,3,false,1);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
				// 4 tir en V	
					Tir tir4 = new Tir(1,1200,10,330,30,-20,5,false,2);
					Tir tir7 = new Tir(1,1200,10,330,-30,-20,5,false,2);
					
					Tir tir5 = new Tir(1,1200,10,30,30,-20,5,false,2);
					Tir tir8 = new Tir(1,1200,10,30,-30,-20,5,false,2);
					
					Rafale rafale2 = new Rafale(30,1000);
					maSequence.getMaListeRafale().put(2,rafale2);
					maSequence.getMaListeTir().add(tir4);
					maSequence.getMaListeTir().add(tir5);
				
					maSequence.getMaListeTir().add(tir7);
					maSequence.getMaListeTir().add(tir8);
				this.unTemplate.add(maSequence);
				
				SequenceTir maSequence2 = new SequenceTir(40);
				// 2 tir en croisé
						Tir tir22 = new Tir(1,200,10,35,-35,-20,5,false,3);
						Tir tir32 = new Tir(1,200,10,325,35,-20,5,false,3);
						Rafale rafale3 = new Rafale(10,1500);
						maSequence2.getMaListeRafale().put(3,rafale3);
						maSequence2.getMaListeTir().add(tir22);
						maSequence2.getMaListeTir().add(tir32);
				// 1 laser tres rapide		
						Tir tir42=  new Tir(3,600,70,0,0,0,12,false,4);
						Rafale rafale4 = new Rafale(3,3000);
					maSequence2.getMaListeRafale().put(4,rafale4);
					maSequence2.getMaListeTir().add(tir42);
					this.unTemplate.add(maSequence2);
					break;
				  	  }	
			  case 102:
			  {
				  SequenceTir maSequence2 = new SequenceTir(100);
				  //1 tir autovisé
				  Tir tir21 = new Tir(1,150,10,0,-70,-30,8,true,3);
				  Tir tir22 = new Tir(1,150,10,0,70,-30,8,true,3);
				  Rafale rafale1 = new Rafale(6,1000);
				  maSequence2.getMaListeRafale().put(3, rafale1);
				  maSequence2.getMaListeTir().add(tir21);
				  maSequence2.getMaListeTir().add(tir22);
				
				  Tir tir23 = new Tir(3,2000,60,0,0,-15,11,false,4);
				  maSequence2.getMaListeTir().add(tir23);
					
				  this.unTemplate.add(maSequence2);
				  
				  // type,cadence,dgt,angle,x,y,vit,auto,grp)
				  SequenceTir maSequence = new SequenceTir(50);
				  //1 tir autovisé
				  Tir tir11 = new Tir(1,800,10,0,0,-20,7,true,1);
				  Tir tir112 = new Tir(1,800,10,0,0,-20,6,true,1);
				  Tir tir113 = new Tir(1,800,10,0,0,-20,5,true,1);
				  maSequence.getMaListeTir().add(tir11);
				  maSequence.getMaListeTir().add(tir112);
				  maSequence.getMaListeTir().add(tir113);
				  
					Tir tir12 = new Tir(3,1300,40,0,-60,-25,5,false,2);
					Tir tir13 = new Tir(3,1300,40,0,60,-25,5,false,2);
					
					maSequence.getMaListeTir().add(tir12);
					maSequence.getMaListeTir().add(tir13);
					this.unTemplate.add(maSequence);
					
				
					break;		
			  }	
			  case 103:
			  {
				  SequenceTir maSequence = new SequenceTir(100);
				  	Tir tir1 = new Tir(1,200,10,0,0,-20,5,true,1);
					Tir tir2 = new Tir(1,1200,5,45,0,-20,3,false,2);
					Tir tir3 = new Tir(1,1200,5,315,0,-20,3,false,2);
					Tir tir4 = new Tir(1,1200,5,30,0,-20,3,false,2);
					Tir tir5 = new Tir(1,1200,5,330,0,-20,3,false,2);
					Rafale rafale = new Rafale(4,2000);
					maSequence.getMaListeRafale().put(1, rafale);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					maSequence.getMaListeTir().add(tir3);
					maSequence.getMaListeTir().add(tir4);
					maSequence.getMaListeTir().add(tir5);
					
					this.unTemplate.add(maSequence);
					
					SequenceTir maSequence2 = new SequenceTir(20);
				  	Tir tir21 = new Tir(1,500,12,0,0,-20,5,true,3);
					Tir tir23 = new Tir(1,800,12,345,0,-20,5,false,4);
					Tir tir24 = new Tir(1,800,12,15,0,-20,5,false,4);
					
					maSequence2.getMaListeTir().add(tir21);
					maSequence2.getMaListeTir().add(tir23);
					maSequence2.getMaListeTir().add(tir24);
					
					Tir tir26 = new Tir(3,150,40,0,-56,-76,8,false,5);
					Tir tir27 = new Tir(3,150,40,0,56,-76,8,false,5);
					Rafale rafale2 = new Rafale(6,1500);
					maSequence2.getMaListeRafale().put(5, rafale2);
					maSequence2.getMaListeTir().add(tir26);
					maSequence2.getMaListeTir().add(tir27);
					
					this.unTemplate.add(maSequence2);
					
					break;		  
			  }	
			  case 104:
			  {
				  SequenceTir maSequence = new SequenceTir(100);
				  Tir tir1 = new Tir(1,100,10,90,80,-70,8,false,1);
				  Tir tir2 = new Tir(1,100,10,80,80,-70,8,false,1);
				  Tir tir3 = new Tir(1,100,10,70,80,-70,8,false,1);
				  Tir tir4 = new Tir(1,100,10,270,-80,-70,8,false,1);
				  Tir tir5 = new Tir(1,100,10,280,-80,-70,8,false,1);
				  Tir tir6 = new Tir(1,100,10,290,-80,-70,8,false,1);
				  
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					maSequence.getMaListeTir().add(tir3);
					maSequence.getMaListeTir().add(tir4);
					maSequence.getMaListeTir().add(tir5);
					maSequence.getMaListeTir().add(tir6);
				
					Tir tir7 = new Tir(2,1000,50,0,-16,-20,10,true,2);
					Tir tir8 = new Tir(2,1000,50,0,16,-20,10,true,2);
					
					maSequence.getMaListeTir().add(tir7);
					maSequence.getMaListeTir().add(tir8);
					
					this.unTemplate.add(maSequence);
					
					SequenceTir maSequence2 = new SequenceTir(50);
					  Tir tir21 = new Tir(1,100,10,60,80,-70,8,false,3);
					  Tir tir22 = new Tir(1,100,10,50,80,-70,8,false,3);
					  Tir tir23 = new Tir(1,100,10,40,80,-70,8,false,3);
					  Tir tir24 = new Tir(1,100,10,300,-80,-70,8,false,3);
					  Tir tir25 = new Tir(1,100,10,310,-80,-70,8,false,3);
					  Tir tir26 = new Tir(1,100,10,320,-80,-70,8,false,3);
					  
						maSequence2.getMaListeTir().add(tir21);
						maSequence2.getMaListeTir().add(tir22);
						maSequence2.getMaListeTir().add(tir23);
						maSequence2.getMaListeTir().add(tir24);
						maSequence2.getMaListeTir().add(tir25);
						maSequence2.getMaListeTir().add(tir26);
					
						Tir tir27 = new Tir(2,900,50,0,-16,-20,11,true,4);
						Tir tir28 = new Tir(2,900,50,0,16,-20,11,true,4);
						
						maSequence2.getMaListeTir().add(tir27);
						maSequence2.getMaListeTir().add(tir28);
						
						this.unTemplate.add(maSequence2);
					
					SequenceTir maSequence3 = new SequenceTir(20);
					  Tir tir31 = new Tir(1,100,10,50,80,-70,8,false,5);
					  Tir tir32 = new Tir(1,100,10,40,80,-70,8,false,5);
					  Tir tir33 = new Tir(1,100,10,30,80,-70,8,false,5);
					  Tir tir34 = new Tir(1,100,10,310,-80,-70,8,false,5);
					  Tir tir35 = new Tir(1,100,10,320,-80,-70,8,false,5);
					  Tir tir36 = new Tir(1,100,10,330,-80,-70,8,false,5);
					  
						maSequence3.getMaListeTir().add(tir31);
						maSequence3.getMaListeTir().add(tir32);
						maSequence3.getMaListeTir().add(tir33);
						maSequence3.getMaListeTir().add(tir34);
						maSequence3.getMaListeTir().add(tir35);
						maSequence3.getMaListeTir().add(tir36);
					
						Tir tir37 = new Tir(2,900,50,0,0,-20,12,true,6);
						//Tir tir38 = new Tir(2,800,50,0,60,-20,12,true,6);
						
						maSequence3.getMaListeTir().add(tir37);
						//maSequence3.getMaListeTir().add(tir38);
						
						this.unTemplate.add(maSequence3);
					break;		  
			  }	
			  case 105:
			  {
				  SequenceTir maSequence = new SequenceTir(100);
				  	Tir tir1 = new Tir(1,100,5,315,-55,-75,4,false,1);
					Tir tir2 = new Tir(1,100,5,45,55,-75,4,false,1);
					Rafale rafale1 = new Rafale(2,2000);
					maSequence.getMaListeRafale().put(1, rafale1);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					
					Tir tir3 = new Tir(1,100,5,330,-55,-75,4,false,2);
					Tir tir4 = new Tir(1,100,5,30,55,-75,4,false,2);
					Rafale rafale2 = new Rafale(2,2000,200);
					maSequence.getMaListeRafale().put(2, rafale2);
					maSequence.getMaListeTir().add(tir3);
					maSequence.getMaListeTir().add(tir4);
					
					Tir tir5 = new Tir(1,100,5,345,-55,-75,4,false,3);
					Tir tir6 = new Tir(1,100,5,15,55,-75,4,false,3);
					Rafale rafale3 = new Rafale(2,2000,400);
					maSequence.getMaListeRafale().put(3, rafale3);
					maSequence.getMaListeTir().add(tir5);
					maSequence.getMaListeTir().add(tir6);
					
					Tir tir7 = new Tir(1,100,5,0,-55,-75,4,false,4);
					Tir tir8 = new Tir(1,100,5,0,55,-75,4,false,4);
					Rafale rafale4 = new Rafale(2,2000,600);
					maSequence.getMaListeRafale().put(4, rafale4);
					maSequence.getMaListeTir().add(tir7);
					maSequence.getMaListeTir().add(tir8);
					
					Tir tir9 = new Tir(1,100,5,15,-55,-75,4,false,5);
					Tir tir10 = new Tir(1,100,5,345,55,-75,4,false,5);
					Rafale rafale5 = new Rafale(2,2000,800);
					maSequence.getMaListeRafale().put(5, rafale5);
					maSequence.getMaListeTir().add(tir9);
					maSequence.getMaListeTir().add(tir10);
					
					Tir tir11 = new Tir(1,100,5,30,-55,-75,4,false,6);
					Tir tir12 = new Tir(1,100,5,330,55,-75,4,false,6);
					Rafale rafale6 = new Rafale(2,2000,1000);
					maSequence.getMaListeRafale().put(6, rafale6);
					maSequence.getMaListeTir().add(tir11);
					maSequence.getMaListeTir().add(tir12);
					
					Tir tir13 = new Tir(1,100,5,45,-55,-75,4,false,7);
					Tir tir14 = new Tir(1,100,5,315,55,-75,4,false,7);
					Rafale rafale7 = new Rafale(2,2000,1200);
					maSequence.getMaListeRafale().put(7, rafale7);
					maSequence.getMaListeTir().add(tir13);
					maSequence.getMaListeTir().add(tir14);
					//70-150 70-150
					Tir tir20 = new Tir(2,3000,40,0,7,-25,5,false,8);
					Tir tir21 = new Tir(2,3000,40,0,-7,-25,5,false,8);
					Rafale rafale8 = new Rafale(2,0,4000);
					maSequence.getMaListeRafale().put(8, rafale8);
					maSequence.getMaListeTir().add(tir20);
					maSequence.getMaListeTir().add(tir21);
					
					this.unTemplate.add(maSequence);
					
					SequenceTir maSequence2 = new SequenceTir(50);
					Tir tir26 = new Tir(2,1800,25,15,60,-80,7,false,9);
					Tir tir28 = new Tir(2,1800,25,355,50,-80,5,false,9);
					
					Tir tir27 = new Tir(2,1800,25,5,-50,-80,5,false,9);
					Tir tir29 = new Tir(2,1800,25,350,-60,-80,7,false,9);
					maSequence2.getMaListeTir().add(tir26);
					maSequence2.getMaListeTir().add(tir28);
					maSequence2.getMaListeTir().add(tir27);					
					maSequence2.getMaListeTir().add(tir29);
					
					Tir tir30 = new Tir(1,200,5,0,7,-12,10,false,10);
					Tir tir31 = new Tir(1,200,5,0,-7,-12,10,false,10);
					Rafale rafale10 = new Rafale(8,2500);
					maSequence2.getMaListeRafale().put(10, rafale10);
					maSequence2.getMaListeTir().add(tir30);
					maSequence2.getMaListeTir().add(tir31);
					
					this.unTemplate.add(maSequence2);
					break;		  
			  }	
			  case 106:
			  {
				  SequenceTir maSequence = new SequenceTir(100);
				  	Tir tir1 = new Tir(1,1600,3,0,13,-5,5,false,1);
					Tir tir2 = new Tir(1,1600,3,0,0,-5,5,false,1);
					Tir tir3 = new Tir(1,1600,3,0,-13,-5,5,false,1);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					maSequence.getMaListeTir().add(tir3);
					
					Tir tir4 = new Tir(1,500,1,0,-65,-90,8,true,2);
					Tir tir5 = new Tir(1,500,1,0,65,-90,8,true,2);
					maSequence.getMaListeTir().add(tir4);
					maSequence.getMaListeTir().add(tir5);
					
					Tir tir6 = new Tir(1,400,1,0,-30,-30,7,true,3);
					Tir tir7 = new Tir(1,400,1,0,30,-30,7,true,3);
					maSequence.getMaListeTir().add(tir6);
					maSequence.getMaListeTir().add(tir7);
					
					this.unTemplate.add(maSequence);
					
					SequenceTir maSequence2 = new SequenceTir(20);
				  	Tir tir21 = new Tir(1,800,3,0,13,-5,10,false,4);
					Tir tir22 = new Tir(1,800,3,0,0,-5,8,false,4);
					Tir tir23 = new Tir(1,800,3,0,-13,-5,10,false,4);
					maSequence2.getMaListeTir().add(tir21);
					maSequence2.getMaListeTir().add(tir22);
					maSequence2.getMaListeTir().add(tir23);
					
					Tir tir24 = new Tir(1,300,2,0,-65,-90,11,true,5);
					Tir tir25 = new Tir(1,300,2,0,65,-90,11,true,5);
					maSequence2.getMaListeTir().add(tir24);
					maSequence2.getMaListeTir().add(tir25);
					
					Tir tir26 = new Tir(1,200,2,0,-30,-30,10,true,6);
					Tir tir27 = new Tir(1,200,2,0,30,-30,10,true,6);
					maSequence2.getMaListeTir().add(tir26);
					maSequence2.getMaListeTir().add(tir27);
					
					this.unTemplate.add(maSequence2);
					
					
					break;		  
			  }	
			  case 107:
			  {
				  SequenceTir maSequence = new SequenceTir(100);
				  	Tir tir1 = new Tir(5,2000,10,0,-55,-70,5,false,1);
					Tir tir2 = new Tir(5,2000,10,0,55,-70,5,false,1);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);


					Tir tir7 = new Tir(2,1400,35,0,20,-50,6,true,2);
					Tir tir8 = new Tir(2,1400,35,0,-20,-50,6,true,2);
					
					maSequence.getMaListeTir().add(tir7);
					maSequence.getMaListeTir().add(tir8);	
					
					Tir tir3 = new Tir(1,600,15,160,45,-150,5,false,3);
					Tir tir4 = new Tir(1,600,15,200,45,-150,5,false,3);
					
					Tir tir5 = new Tir(1,600,15,200,-45,-150,5,false,3);
					Tir tir6 = new Tir(1,600,15,160,-45,-150,5,false,3);
					maSequence.getMaListeTir().add(tir3);
					maSequence.getMaListeTir().add(tir4);
					maSequence.getMaListeTir().add(tir5);
					maSequence.getMaListeTir().add(tir6);
					
					
					this.unTemplate.add(maSequence);
					
				SequenceTir maSequence2 = new SequenceTir(40);
					Tir tir21 = new Tir(6,1000,40,340,-55,-70,8,false,10);
					Tir tir22 = new Tir(6,1000,40,20,55,-70,8,false,10);
					Tir tir23 = new Tir(6,1000,40,0,0,-20,8,false,10);
					Rafale rafale1 = new Rafale(12,2000);
					maSequence2.getMaListeRafale().put(10, rafale1);
					
					maSequence2.getMaListeTir().add(tir21);
					maSequence2.getMaListeTir().add(tir22);
					maSequence2.getMaListeTir().add(tir23);
					
				this.unTemplate.add(maSequence2);
				break;		  
			  }	
			  case 108:
			  {
				  SequenceTir maSequence = new SequenceTir(100);
				  	Tir tir1 = new Tir(2,600,60,0,-50,-60,10,true,1);
					Tir tir2 = new Tir(2,600,60,0,50,-60,10,true,1);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					
					Tir tir3 = new Tir(2,400,60,0,-65,-80,11,true,2);
					Tir tir4 = new Tir(2,400,60,0,65,-80,11,true,2);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					
					Tir tir5 = new Tir(2,500,60,0,-87,-120,12,true,3);
					Tir tir6 = new Tir(2,500,60,0,87,-120,12,true,3);
					
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					maSequence.getMaListeTir().add(tir3);
					maSequence.getMaListeTir().add(tir4);
					maSequence.getMaListeTir().add(tir5);
					maSequence.getMaListeTir().add(tir6);
					
					this.unTemplate.add(maSequence);
					break;		  
			  }	
			  case 109:
			  {
				  SequenceTir maSequence = new SequenceTir(100);
				  	Tir tir1 = new Tir(2,1500,20,0,ThePanel.MonAvion.getWidth()-40,0,3,false,1);
					Tir tir2 = new Tir(2,1500,20,0,20,0,3,false,1);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					this.unTemplate.add(maSequence);
					break;		  
			  }	
			  case 110:
			  {
				  SequenceTir maSequence = new SequenceTir(100);
				  	Tir tir1 = new Tir(2,1500,20,0,ThePanel.MonAvion.getWidth()-40,0,3,false,1);
					Tir tir2 = new Tir(2,1500,20,0,20,0,3,false,1);
					maSequence.getMaListeTir().add(tir1);
					maSequence.getMaListeTir().add(tir2);
					this.unTemplate.add(maSequence);
					break;		  
			  }	
			}
		}
	}
	
}

