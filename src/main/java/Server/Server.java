package Server;

import Server.Controller.Configuration;
import Server.Controller.TerminalController;
import Server.Model.CustomerDB;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server class
 */
public class Server {
    private final int SERVER_PORT;
    private final int MAX_CLIENT;
    /**
     * @see CustomerDB
     */
    private CustomerDB db;
    /**
     * @see Configuration
     */
    private Configuration config;

    public Server(int SERVER_PORT, int MAX_CLIENT){
        this.SERVER_PORT = SERVER_PORT;
        this.MAX_CLIENT = MAX_CLIENT;
        config = new Configuration();
        db = config.createDB();
    }

    /**
     * This method create ServerSocket
     * accept new client and call Terminal for handles client's request in new Thread
     */
    public void start(){
        try(ServerSocket serverSocket = new ServerSocket(SERVER_PORT, MAX_CLIENT)){
            System.out.println("Server is running");
            while (!serverSocket.isClosed()){
                System.out.println("Waiting for connection...");
                Socket client = serverSocket.accept();
                System.out.println("Client connected...");
                /**
                 * @see TerminalController
                 */
                TerminalController tc = config.createTerminal(client, db);
                Thread newThread = new Thread(tc);
                newThread.start();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
