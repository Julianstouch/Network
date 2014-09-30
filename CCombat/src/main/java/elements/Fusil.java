package elements;

import java.awt.Color;
import java.awt.Graphics;

import fixed.Const;


/**
 * 
 * @author jfeniou
 * 
 */
public class Fusil extends Combattant
{

    public Fusil(Integer sonMatricule)
    {
        super(sonMatricule);
        type = Const.typeFusil;
        par = new Param();
        par.porte = Const.Fu_Porte;
        par.angle = Const.Fu_Angle;
        par.courte = Const.Fu_Courte;
    }

    @Override
    public void zoneTracer(Graphics g2d)
    {
        // les 2 cote du cone
        g2d.drawLine(this.coordXBaseBasse, this.coordYBaseBasse, this.coordXcibleBasse, this.coordYcibleBasse);
        g2d.drawLine(this.coordXBaseHaute, this.coordYBaseHaute, this.coordXcibleHaute, this.coordYcibleHaute);
        // grand arc
        g2d.drawArc(this.coordX - par.porte, this.coordY - par.porte, par.porte * 2, par.porte * 2, this.angleConeBas, par.angle * 2);
        // petit arc
        g2d.drawArc(this.coordX - par.courte, this.coordY - par.courte, par.courte * 2, par.courte * 2, this.angleConeBas, par.angle * 2);

    }

    @Override
    public void zonePeindre(Graphics g2d)
    {
        g2d.setColor(Color.RED);

        g2d.fillArc(this.coordX - par.porte, this.coordY - par.porte, par.porte * 2, par.porte * 2, this.angleConeBas, par.angle * 2);

        g2d.setColor(Color.BLACK);
        // zone de non tir proche
        g2d.fillArc(this.coordX - par.courte, this.coordY - par.courte, par.courte * 2, par.courte * 2, this.angleConeBas - 1, (par.angle + 1) * 2);
        // sur le mec au cas ou
        g2d.fillRect(this.coordX, this.coordY, 1, 1);
        // obstacle

        g2d.setColor(Color.BLACK);

    }
}
