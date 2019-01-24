package Server.Controller.Command;

import CommonPacage.Controller.Exceptions.CustomerNotFoundException;
import CommonPacage.Controller.Exceptions.WrongArgumentException;
import CommonPacage.Controller.Exceptions.WrongParameterException;
import CommonPacage.Model.Customer;
import Server.Model.CustomerDB;

import java.io.IOException;
import java.io.ObjectOutput;
import java.util.Map;

/**
 * This class return customer by id
 */
public class GetCustomer implements Command{
    /**
     * @see CustomerDB
     */
    private CustomerDB db;

    public GetCustomer(CustomerDB db){
        this.db = db;
    }

    /**
     * @see Command
     * @param map contains (parameter -> argument)
     * @param response is ObjectOutputStream (response for client)
     */
    @Override
    public void execute(Map<String, String> map, ObjectOutput response)throws WrongArgumentException,
            WrongParameterException, CustomerNotFoundException, IOException {
        try{
            int id = getID(map);
            Customer customer = getCustomerByID(id);

            response.writeUTF("SUCCESS");
            response.writeUTF("GetCustomer");
            response.writeObject(customer);
            response.flush();
        }
        catch (Exception e){
            throw e;
        }
    }

    /**
     * This method get customer by his id from DB
     * @param id of customer
     * @return Customer whose you want to get
     * @throws CustomerNotFoundException when customer not found in DB by id
     * @see ClassNotFoundException
     */
    private Customer getCustomerByID(int id) throws CustomerNotFoundException{
        Customer customer = db.getCustomer(id);
        if (customer != null) {
            return customer;
        }
        else{
            throw new CustomerNotFoundException("by id = " + id + " in GetCustomer command");
        }
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
                throw new WrongArgumentException("id = " + id + " in GetCustomer command");
            }
        }
        else{
            throw new WrongParameterException("parameter 'id' not found in GetCustomer command");
        }
    }
}
