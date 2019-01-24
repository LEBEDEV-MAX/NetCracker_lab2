package CommonPacage.Controller.Exceptions;

/**
 * This exception thrown away when user wrote invalid parameter
 */
public class WrongParameterException extends Exception {

    public WrongParameterException(String str){
        super("Wrong Parameter: " + str);
    }
}
