package CommonPacage.Controller.Exceptions;

/**
 * This exception thrown away when customer is not found by specified ID
 */
public class CustomerNotFoundException extends Exception{

    public CustomerNotFoundException(String str){
        super("Customer not found: " + str);
    }
}
