package exchange;

import java.io.Serializable;

/**
 * 
 * @author jfeniou
 *
 */
public class GameExchange  implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 4638060587480151195L;
    
    private String name;
    private int port;
    private String maxPlayer;
    
    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
     * @return the port
     */
    public int getPort()
    {
        return port;
    }
    /**
     * @param port the port to set
     */
    public void setPort(int port)
    {
        this.port = port;
    }
    /**
     * @return the maxPlayer
     */
    public String getMaxPlayer()
    {
        return maxPlayer;
    }
    /**
     * @param maxPlayer the maxPlayer to set
     */
    public void setMaxPlayer(String maxPlayer)
    {
        this.maxPlayer = maxPlayer;
    }
}
