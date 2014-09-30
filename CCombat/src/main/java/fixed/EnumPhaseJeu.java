package fixed;

import java.util.EnumSet;
import java.util.Set;

public enum EnumPhaseJeu {

    phaseJustStarted(10),
    phaseJouer(20),
    phasePause(30),
    phasePerdu(40),
    phaseWin(50),
    phaseDraw(60),
    phaseErreur(70);
    
    private int num; 
    
    private EnumPhaseJeu(int num)
    {
        this.num = num;
    }
    
    public boolean isPhase(EnumPhaseJeu enCours)
    {
        return enCours.num == this.num;
    }
    
    /**
     * 
     */
    public final static Set<EnumPhaseJeu> phasesFinies = EnumSet.of(phasePerdu, phaseWin, phaseDraw);
    
    public final static Set<EnumPhaseJeu> phasesJeu = EnumSet.of(phaseJouer, phasePause);
}
