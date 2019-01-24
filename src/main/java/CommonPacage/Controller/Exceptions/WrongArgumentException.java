package CommonPacage.Controller.Exceptions;

/**
 * This exception thrown away when user wrote invalid argument
 */
public class WrongArgumentException extends Exception {

    public WrongArgumentException(String str){
        super("Wrong Argument: " + str);
    }
}

