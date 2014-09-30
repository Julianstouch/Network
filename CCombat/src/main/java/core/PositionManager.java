package core;

import java.awt.Color;
import java.awt.event.MouseEvent;

import elements.Combattant;
import fixed.Const;
import graph.ImageDistributor;

public class PositionManager
{
    public int etapeAction;
    
    public void gererPlacement(MouseEvent mouseEv)
    {
        // si on est en mode poser les joueurs, ou les faire tirer
        if (this.etapeAction != Const.actionFini && CombatManager.getInstance().getCurrentHero() != null)
        {
            Combattant mrBonhomme = CombatManager.getInstance().getCurrentHero();

            if (this.etapeAction == Const.actionPoser)
            {
                // test si on peut le poser
                int placement = ImageDistributor.getInstance().getLeFondZone().getRGB(mouseEv.getX(), mouseEv.getY());
                if (ImageDistributor.getInstance().getFondNonPosable() == placement || placement == Color.WHITE.getRGB())
                {
                    return;
                }
                
                // ok
                mrBonhomme.sePlace(mouseEv.getX(), mouseEv.getY());

                this.etapeAction = Const.actionCibler;
                
                // si couteau, on positionne sa zone
                if (mrBonhomme.type != Const.typeCouteau)
                {
                    return;
                }                
            }

            if (this.etapeAction == Const.actionCibler)
            {
                Integer leXangle = mouseEv.getX();
                Integer leYangle = mouseEv.getY();

                mrBonhomme.seActive(leXangle, leYangle);

                Boolean next = CombatManager.getInstance().switchNextHero();
                
                if(next)
                {
                    this.etapeAction = Const.actionPoser;
                }
                else
                {
                    this.etapeAction = Const.actionFini;
                }
            }
        }
    }
    
    /**
     * 
     */
    public void activer()
    {
        this.etapeAction = Const.actionPoser;
    }
}
