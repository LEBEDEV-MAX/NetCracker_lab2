package Client;

import Client.Controller.Configuration;
import Client.Controller.TerminalController;
import CommonPacage.View.PrintMainMenu;

import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client class
 */
public class Client {
    private final int SERVER_PORT;
    private final String HOST;
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
    }

    /**
     * This method create Socket
     * When client got response from server, sends data to Terminal
     */
    public void start(){
        try(Socket socket = new Socket(HOST, SERVER_PORT);
            Scanner in = config.createScanner();
            /**
             * @see Configuration#createOOS(Socket)
             */
            ObjectOutput request = config.createOOS(socket);
            /**
             * @see Configuration#createOIS(Socket)
             */
            ObjectInput response = config.createOIS(socket)){

            menu.printMenu();
            while(!socket.isOutputShutdown()){
                String data = in.nextLine();

                if(data.equals("exit")) {
                    request.writeUTF(data);
                    request.flush();
                    Thread.sleep(1000);
                    socket.shutdownInput();
                    socket.shutdownOutput();
                }
                else if(data.equals("help")){
                    menu.printCustomerCommandList();
                }
                else{
                    request.writeUTF(data);
                    request.flush();
                    tc.start(response);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
