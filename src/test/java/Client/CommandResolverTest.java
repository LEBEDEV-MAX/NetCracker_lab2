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
    private CommandResolver cr;

    @Before
    public void beforeTest(){
        map = new HashMap<>();

        map.put("CreateCustomer", new CreateCustomer());
        map.put("GetAllCustomers", new GetAllCustomers());
        map.put("UpdateCustomer", new UpdateCustomer());
        map.put("DeleteCustomerTest", new DeleteCustomer());
        map.put("GetCustomer", new GetCustomer());
        map.put("FindCustomer", new FindCustomer());
        map.put("DeleteAllCustomers", new DeleteAllCustomers());
        cr = new CommandResolver(map);
    }

    @Test
    public void testCreateCustomer() throws Exception{
        String str = "CreateCustomer";
        Assert.assertTrue(cr.resolveCommand(str) instanceof CreateCustomer);
    }

    @Test
    public void testGetAllCustomers() throws Exception{
        String str = "GetAllCustomers";
        Assert.assertTrue(cr.resolveCommand(str) instanceof GetAllCustomers);
    }

    @Test
    public void testUpdateCustomer() throws Exception{
        String str = "UpdateCustomer";
        Assert.assertTrue(cr.resolveCommand(str) instanceof UpdateCustomer);
    }

    @Test
    public void testDeleteCustomer() throws Exception{
        String str = "DeleteCustomerTest";
        Assert.assertTrue(cr.resolveCommand(str) instanceof DeleteCustomer);
    }

    @Test
    public void testDeleteAllCustomers() throws Exception{
        String str = "DeleteAllCustomers";
        Assert.assertTrue(cr.resolveCommand(str) instanceof DeleteAllCustomers);
    }

    @Test
    public void testFindCustomer() throws Exception{
        String str = "FindCustomer";
        Assert.assertTrue(cr.resolveCommand(str) instanceof FindCustomer);
    }

    @Test
    public void testGetCustomer() throws Exception{
        String str = "GetCustomer";
        Assert.assertTrue(cr.resolveCommand(str) instanceof GetCustomer);
    }

    @Test(expected = WrongCommandException.class)
    public void testWrongCommandException() throws Exception{
        String str = "a";
        cr.resolveCommand(str);
    }
}
