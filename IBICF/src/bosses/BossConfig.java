package bosses;

import ibicf.ThePanel;

public class BossConfig 
{
	public int pv;
	public int vitesse;
	public int numero;
	public String name;
	public boolean isBossFin;

	public BossConfig(int num, boolean isBossFin)
	{
		this.numero = num;
		this.name = "boss"+num;
		this.isBossFin = isBossFin;
		
		switch (num) 
		{
			case 1:
			{
				this.pv = (500*ThePanel.difficulte)+400;
				this.vitesse = 1;
				break;
			}
			case 2:
			{
				this.pv = (500*ThePanel.difficulte)+600;
				this.vitesse = 2;
				break;
			}
			case 3:
			{
				this.pv = (600*ThePanel.difficulte)+1200;
				this.vitesse = 1;
				break;
			}
			case 4:
			{
				this.pv = (500*ThePanel.difficulte)+800;
				this.vitesse = 1;
				break;
			}
			case 5:
			{
				this.pv = (500*ThePanel.difficulte)+1500;
				this.vitesse = 1;
				break;
			}
			case 6:
			{
				this.pv = (800*ThePanel.difficulte)+2500;
				this.vitesse = 1;
				break;
			}
			case 7:
			{
				this.pv = (600*ThePanel.difficulte)+2000;
				this.vitesse = 1;
				break;
			}
			case 8:
			{
				this.pv = (500*ThePanel.difficulte)+5000;
				this.vitesse = 1;
				break;
			}
			case 9:
			{
				this.pv = (500*ThePanel.difficulte)+1000;
				this.vitesse = 1;
				break;
			}
			case 10:
			{
				this.pv = (500*ThePanel.difficulte)+1000;
				this.vitesse = 1;
				break;
			}
			case 11:
			{
				this.pv = (500*ThePanel.difficulte)+1000;
				this.vitesse = 1;
				break;
			}
			case 12:
			{
				this.pv = (500*ThePanel.difficulte)+1000;
				this.vitesse = 1;
				break;
			}
			
		} 
		
		if (!this.isBossFin)
		{
			this.pv=this.pv/3;
			this.numero = num+100;
		}
	}	
}
