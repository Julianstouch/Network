package option;
import java.awt.Color;


public class Bonus 
{
	public int type;
	public String nom;
	public int valeur;
	public int cap;
	public int droprate;
	public int leveldrop;
	public Boolean isTir;
	public Color laColor;
	
	public Bonus(int type, String nom, int valeur, int cap, int droprate, int leveldrop,Boolean isTir, Color lacouleur) 
	{
		this.type = type;
		this.nom = nom;
		this.valeur = valeur;
		this.cap = cap;
		this.droprate = droprate;
		this.leveldrop = leveldrop;
		this.isTir = isTir;
		this.laColor = lacouleur;
	}
	
	
	
	
}
