package Server.Controller;

import java.util.HashMap;
import java.util.Map;

public class Interpreter {

    /**
     * This method returns command name
     * @param data string of command name with his arguments
     * @return command name
     */
    public String getCommandName(String data){
        return data.split(" ")[0];
    }

    /**
     * @see Interpreter
     * @param data string of command name with his arguments
     * @return map (parameter -> argument)
     */
    public Map<String, String> interpretArguments(String data){
        Map<String, String> map = new HashMap<String,String>();
        data = replace(data);

        if(data.contains("=")) {
            String[] arguments = data.split(";");
            for (String arg : arguments) {
                if(arg.matches("[^=]*=[^=]*")){
                    String[] a = arg.split("=");
                    map.put(a[0].trim(), a[1].trim());
                }
            }
        }
        return map;
    }

    /**
     * This method cut out command name from data (for easy search of arguments)
     * @param data string of command name with his arguments
     * @return data without command name
     */
    private String replace(String data){
        String commandName = getCommandName(data);
        return data.replaceAll(commandName, "");
    }
}
