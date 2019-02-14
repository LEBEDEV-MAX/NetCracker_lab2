package Server.Controller.Command;

import CommonPacage.Controller.Exceptions.ExistCustomerException;
import CommonPacage.Controller.Exceptions.WrongArgumentException;
import CommonPacage.Controller.Exceptions.WrongParameterException;
import CommonPacage.Model.Customer;
import Server.Model.CustomerDB;

import java.io.IOException;
import java.io.ObjectOutput;
import java.util.Map;

/**
 * This class create new customer and add his to DB
 */
public class CreateCustomer implements Command {
    /**
     * @see Server.Model.CustomerDB
     */
    private CustomerDB db;

    public CreateCustomer(CustomerDB db){
        this.db = db;
    }

    /**
     * This method returns id argument of customer from map
     * @param map contains (parameter -> argument)
     * @return id of customer
     * @throws WrongArgumentException when user wrote wrong id argument
     * @see WrongArgumentException
     * @throws WrongParameterException when user wrote wrong id parameter
     * @see WrongParameterException
     */
    private int getID(Map<String, String> map) throws WrongArgumentException, WrongParameterException {
        String id = map.get("id");
        if(id != null){
            try{
                return Integer.parseInt(id);
            }
            catch (Exception e){
                throw new WrongArgumentException("id = " + id + " in CreateCustomer command");
            }
        }
        else{
            throw new WrongParameterException("parameter 'id' not found in CreateCustomer command");
        }
    }

    /**
     * This method returns argument by his parameter name from map
     * @param map contains (parameter -> argument)
     * @param parameterName which argument do you want to get
     * @throws WrongParameterException when user wrote wrong name | phone | address parameters
     * @see WrongParameterException
     */
    private String getStringArgument(Map<String, String> map, String parameterName) throws WrongParameterException {
        String argument = map.get(parameterName);
        if(argument != null)
            return argument;
        else
            throw new WrongParameterException("parameter '" + parameterName + "' not found in CreateCustomer command");
    }


    /**
     * This method handles client's request
     * @param map contains (parameter -> argument)
     * @param response is ObjectOutputStream (response for client)
     * @throws WrongArgumentException
     * @see WrongArgumentException
     * @throws WrongParameterException
     * @see WrongParameterException
     * @throws ExistCustomerException
     * @see ExistCustomerException
     * @throws IOException
     */
    @Override
    public void execute(Map<String, String> map, ObjectOutput response) throws WrongArgumentException,
            WrongParameterException, ExistCustomerException, IOException {

        Customer customer = new Customer(
                getID(map),
                getStringArgument(map,"name"),
                getStringArgument(map,"phone"),
                getStringArgument(map,"address")
        );

        /**
         * @see CustomerDB#addCustomer(Customer)
         */
        db.addCustomer(customer);

        response.writeUTF("SUCCESS");
        response.writeUTF("CreateCustomer");
        response.flush();
    }
}
