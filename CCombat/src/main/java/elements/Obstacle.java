package elements;

import java.io.Serializable;

public class Obstacle implements Serializable
{

    public int coordXDepart;
    public int coordYDepart;
    public int coordXArrive;
    public int coordYArrive;
    public int type; // 1=mur 2=caisse 3=crater

    public Obstacle(int leXD, int leYD, int leXA, int leYA, int letype)
    {
        coordXDepart = leXD;
        coordYDepart = leYD;
        coordXArrive = leXA;
        coordYArrive = leYA;
        type = letype;
    }

}
