package exchange;

import java.io.Serializable;

/**
 * For DataBase insert
 * 
 * @author jfeniou
 * 
 */
public class PlayerExchange implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -6380732698545802966L;

    private int numero;
    private String nom;
    private String adresse;
    private PlayerExchange opponent;

    /**
     * @return the nom
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * @param nom
     *            the nom to set
     */
    public void setNom(String nom)
    {
        this.nom = nom;
    }

    /**
     * @return the adresse
     */
    public String getAdresse()
    {
        return adresse;
    }

    /**
     * @param adresse
     *            the adresse to set
     */
    public void setAdresse(String adresse)
    {
        this.adresse = adresse;
    }

    /**
     * @return the numero
     */
    public int getNumero()
    {
        return numero;
    }

    /**
     * @param numero
     *            the numero to set
     */
    public void setNumero(int numero)
    {
        this.numero = numero;
    }

    /**
     * @return the opponent
     */
    public PlayerExchange getOpponent()
    {
        return opponent;
    }

    /**
     * @param opponent the opponent to set
     */
    public void setOpponent(PlayerExchange opponent)
    {
        this.opponent = opponent;
    }
}