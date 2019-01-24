package CommonPacage.Controller.Exceptions;

/**
 * This exception thrown away when user wrote invalid command name
 */
public class WrongCommandException extends Exception{

    public WrongCommandException(){
        super("Wrong Command");
    }
}

