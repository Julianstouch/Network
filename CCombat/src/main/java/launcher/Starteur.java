package launcher;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import graph.ImageDistributor;
import core.VisualManager;

public class Starteur
{

    public static void main(String[] args)
    {
        // switch on translucency acceleration in Windows
        System.setProperty("sun.java2d.translaccel", "true");
        // System.setProperty("sun.java2d.ddforcevram", "true");
        
        JFrame app = new JFrame("Starting ... ");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        app.setLocation((screenSize.width / 2) - 200 , (screenSize.height/2) - 100);
        app.setPreferredSize(new Dimension(200, 100));
        app.validate();
        app.pack();
        app.setVisible(true);
        
        ImageDistributor imgDis = new ImageDistributor();
        imgDis.initImages();
        
        VisualManager vManag = new VisualManager();
        app.setVisible(false);
        app.setEnabled(false);
        app.dispose();
        vManag.letsStart();
    }
}
