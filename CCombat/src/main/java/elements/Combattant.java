package elements;

import java.awt.Graphics;
import java.awt.Graphics2D;

import graph.ImageDistributor;

public abstract class Combattant extends CombattantExchange
{
    public final Integer limitClignote = 10;

    public Param par;
    public Integer countAff;
    public Integer countMask;
    
    
    public Combattant(Integer sonMatricule)
    {
        super(sonMatricule);
        countAff = 0;
        countMask = 0;
    }
    
    public void saPhoto(Graphics2D g2d)
    {
        g2d.drawImage(ImageDistributor.getInstance().getPortrait(this), null, 5 + (this.matricule) * 120, 40);
    }

    /**
     * 
     * @param posX
     * @param posY
     */
    public void sePlace(int posX, int posY)
    {
        this.pose = true;
        this.coordX = posX;
        this.coordY = posY;
        this.coordXAff = posX - 20;
        this.coordYAff = posY - 20;
    }
    
    /**
     * 
     * @param leXangle
     * @param leYangle
     */
    public void seActive(Integer leXangle,Integer leYangle)
    {
        
        this.angle = getAngle(leXangle - this.coordX, leYangle - this.coordY);
        Integer angleCone = 90 - this.angle;
        this.angleConeBas = angleCone - par.angle;
        this.angleCone = angleCone;
        this.angleConeHaut = angleCone + par.angle;

        // Cible clike (missile)
        this.coordXcibleMiddle = leXangle;
        this.coordYcibleMiddle = leYangle;
        // BOUT
        Double laCoordXCibleB = Math.cos(Math.toRadians(this.angleConeBas.doubleValue())) * par.porte;
        Double laCoordYCibleB = Math.sin(Math.toRadians(this.angleConeBas.doubleValue())) * par.porte;
        this.coordXcibleBasse = this.coordX + laCoordXCibleB.intValue();
        this.coordYcibleBasse = this.coordY - laCoordYCibleB.intValue();

        Double laCoordXCibleH = Math.cos(Math.toRadians(this.angleConeHaut.doubleValue())) * par.porte;
        Double laCoordYCibleH = Math.sin(Math.toRadians(this.angleConeHaut.doubleValue())) * par.porte;
        this.coordXcibleHaute = this.coordX + laCoordXCibleH.intValue();
        this.coordYcibleHaute = this.coordY - laCoordYCibleH.intValue();

        // BASE
        Double laCoordXBaseB = Math.cos(Math.toRadians(this.angleConeBas.doubleValue())) * par.courte;
        Double laCoordYBaseB = Math.sin(Math.toRadians(this.angleConeBas.doubleValue())) * par.courte;
        this.coordXBaseBasse = this.coordX + laCoordXBaseB.intValue();
        this.coordYBaseBasse = this.coordY - laCoordYBaseB.intValue();

        Double laCoordXBaseH = Math.cos(Math.toRadians(this.angleConeHaut.doubleValue())) * par.courte;
        Double laCoordYBaseH = Math.sin(Math.toRadians(this.angleConeHaut.doubleValue())) * par.courte;
        this.coordXBaseHaute = this.coordX + laCoordXBaseH.intValue();
        this.coordYBaseHaute = this.coordY - laCoordYBaseH.intValue();

        this.pose = false;
        this.active = true;
    }
    
    /**
     * 
     * @return
     */
    public CombattantExchange generateExchange()
    {
        CombattantExchange cEx = new CombattantExchange(this.matricule);
        cEx.type = type;
        cEx.mort = mort;
        cEx.mortDansRound = mortDansRound;
        cEx.pose = pose;
        cEx.active = active;
        
        cEx.angle = angle;
        cEx.angleConeBas = angleConeBas;
        cEx.angleCone = angleCone;
        cEx.angleConeHaut = angleConeHaut;
        cEx.coordX = coordX;
        cEx.coordY = coordY;
        cEx.coordXAff = coordXAff;
        cEx.coordYAff = coordYAff;
        
        cEx.coordXcibleBasse = coordXcibleBasse;
        cEx.coordYcibleBasse = coordYcibleBasse;
        cEx.coordXcibleHaute = coordXcibleHaute;
        cEx.coordYcibleHaute = coordYcibleHaute;
        cEx.coordXcibleMiddle = coordXcibleMiddle;
        cEx.coordYcibleMiddle = coordYcibleMiddle;

        cEx.coordXBaseBasse = coordXBaseBasse;
        cEx.coordYBaseBasse = coordYBaseBasse;
        cEx.coordXBaseHaute = coordXBaseHaute;
        cEx.coordYBaseHaute = coordYBaseHaute;
        return cEx;
    }
    
    /**
     * 
     * @param unHero
     * @param cEx
     */
    public void alimenterDepuisExchange(CombattantExchange cEx)
    {
        this.matricule = cEx.matricule;
        this.type = cEx.type;
        this.mort = cEx.mort;
        this.mortDansRound = cEx.mortDansRound;
        this.pose = cEx.pose;
        this.active = cEx.active;
        
        this.angle = cEx.angle;
        this.angleConeBas = cEx.angleConeBas;
        this.angleCone = cEx.angleCone;
        this.angleConeHaut = cEx.angleConeHaut;
        this.coordX = cEx.coordX;
        this.coordY = cEx.coordY;
        this.coordXAff  = cEx.coordXAff;
        this.coordYAff = cEx.coordYAff;
        
        this.coordXcibleBasse = cEx.coordXcibleBasse;
        this.coordYcibleBasse = cEx.coordYcibleBasse;
        this.coordXcibleHaute = cEx.coordXcibleHaute;
        this.coordYcibleHaute = cEx.coordYcibleHaute;
        this.coordXcibleMiddle = cEx.coordXcibleMiddle;
        this.coordYcibleMiddle = cEx.coordYcibleMiddle;

        this.coordXBaseBasse = cEx.coordXBaseBasse;
        this.coordYBaseBasse = cEx.coordYBaseBasse;
        this.coordXBaseHaute = cEx.coordXBaseHaute;
        this.coordYBaseHaute = cEx.coordYBaseHaute;        
    }
    
    /**
     * 
     * @param unHero
     * @param cEx
     */
    public void restartRound()
    {
        this.mortDansRound = false;
        
        if (!this.mort)
        {
            this.pose = false;
            this.active = false;

            this.angle = 0;
            this.angleConeBas = 0;
            this.angleCone = 0;
            this.angleConeHaut = 0;
            this.coordX = 0;
            this.coordY = 0;
            this.coordXAff = 0;
            this.coordYAff = 0;

            this.coordXcibleBasse = 0;
            this.coordYcibleBasse = 0;
            this.coordXcibleHaute = 0;
            this.coordYcibleHaute = 0;
            this.coordXcibleMiddle = 0;
            this.coordYcibleMiddle = 0;

            this.coordXBaseBasse = 0;
            this.coordYBaseBasse = 0;
            this.coordXBaseHaute = 0;
            this.coordYBaseHaute = 0;
        }
    }

    /**
     * Rempli le fond
     * @param g2d
     */
    public abstract void zonePeindre(Graphics g2d);
    
    /**
     * Trace la zone de tir
     * @param g2d
     */
    public abstract void zoneTracer(Graphics g2d);
    
    public static int getAngle(int leX, int leY)
    {
        Integer troc = leX;
        Integer troc2 = leY;
        Double yop = Math.atan2(troc.doubleValue(), troc2.doubleValue());
        Double finalangle = Math.toDegrees(yop);
        int angle = 180 - finalangle.intValue();
        return angle;
    }
}
