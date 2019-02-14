package Client.Controller;

import Client.Controller.Command.Command;
import Client.View.PrintException;
import CommonPacage.Controller.Exceptions.WrongCommandException;

import java.io.IOException;
import java.io.ObjectInput;

public class TerminalController {
    /**
     * @see Interpreter
     */
    private Interpreter interpreter;
    /**
     * @see CommandResolver
     */
    private CommandResolver resolver;
    /**
     * @see PrintException
     */
    private PrintException printE;

    public TerminalController(Interpreter interpreter, CommandResolver resolver, PrintException printE){
        this.interpreter = interpreter;
        this.resolver = resolver;
        this.printE = printE;
    }

    /**
     * This method interprets commandResult
     * If it is FAIL then call method fail(), else call method success()
     * @param response of server
     * @throws IOException
     * @throws WrongCommandException
     * @see WrongCommandException
     */
    public void start(ObjectInput response) throws Exception{
        String commandResult = interpreter.getCommandResult(response);

        switch (commandResult){
            case "FAIL":
                fail(response);
                break;
            case "SUCCESS":
                success(response);
                break;
        }
    }

    /**
     * This method interpret commandName
     * create Command by his name and calls method execute of command
     * @param response of server
     * @throws IOException
     * @throws WrongCommandException
     */
    private void success(ObjectInput response) throws Exception{
        String commandName = interpreter.getCommandName(response);
        Command command = resolver.resolveCommand(commandName);
        command.execute(response);
    }

    /**
     * This method prints errors
     * @param response of server
     * @throws IOException
     */
    private void fail(ObjectInput response) throws IOException{
        String error = interpreter.getError(response);
        printE.print(error);
    }
}
