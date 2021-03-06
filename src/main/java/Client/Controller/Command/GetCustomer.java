package Client.Controller.Command;

import CommonPacage.Model.Customer;

import java.io.IOException;
import java.io.ObjectInput;

public class GetCustomer implements Command{

    /**
     * @see Command
     * @param response of server
     */
    @Override
    public void execute(ObjectInput response) throws IOException, ClassNotFoundException{
        Customer customer = (Customer) response.readObject();

        System.out.println("\tid = " + customer.getId());
        System.out.println("\tname = " + customer.getName());
        System.out.println("\tphone = " + customer.getPhone());
        System.out.println("\taddress = " + customer.getAddress());
    }
}
