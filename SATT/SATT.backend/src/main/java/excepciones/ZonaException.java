/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones;

/**
 *
 * @author ac.zuleta10
 */
public class ZonaException extends Exception
{
    private final String msg;
    
    public ZonaException(String msg)
    {
        super();
        this.msg = msg;
    }
    
    @Override
    public String getMessage()
    {
        return msg;
    }
}
