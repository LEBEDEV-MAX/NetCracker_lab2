package Server;

import Server.Controller.Configuration;
import Server.Controller.TerminalController;
import Server.Model.CustomerDB;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is handler for client
 */
public class ClientHandler implements Runnable {
    private ServerSocket serverSocket;
    /**
     * @see Configuration
     */
    private Configuration config;
    /**
     * @see CustomerDB
     */
    private CustomerDB db;
    private List<Socket> clients;
    private boolean serverIsClosing;

    public ClientHandler(ServerSocket serverSocket, Configuration config, CustomerDB db){
        this.serverSocket = serverSocket;
        this.config = config;
        this.db = db;
        clients = new ArrayList<>();
        serverIsClosing = false;
    }

    /**
     * This method accept new client and call Terminal for handles client's request in new Thread
     */
    @Override
    public void run() {
        try{
            while (!serverIsClosing){
                System.out.println("Waiting for connection...");
                Socket client = serverSocket.accept();
                clients.add(client);
                System.out.println("Client connected...");
                /**
                 * @see TerminalController
                 */
                TerminalController tc = config.createTerminal(client, db);
                Thread newThread = new Thread(tc);
                newThread.start();
            }
        }
        catch (SocketException se){
            System.out.println("Server is closed");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method close all clients
     * @throws Exception
     */
    public void closeClients() throws Exception{
        serverIsClosing = true;
        for(Socket client : clients){
            client.close();
        }
    }
}
