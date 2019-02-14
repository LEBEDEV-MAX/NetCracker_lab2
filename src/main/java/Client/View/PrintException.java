package Client.View;

public class PrintException {
    /**
     * Method prints message upon unsuccessful completion of the command.
     * @param error type of Exception
     */
    public void print(String error){
        System.err.println(error);
        System.out.println("Write 'help' to see command mnemonic");
    }
}
