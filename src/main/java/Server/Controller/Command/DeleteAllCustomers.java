package Server.Controller.Command;

import Server.Model.CustomerDB;

import java.io.IOException;
import java.io.ObjectOutput;
import java.util.Map;

/**
 * This class delete all customers in DB
 */
public class DeleteAllCustomers implements Command {
    /**
     * @see Server.Model.CustomerDB
     */
    private CustomerDB db;

    public DeleteAllCustomers(CustomerDB db){
        this.db = db;
    }

    /**
     * @see Command
     * @param map contains (parameter -> argument)
     * @param response is ObjectOutputStream (response for client)
     * @throws IOException
     */
    @Override
    public void execute(Map<String, String> map, ObjectOutput response) throws IOException{
        db.deleteAllCustomers();
        response.writeUTF("SUCCESS");
        response.writeUTF("DeleteAllCustomers");
        response.flush();
    }
}
