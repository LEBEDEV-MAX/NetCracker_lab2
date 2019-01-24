package Server.Controller.Command;

import CommonPacage.Model.Customer;
import Server.Model.CustomerDB;

import java.io.IOException;
import java.io.ObjectOutput;
import java.util.List;
import java.util.Map;

/**
 * This class returns all customers from DB
 */
public class GetAllCustomers implements Command {
    /**
     * @see Server.Model.CustomerDB
     */
    private CustomerDB db;

    public GetAllCustomers(CustomerDB db){
        this.db = db;
    }

    /**
     * @see Command
     * @param map contains (parameter -> argument)
     * @param response is ObjectOutputStream (response for client)
     * @throws IOException
     */
    @Override
    public void execute(Map<String, String> map, ObjectOutput response) throws IOException {
        List<Customer> customers = db.getCustomers();

        response.writeUTF("SUCCESS");
        response.writeUTF("GetAllCustomers");
        response.writeInt(customers.size());

        for (Customer customer: customers){
            response.writeObject(customer);
        }
        response.flush();
    }
}
