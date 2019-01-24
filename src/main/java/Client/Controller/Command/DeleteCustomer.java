package Client.Controller.Command;

import java.io.ObjectInput;

public class DeleteCustomer implements Command {

    /**
     * @see Command
     * @param response of server
     */
    public void execute(ObjectInput response){
        System.out.println("Command complete: customer deleted");
    }
}
