package server;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import exchange.GameExchange;
import exchange.PlayerExchange;

/**
 * Serveur de distribution d'ip entre joueur et generateur de socket
 * 
 * @author jfeniou
 * 
 */
public class DispatchServeur extends JFrame
{

    public static final JTextArea AFFICHAGE;
    static
    {
        AFFICHAGE = new JTextArea();
    }

    public static final String SEPARATOR = "_";

    // interface IHM
    private Container cont;

    // connexion au serveur.
    private Socket connexionC;
    private ServerSocket serveur;

    // les flux
    private ObjectOutputStream sortieS;
    private ObjectInputStream entreeC;

    private Map<String, GameExchange> mapGameInfo;
    private Map<String, Integer> mapGameArena;
    private Map<String, List<PlayerExchange>> mapGamePlayer;

    private Integer portMin;
    private Integer portMax;
    private List<Integer> portUsed;

    /**
     * Lancement du serveur
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        DispatchServeur client = new DispatchServeur();
        client.lancerServeur();
    }

    /**
     * 
     */
    public DispatchServeur()
    {
        super("Super Serveur");
        cont = getContentPane();
        cont.add(new JScrollPane(AFFICHAGE), BorderLayout.CENTER);
        setSize(350, 500);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mapGamePlayer = new HashMap<String, List<PlayerExchange>>();
        mapGameInfo = new HashMap<String, GameExchange>();
        mapGameArena = new HashMap<String, Integer>();

        portMin = 5001;
        portMax = 5099;
        portUsed = new ArrayList<Integer>();

        try
        {
            serveur = new ServerSocket(5000, 100);
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
    }

    /**
     * 
     */
    public void lancerServeur()
    {
        // Etape n0
        AFFICHAGE.append("      <Official J's Fen Game Serveur>");
        try
        {
            AFFICHAGE.append("\n    ** Server @" + InetAddress.getLocalHost().getHostAddress() + " **");
        }
        catch (UnknownHostException e1)
        {
            AFFICHAGE.append("\n   XXXXX Error on server ! XXXXX");
        }
        AFFICHAGE.append("\n   ===== Ready for Battle ! =====");
        do
        {
            try
            {
                // Etape n1
                openServeur();
                // Etape n2
                obtenirFlux();
                // Etape n3
                traiterConnexion();
                // Etape n4
                fermerConnexion();
            }
            catch (EOFException eof)
            {
                JOptionPane.showMessageDialog(this, eof.getMessage() + "", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException e)
            {
            }
        }
        while (true);
    }

    /**
     * 
     * 
     * @throws IOException
     */
    public void openServeur() throws IOException
    {
        AFFICHAGE.append("\nWaiting ...");
        // pour defiler au dernier message
        AFFICHAGE.setCaretPosition(AFFICHAGE.getText().length());
        this.connexionC = serveur.accept();
    }

    /**
     * 
     * @throws IOException
     */
    public void obtenirFlux() throws IOException
    {
        this.sortieS = new ObjectOutputStream(this.connexionC.getOutputStream());
        sortieS.flush();
        this.entreeC = new ObjectInputStream(this.connexionC.getInputStream());
    }

    /**
     * 
     * @throws IOException
     */
    public void traiterConnexion() throws IOException
    {
        try
        {
            String addrArrivant = connexionC.getInetAddress().getHostAddress();

            GameExchange connectGame = (GameExchange) entreeC.readObject();
            PlayerExchange player = (PlayerExchange) entreeC.readObject();

            initGame(connectGame);

            StringBuilder keyGameArena = new StringBuilder();
            keyGameArena.append(connectGame.getName());
            keyGameArena.append(SEPARATOR);
            Integer arenaNum = mapGameArena.get(connectGame.getName());
            keyGameArena.append(arenaNum);

            List<PlayerExchange> listPlayer = mapGamePlayer.get(keyGameArena.toString());

            if (listPlayer == null || listPlayer.isEmpty())
            {
                AFFICHAGE.append("\n<FGS>" + connectGame.getName() + " New arena n°" + arenaNum + "! " + player.getNom() + "@" + addrArrivant);

                player.setAdresse(addrArrivant);
                player.setNumero(1);
                listPlayer = new ArrayList<PlayerExchange>();
                listPlayer.add(player);
                mapGamePlayer.put(keyGameArena.toString(), listPlayer);
            }
            else
            {
                player.setNumero(2);
                player.setOpponent(listPlayer.get(0));
                AFFICHAGE.append("\n<FGS>" + connectGame.getName() + " Arena n°" + arenaNum + " complete! " + player.getNom() + "@" + addrArrivant);
                mapGameArena.put(connectGame.getName(), arenaNum + 1);
            }

            // for more than 2 players
            // Integer numJoueur = determinerAreneLibre(jeu, addrArrivant, pseudo);

            // pour defiler au dernier message
            AFFICHAGE.setCaretPosition(AFFICHAGE.getText().length());

            sortieS.writeObject(connectGame);
            sortieS.flush();
            sortieS.writeObject(player);
            sortieS.flush();

        }
        catch (Exception e)
        {
            AFFICHAGE.append("\n <FGS>Erreur de dispatch : " + e.getMessage());
            AFFICHAGE.setCaretPosition(AFFICHAGE.getText().length());
        }
    }

    /**
     * Init the connecting game with the port
     * 
     * @param connectGame
     */
    private void initGame(GameExchange connectGame)
    {
        GameExchange refGame;

        // not yet this game in the server
        if (!mapGameInfo.containsKey(connectGame.getName()))
        {
            refGame = new GameExchange();
            refGame.setName(connectGame.getName());
            refGame.setMaxPlayer(connectGame.getMaxPlayer());

            // random port
            int gamePort = aleatoire(portMin, portMax);
            while (portUsed.contains(gamePort))
            {
                gamePort = aleatoire(portMin, portMax);
            }
            refGame.setPort(gamePort);
            portUsed.add(gamePort);     // listener
            portUsed.add(gamePort+1);   // recepter
            
            mapGameInfo.put(connectGame.getName(), refGame);
            mapGameArena.put(connectGame.getName(), 1);

            AFFICHAGE.append("\n<FGS> New game incomming : " + refGame.getName() + " on port :  " + refGame.getPort());
        }

        refGame = mapGameInfo.get(connectGame.getName());
        connectGame.setPort(refGame.getPort());
    }

    /**
     * 
     * @param jeu
     * @param addrArrivant
     * @param pseudo
     * @return
     */
    /*private Integer determinerAreneLibre(String jeu, String addrArrivant, String pseudo)
    {
        // cherche les différentes arenes
        int retour = 0;

        Map<Integer, PlayerExchange> listeJoueur = mapGamePlayer.get(jeu);
        // trouve une libre
        if (listeJoueur == null)
        {
            listeJoueur = new HashMap<Integer, PlayerExchange>();
            mapGamePlayer.put(jeu, listeJoueur);
        }

        PlayerExchange nouveauJoueur = new PlayerExchange();
        nouveauJoueur.nom = pseudo;
        nouveauJoueur.adresse = addrArrivant;

        for (int i = 1; i < 13; i++)
        {
            PlayerExchange joueurExistant = listeJoueur.get(i);
            if (joueurExistant == null)
            {
                if (retour == 0)
                {
                    retour = i;
                    nouveauJoueur.id = i;
                }
            }
            else
            {
                // if(joueurExistant.adresse.equals(addrArrivant))
                // {
                // retour = i;
                // nouveauJoueur = joueurExistant;
                // break;
                // }
            }
        }

        listeJoueur.put(retour, nouveauJoueur);

        return retour;

    }*/

    /**
     * 
     * @throws IOException
     */
    public void fermerConnexion() throws IOException
    {
        sortieS.close();
        entreeC.close();
        connexionC.close();
    }

    // renvoi un nombre aleatoire compris entre min et max
    private int aleatoire(int min, int max)
    {
        return ((int) (Math.random() * (max - min + 1))) + min;
    }

}