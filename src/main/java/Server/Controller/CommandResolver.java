package Server.Controller;

import Server.Controller.Command.Command;
import CommonPacage.Controller.Exceptions.WrongCommandException;

import java.util.Map;

public class CommandResolver {
    /**
     * map contains all commands
     */
    private Map<String, Command> map;

    public CommandResolver(Map<String, Command> map){
        this.map = map;
    }

    /**
     * This method returns Command by his name
     * @param commandName is name of command
     * @return Command
     * @see Command
     * @throws WrongCommandException
     * @see WrongCommandException
     */
    public Command resolveCommand(String commandName) throws WrongCommandException {
        Command command = map.get(commandName);
        if(command != null) {
            return map.get(commandName);
        }
        else {
            throw new WrongCommandException();
        }
    }
}
