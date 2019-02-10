package Server.Controller;

import Server.Controller.Command.*;
import Server.Model.CustomerDB;
import org.jetbrains.annotations.NotNull;

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
    public TerminalController createTerminal(Socket client, CustomerDB db){
        return new TerminalController(createInterpreter(), commandResolver(db), client);
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
    private CommandResolver commandResolver(CustomerDB db){
        Map<String, Command> map = new HashMap<>();

        map.put("CreateCustomer", new CreateCustomer(db));
        map.put("GetAllCustomers", new GetAllCustomers(db));
        map.put("DeleteAllCustomers", new DeleteAllCustomers(db));
        map.put("UpdateCustomer", new UpdateCustomer(db));
        map.put("DeleteCustomer", new DeleteCustomer(db));
        map.put("FindCustomer", new FindCustomer(db));
        map.put("GetCustomer", new GetCustomer(db));

        return new CommandResolver(map);
    }

    /**
     * Create CustomerDB
     * @return CustomerDB
     * @see CustomerDB
     */
    @NotNull
    public CustomerDB createDB(){
        return new CustomerDB();
    }

    /**
     * Create Scanner
     * @return Scanner
     */
    public Scanner createScanner(){
        return new Scanner(System.in);
    }
}
