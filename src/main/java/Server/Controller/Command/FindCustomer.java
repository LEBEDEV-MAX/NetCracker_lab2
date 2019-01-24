package Server.Controller.Command;

import CommonPacage.Controller.Exceptions.WrongArgumentException;
import CommonPacage.Controller.Exceptions.WrongParameterException;
import CommonPacage.Model.Customer;
import Server.Model.CustomerDB;

import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class find customer by his name and operation
 * operation - search principle; contains arguments: equals | contains | startWith; like
 * equals - will find customer by full name match
 * contains - will find customer if his name contains 'name' argument
 * startWith - will find customer if his name start with 'name' argument
 * like - will find customer if it matches the pattern (pattern = 'name' argument)
 */
public class FindCustomer implements Command {
    /**
     * @see CustomerDB
     */
    private CustomerDB db;

    public FindCustomer(CustomerDB db){
        this.db = db;
    }

    /**
     * @see Command
     * @param map contains (parameter -> argument)
     * @param response is ObjectOutputStream (response for client)
     */
    @Override
    public void execute(Map<String, String> map, ObjectOutput response) throws Exception{
        try{
            String name = getName(map);
            String operation = getOperation(map);
            List<Customer> customers = new ArrayList<>();

            switch (operation){
                case "equals":
                    db.findByEquals(name, customers);
                    break;
                case "contains":
                    db.findByContains(name, customers);
                    break;
                case "startWith":
                    db.findByStartWith(name, customers);
                    break;
                case "like":
                    db.findByLike(name, customers);
                    break;
                default:
                    throw new WrongArgumentException("operation = " + operation + " in FindCustomer command");
            }

            response.writeUTF("SUCCESS");
            response.writeUTF("FindCustomer");
            response.writeInt(customers.size());
            for(Customer customer : customers){
                response.writeObject(customer);
            }
            response.flush();

        }
        catch (Exception e){
            throw e;
        }
    }

    /**
     * This method returns operation argument
     * @param map contains (parameter -> argument)
     * @return operation argument
     * @throws WrongParameterException when user wrote wrong operation parameter
     * @see WrongParameterException
     */
    private String getOperation(Map<String, String> map) throws WrongParameterException {
        String operation = map.get("operation");
        if(operation != null){
            return operation;
        }
        else throw new WrongParameterException("parameter 'operation' not found in FindCustomer command");
    }

    /**
     * This method returns name of customer who you want to find
     * @param map contains (parameter -> argument)
     * @return name of customer
     * @throws WrongParameterException when user wrote wrong operation parameter
     * @see WrongParameterException
     */
    private String getName(Map<String, String> map) throws WrongParameterException{
        String name = map.get("name");
        if(name != null){
            return name;
        }
        else throw new WrongParameterException("parameter 'name' not found in FindCustomer command");
    }
}
