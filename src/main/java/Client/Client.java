package Client;

import Client.Controller.Configuration;
import Client.Controller.TerminalController;
import Client.View.PrintMainMenu;

import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

/**
 * Client class
 */
public class Client {
    private final int SERVER_PORT;
    private final String HOST;
    private boolean closeProgram;
    /**
     * @see Configuration
     */
    private Configuration config;
    /**
     * @see TerminalController
     */
    private TerminalController tc;
    /**
     * @see PrintMainMenu
     */
    private PrintMainMenu menu;

    public Client(int SERVER_PORT, String HOST){
        this.SERVER_PORT = SERVER_PORT;
        this.HOST = HOST;
        config = new Configuration();
        tc = config.createTC();
        menu = config.createMainMenu();
        closeProgram = false;
    }

    /**
     * This method create Socket
     * When client got response from server, sends data to Terminal
     */
    public void start(){
        Scanner in = config.createScanner();
        menu.printMenu();
        while (!closeProgram){
            String data = in.nextLine();

            switch (data){
                case "exit":
                    closeProgram = true;
                    break;
                case "help":
                    menu.printCustomerCommandList();
                    break;
                default:
                    socketHandler(data);
            }
        }
    }

    /**
     * This method create socket and handles requests and responses
     * @param data is request
     */
    private void socketHandler(String data){
        try(Socket socket = new Socket(HOST, SERVER_PORT);
            /**
             * @see Configuration#createOOS(Socket)
             */
            ObjectOutput request = config.createOOS(socket);
            /**
             * @see Configuration#createOIS(Socket)
             */
            ObjectInput response = config.createOIS(socket)){

            request.writeUTF(data);
            request.flush();
            tc.start(response);
        }
        catch (SocketException se){
            System.err.println("Server is closed");
        }
        catch (Exception e){
            e.printStackTrace(System.err);
        }
    }
}

