package Client.Controller.Command;

import CommonPacage.Model.Customer;

import java.io.IOException;
import java.io.ObjectInput;

public class FindCustomer implements Command{
    /**
     * @see Command
     * @param response of server
     */
    @Override
    public void execute(ObjectInput response) throws IOException, ClassNotFoundException{
        int customerValue = response.readInt();

        if(customerValue != 0){
            for(int i=0; i < customerValue; i++){
                Customer customer = (Customer) response.readObject();

                System.out.println((i+1) + ")");
                System.out.println("\tid = " + customer.getId());
                System.out.println("\tname = " + customer.getName());
                System.out.println("\tphone = " + customer.getPhone());
                System.out.println("\taddress = " + customer.getAddress());
            }
        }
        else{
            System.out.println("Customers are not found by this name");
        }
    }
}
