package core;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import elements.Bazooka;
import elements.Combattant;
import elements.CombattantExchange;
import elements.CombattantMort;
import elements.Couteau;
import elements.Fusil;
import elements.Sniper;
import elements.Warning;
import fixed.Const;
import fixed.EnumEnvoyer;
import fixed.EnumPhaseJeu;

public class CombatManager
{
    private static CombatManager instance;
    
    public Map<Integer, Combattant> lesHeros;
    public List<Combattant> lesAutresHeros;
    public List<CombattantMort> lesAutresMort;
    public List<Warning> lesBlesses;
    
    public Integer current = 0;
    public int persoNombre; // pour i = 1 a 6
    //public int persoLimite; // tant que < a limite
    
    public Integer leNumeroJoueur;

    public Boolean pretMoi;
    public Boolean pretLui;

    public Boolean nextMoi;
    public Boolean nextLui;
    
    public EnumPhaseJeu phaseJeu;
    
    private PositionManager posManag;
    
    public static CombatManager getInstance()
    {
        return instance;
    }
    
    public CombatManager(Integer numJoueur)
    {
        instance = this;
        
        
        lesHeros = new HashMap<Integer, Combattant>();
        lesAutresHeros = new ArrayList<Combattant>();
        lesAutresMort = new ArrayList<CombattantMort>();
        lesBlesses = new ArrayList<Warning>();
        posManag = new PositionManager();
        
        phaseJeu = EnumPhaseJeu.phaseJustStarted;
        persoNombre = Const.nombreFusil + Const.nombreCouteau + Const.nombreBazooka + Const.nombreSniper;
        //persoLimite = persoNombre + 1;

        leNumeroJoueur = numJoueur;

        // Listeneur leListen = new Listeneur();

        for (int i = 0; i < Const.nombreFusil; i++)
        {
            lesHeros.put(i, new Fusil(i));
        }
        for (int i = Const.nombreFusil; i < Const.nombreFusil + Const.nombreCouteau; i++)
        {
            lesHeros.put(i, new Couteau(i));
        }
        for (int i = Const.nombreCouteau + Const.nombreFusil; i < Const.nombreFusil + Const.nombreCouteau + Const.nombreBazooka; i++)
        {
            lesHeros.put(i, new Bazooka(i));
        }
        for (int i = Const.nombreCouteau + Const.nombreFusil + Const.nombreBazooka; i < Const.nombreFusil + Const.nombreCouteau + Const.nombreBazooka + Const.nombreSniper; i++)
        {
            lesHeros.put(i, new Sniper(i));
        }
        
        pretMoi = false;
        pretLui = false;
        
        nextMoi = false;
        nextLui = false;
    }
    
    public void demarrer()
    {
        posManag.activer();        
    }
    
    /**
     * 
     */
    public void startFire()
    {
        phaseJeu = EnumPhaseJeu.phasePause;

        if (leNumeroJoueur == Const.joueur1)
        {
            ZoneCalcul.getInstance().calculDesMort();
            VisualManager.getInstance().getEnvoyeur().envoyer(EnumEnvoyer.fire);
            VisualManager.getInstance().btnNextRound.setEnabled(true);
        }
        
    }
    
    /**
     * 
     */
    private void resolution()
    {
        int nbmortJ1 = 0;
        int nbmortJ2 = 0;

        // mise a jour des 2 liste du J1 
        for (Combattant unHero : lesHeros.values())
        {
            if (unHero.mortDansRound)
            {
                nbmortJ1++;
            }
            if (unHero.mort)
            { // l'ancien
                nbmortJ1++;
            }
        }

        // le nombre de mort du J2
        nbmortJ2 = lesAutresMort.size();

        // match null
        if (nbmortJ1 == persoNombre && nbmortJ2 == persoNombre)
        {
            this.majResolu(Const.jeuDraw);
            // VisualManager.getInstance().getEnvoyeur().envoyerDraw();
        } // GG J2
        else if (nbmortJ1 == persoNombre)
        {
            this.majResolu(Const.jeuJ2Win);
            // VisualManager.getInstance().getEnvoyeur().envoyerGagner();
        }// GG J1
        else if (nbmortJ2 == persoNombre)
        {
            this.majResolu(Const.jeuJ1Win);
            // VisualManager.getInstance().getEnvoyeur().envoyerPerdu();
        }// On refait
        else
        {
            this.majResolu(Const.jeuNewRound);
            // VisualManager.getInstance().getEnvoyeur().envoyerRestart();
        }
    }

    public void gererPlacement(MouseEvent mouseEv)
    {
        posManag.gererPlacement(mouseEv);
    }
    
    /**
     * 
     * @param unNewMort
     */
    public void addAutresMort(CombattantExchange unNewMort)
    {
        CombattantMort unMort = new CombattantMort(unNewMort);
        lesAutresMort.add(unMort);
        lesBlesses.add(new Warning(unMort));
    }

    /**
     * 
     * @param uneMAJHero
     */
    public void majHeros(CombattantExchange uneMAJHero)
    {
        Combattant unDead = lesHeros.get(uneMAJHero.matricule);
        unDead.mortDansRound = uneMAJHero.mortDansRound;
        lesBlesses.add(new Warning(unDead));
    }

    public void addAutresHeros(CombattantExchange unHero)
    {
        Combattant unNewHero = null;
        if(unHero.type == Const.typeFusil)
            unNewHero = new Fusil(unHero.matricule);
        else if(unHero.type == Const.typeCouteau)
            unNewHero = new Couteau(unHero.matricule);
        else if(unHero.type == Const.typeBazooka)
            unNewHero = new Bazooka(unHero.matricule);
        else if(unHero.type == Const.typeSniper)
            unNewHero = new Sniper(unHero.matricule);
        
        unNewHero.alimenterDepuisExchange(unHero);
        lesAutresHeros.add(unNewHero);
    }
    
    /**
     * Return current Hero
     * @return
     */
    public Combattant getCurrentHero()
    {
        if(EnumPhaseJeu.phaseJouer.isPhase(phaseJeu))
            return lesHeros.get(current);
        return null;
    }

    /**
     * Switch to next Hero
     * 
     */
    public Boolean switchNextHero()
    {
        // pret : on l'envoi a l'autre joueur
        VisualManager.getInstance().getEnvoyeur().envoyer(EnumEnvoyer.heroActive, lesHeros.get(current).generateExchange());
               
        // tant que le suivant n'est par mort, ou qu'on soit au bout
        while (current < persoNombre - 1)
        {
            current++;
            // s'il n'est pas mort : ok on a bien nexté
            if (!lesHeros.get(current).mort)
                return true;
        }

        // sinon c la fin
        pretMoi = true;
        VisualManager.getInstance().getEnvoyeur().envoyer(EnumEnvoyer.pret);
        current = -1;
        if (pretLui)//leNumeroJoueur == 2)
        {
            CombatManager.getInstance().startFire();
            //if (pretLui)
            //    VisualManager.getInstance().setEnablegoFire(true);
        }
        return false;
    }

    /**
     * 
     * @param jeunewround
     */
    public void majResolu(int jeunewround)
    {
        int posNonMort = -1;        
        for (int i = 0; i < persoNombre; i++)
        {
            Combattant unHero = lesHeros.get(i);
            if (unHero.mortDansRound)
            {
                unHero.mort = true;
            }
 
            unHero.restartRound();
            
            if(!unHero.mort && posNonMort == -1)
                posNonMort = i;
        }
        current = posNonMort;
        
        lesAutresHeros.clear();
        lesBlesses.clear();
        
        switch (jeunewround)
        {
            case 1: // on refait
            {
                posManag.activer();
                phaseJeu = EnumPhaseJeu.phaseJouer;   
                break;
            }
            case 2: // perdu
            {
                phaseJeu = EnumPhaseJeu.phasePerdu;
                break;
            }
            case 3: // gagner
            {
                phaseJeu = EnumPhaseJeu.phaseWin;
                break;
            }
            case 4: // egalite
            {
                phaseJeu = EnumPhaseJeu.phaseDraw;
                break;
            }
        }
    }

    /**
     * 
     */
    public void otherReady()
    {
        pretLui = true;
        //if (leNumeroJoueur == Const.joueur2)
        //{
            if (pretMoi)
                //VisualManager.getInstance().setEnablegoFire(true);
                CombatManager.getInstance().startFire();
        //}
    }

    /**
     * 
     */
    public void clickNextRound()
    {
        nextMoi = true;
        // desactive le bouont
        VisualManager.getInstance().btnNextRound.setEnabled(false);
        VisualManager.getInstance().getEnvoyeur().envoyer(EnumEnvoyer.restartRound);
        nextRound();
    }

    /**
     * 
     */
    public void nextRound()
    {
        if(nextMoi && nextLui)
        {
            pretMoi = false;
            pretLui = false;
            
            nextMoi = false;
            nextLui = false;
            
            resolution();
        }
    }

    public void majRound()
    {
        nextLui = true;
        nextRound();
    }
    
}
