package connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import exchange.GameExchange;
import exchange.PlayerExchange;
import core.VisualManager;

/**
 * Client de connexion au serveur JNet, the Official J's Fen Game Serveur
 * 
 * @author jfeniou
 *
 */
public class CombatClientJNet
{

    private Socket client;
    private final int portDispatchServeur = 5000;
    private String pseudoMoi;
    
    private PlayerExchange itsMe;
    private PlayerExchange opponent;
    private GameExchange theGameInfo;

    private ObjectInputStream entree;
    private ObjectOutputStream sortie;

    /** Creates a new instance of Client 
     * @param pseudoMoi */
    public CombatClientJNet(String addrDispatchServeur, String pseudoMoi)
    {
        this.pseudoMoi = pseudoMoi;
        // Etape n1; connexion serveur principal de dispatch
        try
        {
            connectClient(addrDispatchServeur, portDispatchServeur);
        }

        catch (Exception e)
        {
            VisualManager.getInstance().thereIsAnError("<CombatClient> \n Serveur <FGS> introuvable en : " + addrDispatchServeur, true);
        }
    }

    /**
     * 
     * @param addr
     * @param port
     * @throws IOException
     */
    public void connectClient(String addr, int port) throws IOException
    {
        client = new Socket(addr, port);
        this.sortie = new ObjectOutputStream(this.client.getOutputStream());
        
        GameExchange game = new GameExchange(); 
        game.setName("CCombat");
        PlayerExchange player = new PlayerExchange();      
        player.setNom(pseudoMoi);
        
        sortie.writeObject(game);
        sortie.flush();
        sortie.writeObject(player);
        sortie.flush();
        this.entree = new ObjectInputStream(this.client.getInputStream());
    }
    
    /**
     * 
     */
    public void lancerClient()
    {
        // Etape n2;
        try
        {
            GameExchange game = (GameExchange) entree.readObject();
            PlayerExchange player = (PlayerExchange) entree.readObject();
            
            itsMe = player;
            opponent = player.getOpponent();
            theGameInfo = game; 
            
            fermerConnexion();
            
        }
        catch (Exception e)
        {
            VisualManager.getInstance().thereIsAnError("<CombatClient> \n Probleme de transfert au serveur", true);
        }
    }


    /**
     * 
     * @throws IOException
     */
    public void fermerConnexion() throws IOException
    {
        sortie.close();
        entree.close();
        client.close();
    }

    /**
     * @return the itsMe
     */
    public PlayerExchange getItsMe()
    {
        return itsMe;
    }

    /**
     * @return the opponent
     */
    public PlayerExchange getOpponent()
    {
        return opponent;
    }

    /**
     * @return the theGameInfo
     */
    public GameExchange getTheGameInfo()
    {
        return theGameInfo;
    }

}