package Client.Controller;

import Client.Controller.Command.*;
import CommonPacage.View.PrintException;
import CommonPacage.View.PrintMainMenu;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class create other classes of the program
 */
public class Configuration {

    /**
     * Create Resolver and Interpreter and sends them to TerminalController
     * @return TerminalController
     * @see TerminalController
     */
    public TerminalController createTC(){
        return new TerminalController(createInterpreter(), createResolver(), createPrintE());
    }

    /**
     * Create Interpreter
     * @return Interpeter
     * @see Interpreter
     */
    @NotNull
    private Interpreter createInterpreter(){
        return new Interpreter();
    }

    /**
     * Create Resolver
     * @return CommandResolver
     * @see CommandResolver
     */
    @NotNull
    private CommandResolver createResolver(){
        Map<String, Command> map = new HashMap<>();

        map.put("CreateCustomer", new CreateCustomer());
        map.put("GetAllCustomers", new GetAllCustomers());
        map.put("UpdateCustomer", new UpdateCustomer());
        map.put("DeleteCustomer", new DeleteCustomer());
        map.put("GetCustomer", new GetCustomer());
        map.put("FindCustomer", new FindCustomer());
        map.put("DeleteAllCustomers", new DeleteAllCustomers());

        return new CommandResolver(map);
    }

    /**
     * Create Scanner
     * @return Scanner
     */
    public Scanner createScanner(){
        return new Scanner(System.in);
    }

    /**
     * Create ObjectInputStream
     * @param socket of client
     * @return ObjectInput
     * @throws IOException
     */
    public ObjectInput createOIS(Socket socket) throws IOException{
        return new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Create ObjectoutputStream
     * @param socket of client
     * @return ObjectIOutput
     * @throws IOException
     */
    public ObjectOutput createOOS(Socket socket) throws IOException{
        return new ObjectOutputStream(socket.getOutputStream());
    }

    /**
     * Create new PrintMainMenu
     * @see PrintMainMenu
     * @return PrintMainMenu
     */
    public PrintMainMenu createMainMenu(){
        return new PrintMainMenu();
    }

    /**
     * Create new PrintException
     * @see PrintException
     * @return PrintMainMenu
     */
    @NotNull
    private PrintException createPrintE(){
        return new PrintException();
    }

}
