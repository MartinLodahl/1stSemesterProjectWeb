/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Pravien
 */
public class DontExistException extends Exception
{
     private String type;
     private int id;

    public DontExistException(int id, String type)
    {
       this.id=id;
       this.type=type;
    }

    DontExistException()
    {
        this.toString();
    }

    @Override
    public String toString()
    {
        return type+" with "+"id "+id+" doesn't exist";
    }
    
}
