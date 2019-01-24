package Client.Controller.Command;

import java.io.ObjectInput;

/**
 * Basic interface for commands
 */
public interface Command {
    /**
     * This method runs command execution
     * @param response of server
     */
    public void execute(ObjectInput response) throws Exception;
}
