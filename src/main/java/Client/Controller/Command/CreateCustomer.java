package Client.Controller.Command;

import java.io.ObjectInput;

public class CreateCustomer implements Command{

    /**
     * @see Command
     * @param response of server
     */
    @Override
    public void execute(ObjectInput response){
        System.out.println("Command completed: Customer created");
    }
}
