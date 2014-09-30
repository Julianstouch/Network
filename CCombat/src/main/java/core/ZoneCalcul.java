package core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import elements.Combattant;
import elements.CombattantMort;
import elements.Obstacle;
import elements.Warning;
import fixed.EnumEnvoyer;
import graph.ImageDistributor;

public class ZoneCalcul extends JPanel
{
    public static ZoneCalcul instance;

    private int cont = 0;
    public BufferedImage legraphic;
    public boolean juststarted = true;
    // 1=mur 2=caisse 3=crater
    public Obstacle mur1 = new Obstacle(225, 461, 289, 461, 1);
    public Obstacle mur2 = new Obstacle(289, 461, 330, 509, 1);
    public Obstacle caisse1 = new Obstacle(622, 221, 661, 262, 2);
    public Obstacle caisse2 = new Obstacle(661, 221, 622, 262, 2);

    public static ZoneCalcul getInstance()
    {
        return instance;
    }

    public List<Obstacle> lesPolyBloc;

    public ZoneCalcul()
    {
        instance = this;
        lesPolyBloc = new ArrayList<Obstacle>();
        setPreferredSize(new Dimension(1000, 900));
        legraphic = new BufferedImage(1000, 900, BufferedImage.TYPE_INT_ARGB);
        // resetZone();
        lesPolyBloc.add(caisse1);
        lesPolyBloc.add(caisse2);
        lesPolyBloc.add(mur1);
        lesPolyBloc.add(mur2);

    }

    public Insets insets()
    {
        return new Insets(10, 10, 10, 10);
    }

    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);

        g2d.drawImage(legraphic, 0, 0, null);

        if (juststarted)
        {
            g2d.drawImage(ImageDistributor.getInstance().getLeFondZone(), 0, 0, null);
            juststarted = false;
        }
        // g2d.fillRect(0, 0, 1000, 700);

        // use antialiasing
        // g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        // RenderingHints.VALUE_ANTIALIAS_ON);

        // smoother (and slower) image transformations (e.g. for resizing)
        // g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        // RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // g2d.fill3DRect(0,0, 1000, 700,true );

    }

    public void update(Graphics g)
    {
        // paint(g);

    }

    public void resetZone()
    {
        Graphics g2 = legraphic.getGraphics();
        g2.setColor(Color.BLACK);
        peindreFondDecors();
        g2.drawImage(ImageDistributor.getInstance().getLeFondZone(), 0, 0, null);
        paint(g2);
        this.repaint();
    }

    public void peindreFondDecors()
    {
        Graphics graf = ImageDistributor.getInstance().getLeFondZone().getGraphics();
        graf.setColor(new Color(ImageDistributor.getInstance().getFondNonPosable()));
        for (Obstacle lobstacle : lesPolyBloc)
        {

            switch (lobstacle.type)
            {
                case 1:// mur
                {
                    break;
                }
                case 2:// caisse
                {

                    break;
                }
                case 3:// crater
                {
                    graf.fillOval(lobstacle.coordXDepart, lobstacle.coordYDepart, 150, 150);
                    break;
                }
            }
        }
    }

    public void peindreObstacle(Combattant leMec)
    {
        Graphics g2 = legraphic.getGraphics();
        
        g2.setColor(Color.BLACK);
        for (Obstacle lobstacle : lesPolyBloc)
        {
            switch (lobstacle.type)
            {
                case 1:// mur
                {
                    Polygon unPoly = new Polygon();
                    // 2 premiers points l'obstable
                    unPoly.addPoint(lobstacle.coordXDepart, lobstacle.coordYDepart);
                    unPoly.addPoint(lobstacle.coordXArrive, lobstacle.coordYArrive);
                    // calcul des 2 points eloigné :
                    Integer leProchainXDepart, leProchainYDepart, leProchainXArrive, leProchainYArrive;
                    leProchainXDepart = lobstacle.coordXDepart + (lobstacle.coordXDepart - leMec.coordX) * 10;
                    leProchainYDepart = lobstacle.coordYDepart + (lobstacle.coordYDepart - leMec.coordY) * 10;
                    leProchainXArrive = lobstacle.coordXArrive + (lobstacle.coordXArrive - leMec.coordX) * 10;
                    leProchainYArrive = lobstacle.coordYArrive + (lobstacle.coordYArrive - leMec.coordY) * 10;
                    unPoly.addPoint(leProchainXArrive, leProchainYArrive);
                    unPoly.addPoint(leProchainXDepart, leProchainYDepart);

                    g2.fillPolygon(unPoly);
                    break;
                }
                case 2:// caisse
                {
                    Polygon laCaisse = new Polygon();
                    laCaisse.addPoint(caisse1.coordXDepart, caisse1.coordYDepart);
                    laCaisse.addPoint(caisse2.coordXDepart, caisse2.coordYDepart);
                    laCaisse.addPoint(caisse1.coordXArrive, caisse1.coordYArrive);
                    laCaisse.addPoint(caisse2.coordXArrive, caisse2.coordYArrive);
                    g2.fillPolygon(laCaisse);

                    Polygon unPoly = new Polygon();
                    // 2 premiers points l'obstable
                    unPoly.addPoint(lobstacle.coordXDepart, lobstacle.coordYDepart);
                    unPoly.addPoint(lobstacle.coordXArrive, lobstacle.coordYArrive);
                    // calcul des 2 points eloign� :
                    Integer leProchainXDepart, leProchainYDepart, leProchainXArrive, leProchainYArrive;
                    leProchainXDepart = lobstacle.coordXDepart + (lobstacle.coordXDepart - leMec.coordX) * 10;
                    leProchainYDepart = lobstacle.coordYDepart + (lobstacle.coordYDepart - leMec.coordY) * 10;
                    leProchainXArrive = lobstacle.coordXArrive + (lobstacle.coordXArrive - leMec.coordX) * 10;
                    leProchainYArrive = lobstacle.coordYArrive + (lobstacle.coordYArrive - leMec.coordY) * 10;
                    unPoly.addPoint(leProchainXArrive, leProchainYArrive);
                    unPoly.addPoint(leProchainXDepart, leProchainYDepart);

                    g2.fillPolygon(unPoly);
                    break;
                }
                case 3:// crater
                {
                }
            }
        }
    }

    /**
     * 
     */
    public void calculDesMort()
    {
        List<Combattant> allCombatant = new ArrayList<Combattant>();
        allCombatant.addAll(CombatManager.getInstance().lesHeros.values());
        allCombatant.addAll(CombatManager.getInstance().lesAutresHeros);

        // Chaque combattant tir chacun son tour, et on vérifie qui est touché
        // s'il meurt, il a le temps de tirer avant
        for (Combattant combatant : allCombatant)
        {
            if (combatant.mort)
            {
                // il ne tire pas !
            }
            else
            {
                //Graphics2D g2d = (Graphics2D)legraphic.getGraphics();
                Graphics2D g2d = (Graphics2D)legraphic.createGraphics();
                
                combatant.zonePeindre(g2d);
                
                // tir de bazooka > crater
                if(combatant.type == 3)
                {
                    Obstacle aNewCrater = new Obstacle(combatant.coordXcibleMiddle - 75, combatant.coordYcibleMiddle - 75,
                            combatant.coordXcibleMiddle + 75, combatant.coordYcibleMiddle + 75, 3);
                    lesPolyBloc.add(aNewCrater);
                    VisualManager.getInstance().getEnvoyeur().envoyer(EnumEnvoyer.obstacle, aNewCrater);
                }
                repaint();
                
                for (Combattant unHero : CombatManager.getInstance().lesHeros.values())
                {
                    int sousLeFeu = legraphic.getRGB(unHero.coordX, unHero.coordY);
                    if (Color.RED.getRGB() == sousLeFeu && !unHero.mort && !unHero.mortDansRound)
                    {
                        Warning warn = new Warning(unHero);
                        CombatManager.getInstance().lesBlesses.add(warn);
                        unHero.mortDansRound = true;
                        VisualManager.getInstance().getEnvoyeur().envoyer(EnumEnvoyer.heroMort, unHero.generateExchange());
                    }
                }
                for (Combattant unAHero : CombatManager.getInstance().lesAutresHeros)
                {
                    int sousLeFeu = legraphic.getRGB(unAHero.coordX, unAHero.coordY);
                    if (Color.RED.getRGB() == sousLeFeu && !unAHero.mort && !unAHero.mortDansRound)
                    {
                        Warning warn = new Warning(unAHero);
                        CombatManager.getInstance().lesBlesses.add(warn);
                        unAHero.mortDansRound = true;
                        CombattantMort unDeadOne = new CombattantMort(unAHero.generateExchange());
                        CombatManager.getInstance().lesAutresMort.add(unDeadOne);
                        VisualManager.getInstance().getEnvoyeur().envoyer(EnumEnvoyer.heroTouche, unAHero.generateExchange());
                    }
                }
                resetZone();
            }
        }
        ZoneCombat.getInstance().repaint();
    }
}
