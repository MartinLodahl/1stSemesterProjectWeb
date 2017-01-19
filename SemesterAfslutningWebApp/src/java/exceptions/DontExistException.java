package exceptions;

public class DontExistException extends Exception
{
     private final String type;
     private final int id;

    public DontExistException(int id, String type)
    {
       this.id=id;
       this.type=type;
    }

    @Override
    public String toString()
    {
        return type+" with "+"id "+id+" doesn't exist";
    }
    
}
