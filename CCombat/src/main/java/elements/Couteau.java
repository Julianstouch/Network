package elements;

import java.awt.Color;
import java.awt.Graphics;

import fixed.Const;


/**
 * 
 * @author jfeniou
 *
 */
public class Couteau extends Combattant
{

    public Couteau(Integer sonMatricule)
    {
        super(sonMatricule);
        type = Const.typeCouteau;
        par = new Param();
        par.porte = Const.Cou_Porte;
        par.angle = Const.Cou_Angle;
        par.courte = Const.Cou_Courte;
    }
    
    public void zoneTracer(Graphics g2d)
    {
        // grand cercle
        g2d.drawArc(this.coordX - par.porte, this.coordY - par.porte, par.porte * 2, par.porte * 2, this.angleConeBas, par.angle * 2);
        // petit cercle
        g2d.drawArc(this.coordX - par.courte, this.coordY - par.courte, par.courte * 2, par.courte * 2, this.angleConeBas, par.angle * 2);
    }
    
    public void zonePeindre(Graphics g2d)
    {
        g2d.setColor(Color.RED);
        // rond autour du mec
        g2d.fillArc(this.coordX - par.porte, this.coordY - par.porte, par.porte * 2, par.porte * 2, this.angleConeBas, par.angle * 2);
        g2d.setColor(Color.BLACK);
        // rond sur le mec
        g2d.fillArc(this.coordX - par.courte, this.coordY - par.courte, par.courte * 2, par.courte * 2, this.angleConeBas, par.angle * 2);
    }
    
}
