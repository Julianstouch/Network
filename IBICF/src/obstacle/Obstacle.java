package obstacle;

import java.util.Calendar;

import avion.Avion;
import option.ConstBonus;
import ibicf.Polygon;
import ibicf.Sprite;

public class Obstacle extends Sprite
{

	public Obstacle(int x, int y, String name, int pNiveauProfondeur) {
		super(x, y, name);
		//image = name;
		niveauProfondeur = pNiveauProfondeur;
	}
	
	// Details du sol
	public Obstacle(int x, int y, String name) {
		super(x, y, name);
		//image = name;
		niveauProfondeur = 0;
		decalageEnCours = 0;
		int deplacement = ConstBonus.aleatoire(1, 5);
		// si le deplacement est inferier a 5, on va la faire bouger qu'une fois tout les 'deplacement' fois
		// donc moins vite que le deplacement normal
//		if (deplacement <=5)
//			{
				decalage = deplacement;
				vitesse = 1;
//			}
		// si le deplacement est superieur a 5, a chaque fois, elle va bouge de deplacement - 5 
//		else
//			{
//				decalage = 1;
//				vitesse = deplacement-5;
//			}
	}
	
	//affichage
	/** 0 : juste au dessus du sol, sous les projectiles
	 *	1 : au dessus des unité du sol / projectiles , au dessous des avions enemmis / boss 
	 *	2 : au dessus des avions ennemi / boss , au dessous du notre
	 *	3 : au dessus du notre
	 */  
	public int niveauProfondeur;
	//public String image;
	
	public int angleRotation;

	// son deplacement
	public Boolean isMouvable;
	public Integer vitesse;		// vitesse de deplacement a chaque passage de décalage
	public Integer decalage;	//	décalage a 2 ne fais le déplacement qu'une fois sur 2 
	public Integer decalageEnCours;	// décalage actuel
	public Integer diagonal;
	
	// lié a un avion
	public Boolean isRattache;
	public Avion avionRattache;
	public Integer posXDecalage;
	public Integer posYDecalage;
	
	// collision avec les tirs ?
	public Boolean isTirBloquant;
	public Boolean isDestructible;
	public Integer quelTirBloquant; // 0:rien 1:amis 2:ennemi 3:les2  (4:boss?)
	public Integer pointDeVie;
	
	// collision avec notre vaiseau
	public Boolean isAvionBloquant;
	public Integer quelCollision; 	// 0:rien 1:amis 2:ennemi 3:les2  (4:boss?)
	public Integer degatCollision;	
	public Polygon zoneCollision;
	
	// durée
	public Boolean isPersistant;
	public Long tempsDebut;
	public Long tempsDeVie;
		


	public void setMouvement(Boolean pMouvable, Integer pVitesse,Integer pdecalage,Integer pdiagonal )
	{
		this.isMouvable = pMouvable;
		this.vitesse = pVitesse;		// vitesse de deplacement a chaque passage de décalage
		this.decalage = pdecalage;	//	décalage a 2 ne fais le déplacement qu'une fois sur 2 
		this.decalageEnCours = 0;	// décalage actuel
		this.diagonal = pdiagonal;
	}
	
	public void setRattachement(Boolean pisRattache,Avion pavionRattache)
	{
		this.isRattache = pisRattache;
		this.avionRattache = pavionRattache;
		//public Integer posXDecalage;
		//public Integer posYDecalage;
	}
	
	/**Blocant, destructible, type blocage: 0:rien 1:amis 2:ennemi 3:les2, pv **/ 
	public void setCollisionTir(Boolean pisTirBloquant,Boolean pisDestructible,Integer pquelTirBloquant, Integer ppointDeVie)
	{
		this.isTirBloquant = pisTirBloquant;
		this.isDestructible = pisDestructible;
		this.quelTirBloquant = pquelTirBloquant; // 0:rien 1:amis 2:ennemi 3:les2  (4:boss?)
		this.pointDeVie = ppointDeVie;
	}
	
	public void setCollisionAvion(Boolean pisAvionBloquant, Integer pquelCollision, Integer pdegatCollision)
	{
		this.isAvionBloquant = pisAvionBloquant;
		this.quelCollision = pquelCollision; 	// 0:rien 1:amis 2:ennemi 3:les2  (4:boss?)
		this.degatCollision = pdegatCollision;	
		//this.zoneCollision = ;
	}
	
	public void setPersistance(Boolean pisPersistant,Long ptempsDeVie)
	{
		this.isPersistant = pisPersistant;
		this.tempsDebut = Calendar.getInstance().getTimeInMillis();
		this.tempsDeVie = ptempsDeVie;
	}
	
}