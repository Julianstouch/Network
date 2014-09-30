package connect;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import fixed.EnumEnvoyer;

public class CombatEnvoyer
{
    // connexion des clients
    private Socket connexion;
    private ObjectOutputStream sortie;

    /**
     * 
     * @param laculotte
     * @param port
     * @throws Exception
     */
    public CombatEnvoyer(String laculotte, Integer port) throws Exception
    {

        try
        {
            connexion = new Socket(laculotte, port);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // try{
        this.sortie = new ObjectOutputStream(this.connexion.getOutputStream());
        sortie.flush();
        // }catch (Exception e) {e.printStackTrace();}
    }

    /**
     * Envoi Simple
     * @param typeEnvoi
     */
    public <TRUC extends Serializable> void envoyer(EnumEnvoyer typeEnvoi)
    {
        envoyer(typeEnvoi, null);
    }
    
    /**
     * Envoi avec objet
     * 
     * @param typeEnvoi
     * @param tempo
     */
    public <TRUC extends Serializable> void envoyer(EnumEnvoyer typeEnvoi, TRUC tempo)
    {
        try
        {
            sortie.writeObject(typeEnvoi.getCode());
            sortie.flush();
            if (tempo != null)
            {
                sortie.writeObject(tempo);
                sortie.flush();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
