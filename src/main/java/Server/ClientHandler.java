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
    private List<Thread> terminalThreads;
    private boolean serverIsClosing;

    public ClientHandler(ServerSocket serverSocket, Configuration config, CustomerDB db){
        this.serverSocket = serverSocket;
        this.config = config;
        this.db = db;
        terminalThreads = new ArrayList<>();
        serverIsClosing = false;
    }

    /**
     * This method accept new client and call Terminal for handles client's request in new Thread
     */
    @Override
    public void run() {
        try{
            while (true){
                System.out.println("Waiting for connection...");
                Socket client = serverSocket.accept();

                if(!serverIsClosing){
                    System.out.println("Client connected...");
                    /**
                     * @see TerminalController
                     */
                    TerminalController tc = config.createTerminal(client, db);
                    Thread newThread = new Thread(tc);
                    newThread.start();
                    terminalThreads.add(newThread);
                }
                else{
                    client.close();
                }
            }
        }
        catch (SocketException se){
            System.err.println("Server is closed");
            //se.printStackTrace(System.err);
        }
        catch (Exception e){
            System.err.println("Error of connection");
            e.printStackTrace(System.err);
        }
    }

    /**
     * This method close new handler for clients
     * @throws InterruptedException
     */
    public void closeHandlers() throws InterruptedException{
        serverIsClosing = true;
        System.out.println("Waiting for handler completion...");
        for(Thread thread : terminalThreads){
            if(thread.isAlive()) thread.join();
        }
    }
}
