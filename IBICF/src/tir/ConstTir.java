package tir;

public class ConstTir 
{
//  type 1 : Mitraillette
	public static final Integer Mitra_Degat = 10;
//	type 2 : Missile
	public static final Integer Missile_Degat = 100;
	public static final Integer Missile_Degat_Boss_1 = 100;
//	type 3 : Laser
	public static final Integer Laser_Degat = 100;
	public static final Integer Laser_Degat_Boss_1 = 200;
// 	type 4 : MissLocker
	public static final Integer MissLock_Degat = 200;
// 	type 4 : MissFrager
	public static final Integer MissFrag_Degat = 300;
	
	
	public static String getImage (int type, boolean isMonAvion)
	{		
		switch (type) {
		case 1: { 	if (isMonAvion)
						return "mit";
					else
						return "mit2";
				}
		case 2: { 	if (isMonAvion)
						return "mis1";
					else
						return "mis3";
				}
		case 3: return "laser1";
		case 4: return "mis2";
		case 5: return "mis4";
		case 6: return "mit1";
		case 7: return "laser2";

	default: return "mit";
	}
	}
	
}
