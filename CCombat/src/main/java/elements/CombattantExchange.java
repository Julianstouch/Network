package elements;

import java.io.Serializable;

public class CombattantExchange implements Serializable
{

    public Integer coordXcibleBasse;
    public Integer coordYcibleBasse;
    public Integer coordXcibleMiddle;
    public Integer coordYcibleMiddle;
    public Integer coordXcibleHaute;
    public Integer coordYcibleHaute;
    public Integer coordXBaseBasse;
    public Integer coordYBaseBasse;
    public Integer coordXBaseHaute;
    public Integer coordYBaseHaute;

    public Integer type;
    public boolean active;
    public boolean pose;

    public boolean mort;
    public boolean mortDansRound;
    
    public Integer matricule;

    public Integer coordX;
    public Integer coordY;
    public Integer coordXAff;
    public Integer coordYAff;
    public Integer angle;
    public Integer angleConeBas;
    public Integer angleCone;
    public Integer angleConeHaut;
    
    public CombattantExchange(Integer sonMatricule)
    {
        mort = false;
        mortDansRound = false;
        active = false;
        pose = false;
        angle = 0;
        coordX = 0;
        coordY = 0;
        coordXAff = 0;
        coordYAff = 0;
        coordXcibleBasse = 0;
        coordYcibleBasse = 0;
        coordXcibleHaute = 0;
        coordYcibleHaute = 0;
        matricule = sonMatricule;
    }

}
