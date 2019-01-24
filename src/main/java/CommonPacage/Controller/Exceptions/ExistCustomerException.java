package CommonPacage.Controller.Exceptions;

/**
 * This exception thrown away when customer exist in DB
 */
public class ExistCustomerException extends Exception{

    public ExistCustomerException(String str){
        super("Customer already exist: " + str);
    }
}
