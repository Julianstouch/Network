package databased;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import server.DispatchServeur;
import exchange.PlayerExchange;

public class JFenTrisServeurThread extends Thread implements KeyListener
{
    // connexion des clients
    private ServerSocket serveur6000;
    private Socket connexion6000;
    private ObjectOutputStream sortie6000;
    private ObjectInputStream entree6000;

    private ArrayList<PlayerExchange> fullJoueur;

    public JFenTrisServeurThread()
    {
        try
        {
            serveur6000 = new ServerSocket(6000, 100);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        try
        {
            DispatchServeur.AFFICHAGE.append("\nJTrisReady : started !\n");

            for (;;)
            {
                connexion6000 = serveur6000.accept();
                this.sortie6000 = new ObjectOutputStream(this.connexion6000.getOutputStream());
                sortie6000.flush();
                this.entree6000 = new ObjectInputStream(this.connexion6000.getInputStream());
                String lemessage = (String) entree6000.readObject();
                if (lemessage.equals("listejoueur"))
                {
                    DispatchServeur.AFFICHAGE.append("\nJTrisReady : ListeJoueur pour " + connexion6000.getInetAddress().getHostAddress());
                    remplirpardefaut();
                    sortie6000.writeObject(fullJoueur);
                    sortie6000.flush();
                }
                if (lemessage.equals("newjoueur"))
                {
                    DispatchServeur.AFFICHAGE.append("\nJTrisReady : InsertJoueur depuis " + connexion6000.getInetAddress().getHostAddress());
                    PlayerExchange unnewjoueur = (PlayerExchange) entree6000.readObject();
                    // JTRIServeur.goinsert(unnewjoueur);
                }
                if (lemessage.equals("result"))
                {
                    DispatchServeur.AFFICHAGE.append("\nJTrisReady : InsertResultat depuis " + connexion6000.getInetAddress().getHostAddress());
                    // lire les nom des joueur et inserer les resultat
                    String looser = (String) entree6000.readObject();
                    String winner = (String) entree6000.readObject();

                    // Insertion des données en base
                    // JTRIServeur.goinsertresult(looser, winner);
                }
                if (lemessage.equals("depistage"))
                {
                    String nomJoueur = (String) entree6000.readObject();
                    int nbSort = (Integer) entree6000.readObject();
                    int nbReserve = (Integer) entree6000.readObject();
                    DispatchServeur.AFFICHAGE.append("\nJTrisReady : Check " + nomJoueur + " > " + nbSort + "/" + nbReserve);
                }
                connexion6000.close();
                entree6000.close();
                sortie6000.close();
                DispatchServeur.AFFICHAGE.append("\nJTrisReady : Retour en attente");
                // place la zonne de texte sur le message
                DispatchServeur.AFFICHAGE.setCaretPosition(DispatchServeur.AFFICHAGE.getText().length());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void remplirpardefaut()
    {
        PlayerExchange joueur = new PlayerExchange();
//        joueur.rotation = 1000;
//        joueur.descente = 1007;
//        joueur.droite = 1000;
//        joueur.gauche = 1000;
//        joueur.selection = 1000;
//        joueur.utiliser = 1000;
//
//        joueur.nom = "Joueur";
//        joueur.combat = 0;
//        joueur.totalligne = 0;
//        joueur.victory = 0;
//        joueur.id = 1;
        fullJoueur = new ArrayList<PlayerExchange>();
        fullJoueur.add(joueur);
    }

    public void keyPressed(KeyEvent arg0)
    {
        // TODO Auto-generated method stub

    }

    public void keyReleased(KeyEvent arg0)
    {
        // TODO Auto-generated method stub

    }

    public void keyTyped(KeyEvent arg0)
    {
        System.out.println("une touche a été violentée!");

    }
}