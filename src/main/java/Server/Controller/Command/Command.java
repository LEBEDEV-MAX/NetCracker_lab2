package Server.Controller.Command;

import java.io.ObjectOutput;
import java.util.Map;

/**
 * Basic interface for commands
 */
public interface Command {
    /**
     * This method runs command execution for client
     * @param map contains (parameter -> argument)
     * @param response is ObjectOutputStream (response for client)
     */
    public void execute(Map<String, String> map, ObjectOutput response) throws Exception;
}
