package elements;

import java.awt.Color;
import java.awt.Graphics;

import fixed.Const;


/**
 * 
 * @author jfeniou
 * 
 */
public class Bazooka extends Combattant
{

    public Bazooka(Integer sonMatricule)
    {
        super(sonMatricule);
        type = Const.typeBazooka;
        par = new Param();
        par.porte = Const.Baz_Porte;
        par.angle = Const.Baz_Angle;
        par.courte = Const.Baz_Courte;
    }

    @Override
    public void zoneTracer(Graphics g2d)
    {
        // zone
        g2d.drawArc(this.coordXcibleMiddle - par.porte, this.coordYcibleMiddle - par.porte, par.porte * 2, par.porte * 2, this.angleConeBas,
                par.angle * 2);
        // ligne de tir
        g2d.drawLine(this.coordX, this.coordY, this.coordXcibleMiddle, this.coordYcibleMiddle);

    }

    @Override
    public void zonePeindre(Graphics g2d)
    {
        g2d.setColor(Color.RED);
        g2d.fillArc(this.coordXcibleMiddle - par.porte, this.coordYcibleMiddle - par.porte, par.porte * 2, par.porte * 2, this.angleConeBas,
                par.angle * 2);
        g2d.setColor(Color.BLACK);
    }
}
