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
 * This class update customer's info by ID
 */
public class UpdateCustomer implements Command {
    /**
     * @see Server.Model.CustomerDB
     */
    private CustomerDB db;

    public UpdateCustomer(CustomerDB db){
        this.db = db;
    }

    /**
     * @see Command
     * @param map contains (parameter -> argument)
     * @param response is ObjectOutputStream (response for client)
     */
    @Override
    public void execute(Map<String, String> map, ObjectOutput response) throws WrongArgumentException,
            WrongParameterException, CustomerNotFoundException, IOException {
        try{
            int id = getID(map);
            Customer customer = getCustomerByID(id);

            if(map.get("name") != null)
                customer.setName(map.get("name"));
            if(map.get("phone") != null)
                customer.setPhone(map.get("phone"));
            if(map.get("address") != null)
                customer.setAddress(map.get("address"));

            response.writeUTF("SUCCESS");
            response.writeUTF("UpdateCustomer");
            response.flush();
        }
        catch (Exception e){
            throw e;
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
                throw new WrongArgumentException("id = " + id + " in UpdateCustomer command");
            }
        }
        else{
            throw new WrongParameterException("parameter 'id' not found in UpdateCustomer command");
        }
    }

    /**
     * This method get customer by his id from DB
     * @param id of customer
     * @return Customer whose you want to get
     * @throws CustomerNotFoundException when customer not found in DB by id
     * @see CustomerNotFoundException
     */
    private Customer getCustomerByID(int id) throws CustomerNotFoundException {
        Customer customer = db.getCustomer(id);
        if (customer != null) {
            return customer;
        }
        else{
            throw new CustomerNotFoundException("by id = " + id + " in UpdateCustomer command");
        }
    }
}
