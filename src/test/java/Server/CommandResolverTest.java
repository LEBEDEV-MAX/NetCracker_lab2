package Server;

import CommonPacage.Controller.Exceptions.WrongCommandException;
import Server.Controller.Command.*;
import Server.Controller.CommandResolver;
import Server.Model.CustomerDB;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CommandResolverTest {
    private Map<String, Command> map;
    private CustomerDB db;
    private CommandResolver cr;

    @Before
    public void beforeTest(){
        map = new HashMap<>();
        db = new CustomerDB();

        map.put("CreateCustomer", new CreateCustomer(db));
        map.put("GetAllCustomers", new GetAllCustomers(db));
        map.put("DeleteAllCustomers", new DeleteAllCustomers(db));
        map.put("UpdateCustomer", new UpdateCustomer(db));
        map.put("DeleteCustomerTest", new DeleteCustomer(db));
        map.put("FindCustomer", new FindCustomer(db));
        map.put("GetCustomer", new GetCustomer(db));
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
    public void testGetCustomer() throws Exception{
        String str = "GetCustomer";
        Assert.assertTrue(cr.resolveCommand(str) instanceof GetCustomer);
    }

    @Test
    public void testDeleteAllCustomers() throws Exception{
        String str = "DeleteAllCustomers";
        Assert.assertTrue(cr.resolveCommand(str) instanceof DeleteAllCustomers);
    }

    @Test
    public void testDeleteCustomer() throws Exception{
        String str = "DeleteCustomerTest";
        Assert.assertTrue(cr.resolveCommand(str) instanceof DeleteCustomer);
    }

    @Test
    public void testUpdateCustomer() throws Exception{
        String str = "UpdateCustomer";
        Assert.assertTrue(cr.resolveCommand(str) instanceof UpdateCustomer);
    }

    @Test
    public void testFindCustomer() throws Exception{
        String str = "FindCustomer";
        Assert.assertTrue(cr.resolveCommand(str) instanceof FindCustomer);
    }

    @Test(expected = WrongCommandException.class)
    public void testWrongCommandException() throws Exception{
        String str = "a";
        cr.resolveCommand(str);
    }
}
