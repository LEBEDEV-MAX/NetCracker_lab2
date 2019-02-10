package Server;

import Server.Controller.Interpreter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class InterpreterTest {
    private String data;
    private Interpreter interpreter;
    private Map<String, String> map;

    @Before
    public void beforeTest(){
        data = "CreateCustomer id=1; name=Ivan; phone=+12345678901; address=Russia;";
        map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "Ivan");
        map.put("phone", "+12345678901");
        map.put("address", "Russia");
        interpreter = new Interpreter();
    }

    @Test
    public void testGetCommandName(){
        Assert.assertEquals("CreateCustomer", interpreter.getCommandName(data));
    }

    @Test
    public void InterpretArguments(){
        Assert.assertEquals(map, interpreter.interpretArguments(data));
    }
}
