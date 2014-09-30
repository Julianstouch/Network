package elements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class CombattantMort
{
    public Integer matricule;
    public Integer coordXAff;
    public Integer coordYAff;
    public Integer angle;
    
    public BufferedImage rotIm;
    
    public CombattantMort(CombattantExchange unNewMort)
    {
        this.matricule = unNewMort.matricule;
        this.coordXAff = unNewMort.coordXAff;
        this.coordYAff = unNewMort.coordYAff;
        this.angle = unNewMort.angle;
    }

    public void seDessine(Graphics2D g2d)
    {
        g2d.drawImage(rotIm, null, coordXAff, coordYAff);
    }
}
