package elements;

import java.awt.Graphics2D;

import graph.ImageDistributor;

/**
 * 
 * @author jfeniou
 *
 */
public class Warning
{
    private final Integer limitClignote = 10;
    
    private Integer coordXAff;
    private Integer coordYAff;

    private Integer countAff;
    private Integer countMask;
    
    
    private Warning()
    {
        countAff = 0;
        countMask = 0;
    }
    
    public Warning(Combattant unHero)
    {
        this();
        coordXAff = unHero.coordXAff;
        coordYAff = unHero.coordYAff;
        
    }

    public Warning(CombattantMort unHero)
    {
        this();
        coordXAff = unHero.coordXAff;
        coordYAff = unHero.coordYAff;
    }
    
    public void seDessine(Graphics2D g2d)
    {
        if(countAff < limitClignote)
        {
            g2d.drawImage(ImageDistributor.getInstance().getSousLeFeu(), null, coordXAff, coordYAff);
            countAff++;
        }
        else 
        {
            countMask++;
        }
        
        if(countMask > limitClignote)
        {
            countMask = 0;
            countAff = 0;
        }
    }
}
