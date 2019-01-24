package Client.Controller;


import java.io.IOException;
import java.io.ObjectInput;

public class Interpreter {

    /**
     * This method returns command result
     * @param response of server
     * @return command result
     * @throws IOException
     */
    public String getCommandResult(ObjectInput response) throws IOException{
        return response.readUTF();
    }

    /**
     * This method returns command name
     * @param response of server
     * @return command name
     * @throws IOException
     */
    public String getCommandName(ObjectInput response) throws IOException{
        return response.readUTF();
    }
}
