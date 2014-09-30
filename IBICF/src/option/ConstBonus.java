package option;
import ibicf.ThePanel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ConstBonus 
{
	public static HashMap<Integer,Bonus> listeBonusTotal;
	public static List<Integer> listNumTirBonus;
	public static HashMap<Integer,Bonus> listeBonus;
	public static int currentDropRate;
	
	
	public static final int bonus_Tir = 1;
	public static final int bonus_BonusCumul = 2;
	
	public ConstBonus() 
	{
		initTotalBonus();
		initTotalTir();
		resetCurrentBonus();
	}
		
	public static void initTotalBonus()
	{
		int bonusMunitionAvion=0;
		if (ThePanel.MonAvion!=null)bonusMunitionAvion=ThePanel.MonAvion.munitions;
		
		listeBonusTotal = new HashMap<Integer,Bonus>();
		// 							type  type ,   nom,   valeur, cap		drop,level 	is tir , couleur
		listeBonusTotal.put(1	,new Bonus(1,"tirFrontal",150+bonusMunitionAvion,800,		100,1,	true,new Color(255,156,0)));
		listeBonusTotal.put(2	,new Bonus(2,"tirDouble",150+bonusMunitionAvion,800,		60,1,	true,	new Color(255,156,0)));
		listeBonusTotal.put(3	,new Bonus(3,"tirLateral",150+bonusMunitionAvion,800,		60,2,	true,	new Color(255,156,0)));
		listeBonusTotal.put(4	,new Bonus(4,"doubleMissile",100+bonusMunitionAvion,200,	50,2,	true,	new Color(186,179,172)));
		listeBonusTotal.put(5	,new Bonus(5,"laser",100+bonusMunitionAvion,200,			40,3,	true,	new Color(38,211,62)));
		listeBonusTotal.put(6	,new Bonus(6,"laserLateral",100+bonusMunitionAvion,200,	40,4,	true, new Color(38,211,62)));
		listeBonusTotal.put(7	,new Bonus(7,"missileChercheur",50+bonusMunitionAvion,100,	30,5,	true, new Color(255,255,255)));
		listeBonusTotal.put(8	,new Bonus(8,"missileFragmente",50+bonusMunitionAvion,100,	30,6,	true, new Color(37,98,39)));
		listeBonusTotal.put(9	,new Bonus(9,"vie",100,100,				30,2,	false,null));
		listeBonusTotal.put(10	,new Bonus(10,"vieBouclier",50,100,		40,3,	false,null));
		listeBonusTotal.put(11	,new Bonus(11,"nbVies",1,5,				10,1,	false,null));
		listeBonusTotal.put(12	,new Bonus(12,"laserWave",100+bonusMunitionAvion,200,		20,7,	true,	new Color(38,211,62)));
		listeBonusTotal.put(13	,new Bonus(13,"reflectBouclier",10,100,	40,3,	false,null));
	}
	
	
	public static void initTotalTir()
	{
		listNumTirBonus = new ArrayList<Integer>();
		Set<Integer> listClef = listeBonusTotal.keySet();
		
		for (Integer unClef : listClef) 
		{
			if (listeBonusTotal.get(unClef).isTir)
				listNumTirBonus.add(listeBonusTotal.get(unClef).type);
		}
	}
	
	public static void resetCurrentBonus()
	{
		listeBonus = new HashMap<Integer, Bonus>();
		
		Set<Integer> listClef = listeBonusTotal.keySet();
		for (Integer unClef : listClef) 
		{
			if (ThePanel.level >= listeBonusTotal.get(unClef).leveldrop)
				listeBonus.put(listeBonusTotal.get(unClef).type,listeBonusTotal.get(unClef));
		}
		getTotalDrop();
	}
	
	
	public static void getTotalDrop()
	{
		Set<Integer> listClef = listeBonus.keySet();
		int totaldrop = 0;
		for (Integer unClef : listClef) 
		{
			if (ThePanel.level >= listeBonus.get(unClef).leveldrop)
			totaldrop += listeBonus.get(unClef).droprate;
		}
		currentDropRate = totaldrop;
	}
	
	public static int aleatoire(int min, int max)
	{
		return ((int) (Math.random()*(max-min+1)))+min;
	}
}