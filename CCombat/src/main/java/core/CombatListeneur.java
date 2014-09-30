package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class CombatListeneur
{

    /**
     * Mouse listeneur sur le panel de jeu, niveau click
     * @return
     */
    public MouseListener getMouseListeneur()
    {
        MouseListener mouseL = new MouseListener()
        {
            public void mouseClicked(MouseEvent mouseEv)
            {
            }

            public void mouseEntered(MouseEvent arg0)
            {
            }

            public void mouseExited(MouseEvent arg0)
            {
            }

            public void mousePressed(MouseEvent mouseEv)
            {
                CombatManager.getInstance().gererPlacement(mouseEv);
            }

            public void mouseReleased(MouseEvent arg0)
            {
            }
        };
        return mouseL;
    }

    /**
     * Mouse listeneur sur le panel de jeu, niveau mouvement
     * @return
     */
    public MouseMotionListener getMouseML()
    {
        MouseMotionListener mouseM = new MouseMotionListener()
        {
            public void mouseMoved(MouseEvent mouseEv)
            {
                ZoneCombat.getInstance().majLiveMouse(mouseEv);
            }

            public void mouseDragged(MouseEvent arg0)
            {

            }
        };
        return mouseM;
    }


    /**
     * Action sur bouton zone debug
     * @return
     */
    public ActionListener getBtnDebugZoneL()
    {
        ActionListener leZD = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                VisualManager.getInstance().clickDebug();
            }
        };
        return leZD;
    }
    
    /**
     * Action sur bouton zone debug
     * @return
     */
    public ActionListener getBtnNextRoundL()
    {
        ActionListener leNR = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                CombatManager.getInstance().clickNextRound();
            }
        };
        return leNR;
    }
}
