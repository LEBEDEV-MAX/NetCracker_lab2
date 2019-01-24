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

    @Before
    public void beforeTest(){
        map = new HashMap<>();
        db = new CustomerDB();

        map.put("CreateCustomer", new CreateCustomer(db));
        map.put("GetAllCustomers", new GetAllCustomers(db));
        map.put("DeleteAllCustomers", new DeleteAllCustomers(db));
        map.put("UpdateCustomer", new UpdateCustomer(db));
        map.put("DeleteCustomer", new DeleteCustomer(db));
        map.put("FindCustomer", new FindCustomer(db));
        map.put("GetCustomer", new GetCustomer(db));
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
    public void testGetCustomer() throws Exception{
        String str = "GetCustomer";
        CommandResolver cr = new CommandResolver(map);

        Assert.assertTrue(cr.resolveCommand(str) instanceof GetCustomer);
    }

    @Test
    public void testDeleteAllCustomers() throws Exception{
        String str = "DeleteAllCustomers";
        CommandResolver cr = new CommandResolver(map);

        Assert.assertTrue(cr.resolveCommand(str) instanceof DeleteAllCustomers);
    }

    @Test
    public void testDeleteCustomer() throws Exception{
        String str = "DeleteCustomer";
        CommandResolver cr = new CommandResolver(map);

        Assert.assertTrue(cr.resolveCommand(str) instanceof DeleteCustomer);
    }

    @Test
    public void testUpdateCustomer() throws Exception{
        String str = "UpdateCustomer";
        CommandResolver cr = new CommandResolver(map);

        Assert.assertTrue(cr.resolveCommand(str) instanceof UpdateCustomer);
    }

    @Test
    public void testFindCustomer() throws Exception{
        String str = "FindCustomer";
        CommandResolver cr = new CommandResolver(map);

        Assert.assertTrue(cr.resolveCommand(str) instanceof FindCustomer);
    }

    @Test(expected = WrongCommandException.class)
    public void testWrongCommandException() throws Exception{
        String str = "a";
        CommandResolver cr = new CommandResolver(map);
        cr.resolveCommand(str);
    }
}
