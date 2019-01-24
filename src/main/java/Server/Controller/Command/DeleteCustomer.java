package Server.Controller.Command;

import CommonPacage.Controller.Exceptions.CustomerNotFoundException;
import CommonPacage.Controller.Exceptions.WrongArgumentException;
import CommonPacage.Controller.Exceptions.WrongParameterException;
import Server.Model.CustomerDB;

import java.io.IOException;
import java.io.ObjectOutput;
import java.util.Map;

/**
 * This class delete customer in DB by id
 */
public class DeleteCustomer implements Command {
    /**
     * @see Server.Model.CustomerDB
     */
    private CustomerDB db;

    public DeleteCustomer(CustomerDB db){
        this.db = db;
    }

    /**
     * @see Command
     * @param map contains (parameter -> argument)
     * @param response is ObjectOutputStream (response for client)
     * @throws WrongArgumentException when user wrote wrong id argument
     * @see WrongArgumentException
     * @throws WrongParameterException when user wrote wrong id | name | phone | address parameters
     * @see WrongParameterException
     * @throws CustomerNotFoundException when customer not found in DB by id
     * @see CustomerNotFoundException
     */
    @Override
    public void execute(Map<String, String> map, ObjectOutput response) throws WrongArgumentException,
            WrongParameterException, CustomerNotFoundException, IOException {
        try{
            int id = getID(map);
            db.deleteCustomer(id);

            response.writeUTF("SUCCESS");
            response.writeUTF("DeleteCustomer");
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
                throw new WrongArgumentException("id = " + id + " in DeleteCustomer command" );
            }
        }
        else{
            throw new WrongParameterException("parameter 'id' not found in DeleteCustomer command");
        }
    }
}
