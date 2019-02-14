package Server.Controller;

import Server.Controller.Command.Command;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class TerminalController implements Runnable{
    /**
     * @see Interpreter
     */
    private Interpreter interpreter;
    /**
     * @see CommandResolver
     */
    private CommandResolver resolver;
    private Socket client;

    public TerminalController(Interpreter interpreter, CommandResolver resolver, Socket client){
        this.interpreter = interpreter;
        this.resolver = resolver;
        this.client = client;
    }

    /**
     * This method read string from client's request and start commandExecution()
     * @see TerminalController#commandExecution(String, ObjectOutput)
     */
    @Override
    public void run() {
        try(ObjectOutput response = new ObjectOutputStream(client.getOutputStream());
            ObjectInput request = new ObjectInputStream(client.getInputStream())){

            String data = request.readUTF();
            try{
                commandExecution(data, response);
            }
            catch (IOException e){
                errorLog(e);
            }
        }
        catch (Exception e){
            System.err.println("Client disconnected");
            e.printStackTrace(System.err);
        }
    }

    /**
     * This method interprets command name from data, create Command by commandName,
     * interprets parameters with their arguments from data and sends their to execute() method of command
     * @param data is request of client
     * @param response for client
     * @throws IOException
     */
    private void commandExecution(String data, ObjectOutput response) throws IOException{
        try{
            String commandName = interpreter.getCommandName(data);
            Command command = resolver.resolveCommand(commandName);
            command.execute(interpreter.interpretArguments(data), response);
            System.out.println("Command "+ commandName + " complete");
            System.out.println("Client disconnected");
        }
        catch (Exception e){
            writeCauseOfError(e, response);
        }
    }

    /**
     * This method prints stack trace of IOException
     * @param e is IOException
     */
    private void errorLog(IOException e){
        System.err.println("Error when responding to client");
        e.printStackTrace(System.err);
    }

    /**
     * This method write cause of error to client's response when command is executing
     * @param e is Exception
     * @param response for client
     * @throws IOException
     */
    private void writeCauseOfError(Exception e, ObjectOutput response) throws IOException{
        response.writeUTF("FAIL");
        response.writeUTF(e.getMessage());
        response.flush();
        System.err.println("The command failed");
        e.printStackTrace(System.err);
    }
}
