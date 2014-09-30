package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import elements.Combattant;
import elements.CombattantMort;
import elements.Obstacle;
import elements.Warning;
import fixed.Const;
import fixed.EnumPhaseJeu;
import graph.ImageDistributor;
import graph.ImageSFXs;

public class ZoneCombat extends JPanel implements ActionListener, Runnable
{
    public static ZoneCombat getInstance()
    {
        return instance;
    }
    public static ZoneCombat instance;

    public Thread threadCCombat = null;

    private static final int PERIOD = 50; // 0.1 secs
    /*
     * A Swing timer is triggered every PERIOD ms to update and redraw the images.
     */
    private ImageDistributor imgDis;
    private ImageSFXs imageSfx; // the special effects class

    private int liveYMouse = 0;
    private int liveXMouse = 0;

    public ZoneCombat()
    {
        instance = this;
        imgDis = ImageDistributor.getInstance();
        imageSfx = new ImageSFXs();
        new Timer(PERIOD, this).start();
    }

    public Insets insets()
    {
        return new Insets(0, 0, 100, 100);
    }

    public void update(Graphics g)
    {
        paint(g);

    }

    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        // use antialiasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // smoother (and slower) image transformations (e.g. for resizing)
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(imgDis.getLeFondMetal(), null, 0, 0);

        if (EnumPhaseJeu.phasesJeu.contains(CombatManager.getInstance().phaseJeu))
        {

            // dessin des decors
            for (int h = 0; h < ZoneCalcul.getInstance().lesPolyBloc.size(); h++)
            {
                Obstacle unObstacle = ZoneCalcul.getInstance().lesPolyBloc.get(h);
                if (unObstacle.type == 3)
                {
                    g2d.drawImage(imgDis.getLeDecorsCratere(), null, unObstacle.coordXDepart, unObstacle.coordYDepart);
                }
            }

            // ---------------les Mort Adverses, toujours visibles
            for (CombattantMort unAutreMort : CombatManager.getInstance().lesAutresMort)
            {
                if (unAutreMort.rotIm == null)
                    unAutreMort.rotIm = imageSfx.getRotatedImage(imgDis.getCombattantMort(Const.joueur2), unAutreMort.angle);
                unAutreMort.seDessine(g2d);
            }

            // ---- Si j'ai fini ------ les Mort Adverses
            if (CombatManager.getInstance().pretMoi)
            {
                if (CombatManager.getInstance().lesAutresHeros != null && CombatManager.getInstance().lesAutresHeros.size() > 0)
                {
                    for (int i = 0; i < CombatManager.getInstance().lesAutresHeros.size(); i++)
                    {
                        Combattant unAutreHero = CombatManager.getInstance().lesAutresHeros.get(i);
                        if (!unAutreHero.mort) // && !autreMatriculeMort.contains(unAutreHero.matricule))
                        {
                            // tete de l'adversaire
                            BufferedImage rotIm = imageSfx.getRotatedImage(imgDis.getCombattant(Const.joueur2, unAutreHero), unAutreHero.angle);
                            g2d.drawImage(rotIm, null, unAutreHero.coordXAff, unAutreHero.coordYAff);

                            // sa zone de tir
                            g2d.setColor(Color.RED);

                            unAutreHero.zoneTracer(g2d);

                            g2d.setColor(Color.BLACK);
                        }
                    }
                }
            }

            for (int i = 0; i < CombatManager.getInstance().persoNombre; i++)
            {
                Combattant unHero = CombatManager.getInstance().lesHeros.get(i);
                // ----- un Hero mort ----
                if (unHero.mort)
                {
                    BufferedImage rotIm = imageSfx.getRotatedImage(imgDis.getCombattant(Const.joueur1, unHero), unHero.angle);
                    g2d.drawImage(rotIm, null, unHero.coordXAff, unHero.coordYAff);
                }
                else
                {
                    BufferedImage rotIm;
                    // juste pose, on le fait pivoter en fonction de la souris
                    if (unHero.pose == true)
                    {
                        rotIm = imageSfx.getRotatedImage(imgDis.getCombattant(Const.joueur1, unHero),
                                Combattant.getAngle(unHero.coordX - liveXMouse, unHero.coordY - liveYMouse) - 180);
                        g2d.drawImage(rotIm, null, unHero.coordXAff, unHero.coordYAff);

                        g2d.drawLine(unHero.coordX, unHero.coordY, liveXMouse, liveYMouse);
                    }
                    // Active : Zone de tire :
                    else if (unHero.active == true)
                    {
                        rotIm = imageSfx.getRotatedImage(imgDis.getCombattant(Const.joueur1, unHero), unHero.angle);
                        g2d.drawImage(rotIm, null, unHero.coordXAff, unHero.coordYAff);

                        g2d.setColor(Color.GREEN);
                        ZoneCalcul.getInstance().resetZone();

                        // Trace la zone, et paint sur le fond
                        unHero.zoneTracer(g2d);
                        
                        // Partie debug :
                        unHero.zonePeindre(ZoneCalcul.getInstance().legraphic.getGraphics());
                        // obstacle if pas missile
                        if (unHero.type != 3)
                            ZoneCalcul.getInstance().peindreObstacle(unHero);
                        ZoneCalcul.getInstance().repaint();

                        g2d.setColor(Color.BLACK);
                        
                        if(unHero.mortDansRound)
                        {
                            rotIm = imageSfx.getRotatedImage(imgDis.getCombattantMort(Const.joueur1), unHero.angle);
                            g2d.drawImage(rotIm, null, unHero.coordXAff, unHero.coordYAff);
                        }
                    }
                    // Si c'est celui dans la main
                    else if (i == CombatManager.getInstance().current)
                    {
                        g2d.drawImage(imgDis.getCombattant(Const.joueur1, unHero), null, liveXMouse - 20, liveYMouse - 20);
                    }
                }
            }

            // dessin des warning
            for (Warning warn : CombatManager.getInstance().lesBlesses)
            {
                warn.seDessine(g2d);
            }
        }

        if (EnumPhaseJeu.phasePerdu.isPhase(CombatManager.getInstance().phaseJeu))
            g2d.drawImage(imgDis.getLeFondGameover(), null, 100, 100);
        if (EnumPhaseJeu.phaseWin.isPhase(CombatManager.getInstance().phaseJeu))
            g2d.drawImage(imgDis.getLeFondWin(), null, 100, 100);
        if (EnumPhaseJeu.phaseDraw.isPhase(CombatManager.getInstance().phaseJeu))
            g2d.drawImage(imgDis.getLeFondDraw(), null, 100, 100);
    }

    public void start()
    {
        if (threadCCombat == null)
        {
            threadCCombat = new Thread(this);
            threadCCombat.start();
        }
    }

    public void stop()
    {
        if (threadCCombat != null)
        {
            threadCCombat.stop();
            threadCCombat = null;
        }
    }

    public void run()
    {

    }

    private void fadingImage(Graphics2D g2d, BufferedImage im, int x, int y, int compteur)
    {
        // System.out.println((((compteur*4)%100)/100.0f));
        float alpha = (compteur > 100 ? 99 : compteur) / 100.0f;
        imageSfx.drawFadedImage(g2d, im, x, y, alpha);
        // imageSfx.drawFadedImage(g2d, im, x, y-dureealpha+compteur, alpha);
    }

    /**
     * triggered by the timer: update, repaint
     */
    public void actionPerformed(ActionEvent e)
    {
        if (EnumPhaseJeu.phaseJustStarted.isPhase(CombatManager.getInstance().phaseJeu)) // don't do updates the first time through
            CombatManager.getInstance().phaseJeu = EnumPhaseJeu.phaseJouer;
        else
            imagesUpdate();

        repaint();
    }

    private void imagesUpdate()
    {
    }

    public void sequenceEnded(String imageName)
    {
    }

    /**
     * 
     * @param mouseEv
     */
    public void majLiveMouse(MouseEvent mouseEv)
    {
        liveXMouse = mouseEv.getX();
        liveYMouse = mouseEv.getY();
    }

}
