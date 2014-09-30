package connect;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import elements.CombattantExchange;
import elements.Obstacle;
import exchange.GameExchange;
import exchange.PlayerExchange;
import fixed.EnumEnvoyer;
import core.CombatManager;
import core.VisualManager;
import core.ZoneCalcul;

public class CombatRecevoir extends Thread
{
    // connexion des clients
    private ServerSocket serveur;
    private Socket connexion;
    private ObjectInputStream entree;
    private String adresseautrejoueur;

    /**
     * 
     * @param theGame
     * @param opponent
     * @throws Exception
     */
    public CombatRecevoir(GameExchange theGame, PlayerExchange opponent) throws Exception
    {

        if (opponent == null)
        {
            try
            {
                serveur = new ServerSocket(theGame.getPort(), 100);// 5001
                connexion = serveur.accept();
                adresseautrejoueur = connexion.getInetAddress().getHostAddress();
                // attente que l'autre lance son serveur
                sleep(3000);
                VisualManager.getInstance().createEnvoyeur(adresseautrejoueur, theGame.getPort() +1);
            }
            catch (Exception e)
            {
                VisualManager.getInstance().thereIsAnError("<Recevoir> \n Probleme connexion !", true);
                e.printStackTrace();
            }
        }
        else
        {
            // try{
            adresseautrejoueur = opponent.getAdresse();
            serveur = new ServerSocket(theGame.getPort()+1, 100);// 5002
            VisualManager.getInstance().createEnvoyeur(adresseautrejoueur, theGame.getPort());
            connexion = serveur.accept();
        }
        // catch (Exception e) {e.printStackTrace();}}

        try
        {
            this.entree = new ObjectInputStream(this.connexion.getInputStream());
        }
        catch (Exception e)
        {
            VisualManager.getInstance().thereIsAnError("<Recevoir> \n Probleme connexion !", true);
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    public void run()
    {
        Character lemessage;

        try
        {
            for (;;)
            {
                Object yo = entree.readObject();
                lemessage = (Character) yo;
                EnumEnvoyer recu = EnumEnvoyer.resolve(lemessage);
                
                switch (recu)
                {
                    case heroActive: // envoi d'un perso
                    {
                        CombattantExchange unNewHero = (CombattantExchange) entree.readObject();
                        CombatManager.getInstance().addAutresHeros(unNewHero);
                        break;
                    }
                    case heroTouche: // envoi d'une MAJ d'un perso
                    {
                        CombattantExchange unNewHero = (CombattantExchange) entree.readObject();
                        CombatManager.getInstance().majHeros(unNewHero);
                        break;
                    }
                    case heroMort: // envoi d'un perso
                    {
                        CombattantExchange unNewMort = (CombattantExchange) entree.readObject();
                        CombatManager.getInstance().addAutresMort(unNewMort);                        
                        break;
                    }
                    case fire: // joueur 1 a calculer les mort
                    {
                        VisualManager.getInstance().btnNextRound.setEnabled(true);
                        break;
                    }

                    case pret: // l'autre dit : Je suis pret;
                    {
                        CombatManager.getInstance().otherReady();
                        break;
                    }
                    case restartRound: // Joueur 1 dit : round suivant
                    {
                        CombatManager.getInstance().majRound();
                        break;
                    }

                    case pseudo: // envoi d'un perso
                    {
                        String lautrePseudo = (String) entree.readObject();
                        VisualManager.getInstance().majAutrePseudo(lautrePseudo);
                        break;
                    }
                    case obstacle: // envoi d'un perso
                    {
                        Obstacle unNewObstacle =  (Obstacle) entree.readObject();
                        ZoneCalcul.getInstance().lesPolyBloc.add(unNewObstacle);
                        ZoneCalcul.getInstance().resetZone();
                        break;
                    }
                    case nothing :
                    {
                        break;
                    }
                }
            }
        }
        catch (SocketException e2)
        {
            VisualManager.getInstance().thereIsAnError("<Recevoir> \n L'adversaire s'est déconnecté", true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
