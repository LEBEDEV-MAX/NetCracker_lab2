package Client;

import Client.Controller.Command.*;
import Client.Controller.CommandResolver;
import CommonPacage.Controller.Exceptions.WrongCommandException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CommandResolverTest {
    private Map<String, Command> map;

    @Before
    public void beforeTest(){
        map = new HashMap<>();

        map.put("CreateCustomer", new CreateCustomer());
        map.put("GetAllCustomers", new GetAllCustomers());
        map.put("UpdateCustomer", new UpdateCustomer());
        map.put("DeleteCustomer", new DeleteCustomer());
        map.put("GetCustomer", new GetCustomer());
        map.put("FindCustomer", new FindCustomer());
        map.put("DeleteAllCustomers", new DeleteAllCustomers());
    }

    @Test
    public void testCreateCustomer() throws Exception{
        String str = "CreateCustomer";
        CommandResolver cr = new CommandResolver(map);

        Assert.assertTrue(cr.resolveCommand(str) instanceof CreateCustomer);
    }

    @Test
    public void testGetAllCustomers() throws Exception{
        String str = "GetAllCustomers";
        CommandResolver cr = new CommandResolver(map);

        Assert.assertTrue(cr.resolveCommand(str) instanceof GetAllCustomers);
    }

    @Test
    public void testUpdateCustomer() throws Exception{
        String str = "UpdateCustomer";
        CommandResolver cr = new CommandResolver(map);

        Assert.assertTrue(cr.resolveCommand(str) instanceof UpdateCustomer);
    }

    @Test
    public void testDeleteCustomer() throws Exception{
        String str = "DeleteCustomer";
        CommandResolver cr = new CommandResolver(map);

        Assert.assertTrue(cr.resolveCommand(str) instanceof DeleteCustomer);
    }

    @Test
    public void testDeleteAllCustomers() throws Exception{
        String str = "DeleteAllCustomers";
        CommandResolver cr = new CommandResolver(map);

        Assert.assertTrue(cr.resolveCommand(str) instanceof DeleteAllCustomers);
    }

    @Test
    public void testFindCustomer() throws Exception{
        String str = "FindCustomer";
        CommandResolver cr = new CommandResolver(map);

        Assert.assertTrue(cr.resolveCommand(str) instanceof FindCustomer);
    }

    @Test
    public void testGetCustomer() throws Exception{
        String str = "GetCustomer";
        CommandResolver cr = new CommandResolver(map);

        Assert.assertTrue(cr.resolveCommand(str) instanceof GetCustomer);
    }

    @Test(expected = WrongCommandException.class)
    public void testWrongCommandException() throws Exception{
        String str = "a";
        CommandResolver cr = new CommandResolver(map);
        cr.resolveCommand(str);
    }
}