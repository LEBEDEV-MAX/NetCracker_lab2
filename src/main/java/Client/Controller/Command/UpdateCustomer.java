package Client.Controller.Command;

import java.io.ObjectInput;

public class UpdateCustomer implements Command {

    /**
     * @see Command
     * @param response of server
     */
    @Override
    public void execute(ObjectInput response){
        System.out.println("Command complete: customer is updating");
    }
}
