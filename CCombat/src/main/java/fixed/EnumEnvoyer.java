package fixed;

import java.io.Serializable;

import elements.CombattantExchange;
import elements.Obstacle;

public enum EnumEnvoyer 
{
    heroActive('H', CombattantExchange.class),
    heroTouche('T', CombattantExchange.class),
    heroMort('M', CombattantExchange.class),
    fire('F'),
    restartRound('R'),
    obstacle('O', Obstacle.class),
    pret('P'),
    pseudo('Q'),
    nothing('N');
    
    private final char code;
    
    private Class<? extends Serializable> nextObject;
    
    private EnumEnvoyer(char code)
    {
        this.code = code;
    }
    
    private EnumEnvoyer(char code, Class<? extends Serializable> nextObject)
    {
        this.code = code;
        this.nextObject = nextObject;
    }
    
    public Boolean isCode(EnumEnvoyer testCode)
    {
        return this.code == testCode.code;
    }

    /**
     * @return the nextObject
     */
    public Class<? extends Serializable> getNextObject()
    {
        return nextObject;
    }

    /**
     * @return the code
     */
    public char getCode()
    {
        return code;
    }
    
    public static EnumEnvoyer resolve(char code)
    {
        for (EnumEnvoyer oneEnum : EnumEnvoyer.values())
        {
            if(oneEnum.code == code)
                return oneEnum;
        }
        return nothing;
    }
    
}