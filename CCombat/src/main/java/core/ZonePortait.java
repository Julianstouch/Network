package core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import elements.Fleche;

public class ZonePortait extends JPanel implements ActionListener
{
    private static final int PERIOD = 50; // 0.1 secs
    private static ZonePortait instance;
    
    // public BufferedImage legraphic;
    
    private Fleche laFleche;
    
    public static ZonePortait getInstance()
    {
        return instance;
    }

    public ZonePortait()
    {
        instance = this;
        laFleche = new Fleche();
        // legraphic = new BufferedImage(1000, 268, BufferedImage.TYPE_INT_ARGB);
        new Timer(PERIOD, this).start();
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        // if (activeFleche)
        super.paint(g2d);
        
        laFleche.seDessine(g2d);

        for (int i = 0; i < CombatManager.getInstance().persoNombre; i++)
        {
            CombatManager.getInstance().lesHeros.get(i).saPhoto(g2d);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        repaint();        
    }
}
