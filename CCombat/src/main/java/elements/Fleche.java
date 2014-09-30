package elements;

import java.awt.Graphics2D;

import graph.ImageDistributor;
import core.CombatManager;

/**
 * 
 * @author jfeniou
 *
 */
public class Fleche
{
    private Integer leXFleche;
    private Integer leYFleche;
    private Boolean activeFleche;
    
    /**
     * 
     */
    public Fleche()
    {
        leXFleche = 5 + 30;
        leYFleche = 10;
        activeFleche = true;
    }
    
    
    public void seDessine(Graphics2D g2d)
    {
        activeFleche = CombatManager.getInstance().getCurrentHero() != null;
        
        if (activeFleche)
        {
            int pos = CombatManager.getInstance().getCurrentHero().matricule;
            leXFleche = 5 + 30 + pos * 120;
            g2d.drawImage(ImageDistributor.getInstance().getLaFlecheBas(), null, leXFleche, leYFleche);
        }
    }
}
