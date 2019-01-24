package Server.Controller;

import Server.Controller.Command.Command;

import java.io.*;
import java.net.Socket;

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
     * This method read string from client's request,
     * interprets command name from data, create Command by commandName,
     * interprets parameters with their arguments from data and sends their to execute() method of command
     * Finally if there are errors, writes reasons for client's response
     */
    @Override
    public void run() {
        try(ObjectOutput response = new ObjectOutputStream(client.getOutputStream());
            ObjectInput request = new ObjectInputStream(client.getInputStream())){

            while(!client.isClosed()){
                String data = request.readUTF();
                if(!data.equals("exit")){

                    try{
                        String commandName = interpreter.getCommandName(data);
                        Command command = resolver.resolveCommand(commandName);
                        command.execute(interpreter.interpretArguments(data), response);
                        System.out.println("Command "+ data + " complete");
                    }
                    catch (IOException e){
                        throw new IOException();
                    }
                    catch (Exception e){
                        response.writeUTF("FAIL");
                        response.writeUTF(e.getMessage());
                        response.flush();
                    }

                }
                else{
                    client.close();
                    System.out.println("Client disconnected");
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
