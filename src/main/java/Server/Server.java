package Server;

import Server.Controller.Configuration;
import Server.Model.CustomerDB;

import java.net.ServerSocket;
import java.util.Scanner;

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
     * This method create ServerSocket,
     * create new ClientHandler and sends it to new Thread,
     * waiting for 'exit' command to close the server
     */
    public void start(){
        try(ServerSocket serverSocket = new ServerSocket(SERVER_PORT, MAX_CLIENT)){
            System.out.println("Server is running");
            /**
             * @see ClientHandler
             */
            ClientHandler clientHandler = new ClientHandler(serverSocket, config, db);
            Thread newThread = new Thread(clientHandler);
            newThread.start();

            System.out.println("Write 'exit' to close the server");
            Scanner in = config.createScanner();
            while (!serverSocket.isClosed()){
                String data = in.nextLine();
                if(data.equals("exit")){
                    /**
                     * @see ClientHandler#closeClients() 
                     */
                    clientHandler.closeClients();
                    serverSocket.close();
                }
                else{
                    System.out.println("Wrong command");
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
