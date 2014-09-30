package graph;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import elements.Combattant;
import fixed.Const;

public class ImageDistributor
{
    private final String IMS_FILE = "imsInfo.txt";
    private ImagesLoader imsLoader; // the image loader
    
    private BufferedImage leFondMetal;
    private BufferedImage leFondZone;
    private BufferedImage leDecorsCratere;
    private BufferedImage laFlecheBas;

    private BufferedImage leFondWin;
    private BufferedImage leFondDraw;
    private BufferedImage leFondGameover;
    
    private Map<Integer,BufferedImage> imgIntro;
    private Map<Integer,BufferedImage> portraitVivant;
    private Map<Integer,BufferedImage> portraitMort;
    private Map<Integer,BufferedImage> combattantVertVivant;
    private Map<Integer,BufferedImage> combattantRougeVivant;
    private Map<Integer,BufferedImage> combattantMort;
    
    private BufferedImage sousLeFeu;
    
    private int fondNonPosable;
    
    private static ImageDistributor instance;
    
    public static ImageDistributor getInstance()
    {
        return instance;
    }
    
    public ImageDistributor()
    {
        instance = this;
        imsLoader = new ImagesLoader(IMS_FILE);
    }

    public void initImages()
    {
        imgIntro = new HashMap<Integer, BufferedImage>();
        portraitVivant = new HashMap<Integer, BufferedImage>();
        portraitMort = new HashMap<Integer, BufferedImage>();
        combattantVertVivant = new HashMap<Integer, BufferedImage>();
        combattantRougeVivant = new HashMap<Integer, BufferedImage>();
        combattantMort = new HashMap<Integer, BufferedImage>();
        
        imgIntro.put(1,imsLoader.getImage("JTrisCombat_intro_1"));
        imgIntro.put(2,imsLoader.getImage("JTrisCombat_intro_2"));
        imgIntro.put(3,imsLoader.getImage("JTrisCombat_intro_3"));
        imgIntro.put(4,imsLoader.getImage("JTrisCombat_intro_4"));
        imgIntro.put(5,imsLoader.getImage("JTrisCombat_intro_5"));
        
        leFondWin = imsLoader.getImage("JTrisCombat_res_win");
        leFondGameover = imsLoader.getImage("JTrisCombat_res_gameover");
        leFondDraw = imsLoader.getImage("JTrisCombat_res_draw");

        
        // leFondMetal = imsLoader.getImage("fond_metal");
        leFondMetal = imsLoader.getImage("fond_metal_v2");
        leFondZone = imsLoader.getImage("fond_metal_base");
        leFondZone.createGraphics();
        leDecorsCratere = imsLoader.getImage("decors_crater");
        laFlecheBas = imsLoader.getImage("fleche_bas_trans");

        sousLeFeu = imsLoader.getImage("sous_le_feu");
        
        combattantVertVivant.put(Const.typeFusil, imsLoader.getImage("soldat_fusil_vert"));
        combattantVertVivant.put(Const.typeCouteau, imsLoader.getImage("soldat_couteau_vert"));
        combattantVertVivant.put(Const.typeBazooka, imsLoader.getImage("soldat_bazooka_vert"));
        combattantVertVivant.put(Const.typeSniper, imsLoader.getImage("soldat_sniper_vert"));
        
        combattantRougeVivant.put(Const.typeFusil, imsLoader.getImage("soldat_fusil_rouge"));
        combattantRougeVivant.put(Const.typeCouteau, imsLoader.getImage("soldat_couteau_rouge"));
        combattantRougeVivant.put(Const.typeBazooka, imsLoader.getImage("soldat_bazooka_rouge"));
        combattantRougeVivant.put(Const.typeSniper, imsLoader.getImage("soldat_sniper_rouge"));
        
        combattantMort.put(Const.joueur1, imsLoader.getImage("soldat_vert_mort"));
        combattantMort.put(Const.joueur2, imsLoader.getImage("soldat_rouge_mort"));
        
        //portraitVivant.put(Const.typeFusil, imsLoader.getImage("lebonhomme_fusil_vivant"));
        portraitVivant.put(Const.typeFusil, imsLoader.getImage("h-soldier"));
        portraitMort.put(Const.typeFusil, imsLoader.getImage("lebonhomme_fusil_mort"));
        
        //portraitVivant.put(Const.typeCouteau, imsLoader.getImage("lebonhomme_couteau_vivant"));
        portraitVivant.put(Const.typeCouteau, imsLoader.getImage("h-close"));
        portraitMort.put(Const.typeCouteau, imsLoader.getImage("lebonhomme_couteau_mort"));
        
        //portraitVivant.put(Const.typeBazooka, imsLoader.getImage("lebonhomme_bazooka_vivant"));
        portraitVivant.put(Const.typeBazooka, imsLoader.getImage("h-bazooka"));
        portraitMort.put(Const.typeBazooka, imsLoader.getImage("lebonhomme_bazooka_mort"));
        
        //portraitVivant.put(Const.typeSniper, imsLoader.getImage("lebonhomme_sniper_vivant"));
        portraitVivant.put(Const.typeSniper, imsLoader.getImage("h-sniper"));
        portraitMort.put(Const.typeSniper, imsLoader.getImage("lebonhomme_sniper_mort"));
        
        fondNonPosable = leFondZone.getRGB(1, 1);
    }
    
    /**
     * 
     * @return
     */
    public BufferedImage getIntro()
    {
        return imgIntro.get(new Random().nextInt(5) + 1);
    }
    
    /**
     * 
     * @param joueur
     * @param hero
     * @return
     */
    public BufferedImage getCombattant(Integer joueur, Combattant hero)
    {
        if(hero.mort)
            return combattantMort.get(joueur);
        
        if(Const.joueur1 == joueur)
            return combattantVertVivant.get(hero.type);
     
        return combattantRougeVivant.get(hero.type);
    }
    
    /**
     * 
     * @param hero
     * @return
     */
    public BufferedImage getCombattantMort(Integer joueur)
    {
        return combattantMort.get(joueur);
    }

    /**
     * 
     * @param hero
     * @return
     */
    public BufferedImage getPortrait(Combattant hero)
    {
        if (hero.mort)
            return portraitMort.get(hero.type);
        
        else if (hero.mortDansRound)
        {
            if (hero.countMask > hero.limitClignote)
            {
                hero.countMask = 0;
                hero.countAff = 0;
            }
            
            if (hero.countAff < hero.limitClignote)
            {
                hero.countAff++;
                return portraitMort.get(hero.type);
            }
            else
            {
                hero.countMask++;
                return portraitVivant.get(hero.type);
            }
        }
        return portraitVivant.get(hero.type);
    }
    
    /**
     * @return the leFondMetal
     */
    public BufferedImage getLeFondMetal()
    {
        return leFondMetal;
    }

    /**
     * @return the leFondZone
     */
    public BufferedImage getLeFondZone()
    {
        return leFondZone;
    }

    /**
     * @return the leDecorsCratere
     */
    public BufferedImage getLeDecorsCratere()
    {
        return leDecorsCratere;
    }

    /**
     * @return the laFlecheBas
     */
    public BufferedImage getLaFlecheBas()
    {
        return laFlecheBas;
    }

    /**
     * @return the sousLeFeu
     */
    public BufferedImage getSousLeFeu()
    {
        return sousLeFeu;
    }

    /**
     * @return the fondNonPosable
     */
    public int getFondNonPosable()
    {
        return fondNonPosable;
    }

    /**
     * @return the leFondWin
     */
    public BufferedImage getLeFondWin()
    {
        return leFondWin;
    }

    /**
     * @return the leFondDraw
     */
    public BufferedImage getLeFondDraw()
    {
        return leFondDraw;
    }

    /**
     * @return the leFondGameover
     */
    public BufferedImage getLeFondGameover()
    {
        return leFondGameover;
    }
}
