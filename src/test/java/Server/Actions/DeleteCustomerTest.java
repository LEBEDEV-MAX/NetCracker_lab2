package Server.Actions;

import CommonPacage.Controller.Exceptions.CustomerNotFoundException;
import CommonPacage.Controller.Exceptions.WrongArgumentException;
import CommonPacage.Controller.Exceptions.WrongParameterException;
import CommonPacage.Model.Customer;
import Server.Controller.Command.DeleteCustomer;
import Server.Model.CustomerDB;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.ObjectOutput;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.inOrder;

public class DeleteCustomerTest {
    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();
    private CustomerDB db;
    private Customer customer;
    @Mock
    private ObjectOutput response;
    private Map<String, String> map;
    @InjectMocks
    private DeleteCustomer dc;

    @Before
    public void beforeTest() throws Exception{
        db = new CustomerDB();
        customer = new Customer(1, "Igor", "+09876543210", "USA");
        db.addCustomer(customer);
        dc = new DeleteCustomer(db);
        map = new HashMap<>();
    }

    @Test
    public void testExecute() throws Exception{
        map.put("id","1");

        dc.execute(map,response);

        Assert.assertEquals(db.getCustomers().size(),0);
        InOrder inOrder = inOrder(response);
        inOrder.verify(response).writeUTF("SUCCESS");
        inOrder.verify(response).writeUTF("DeleteCustomer");
        inOrder.verify(response).flush();
    }

    @Test(expected = CustomerNotFoundException.class)
    public void testCustomerNotFoundException() throws Exception{
        map.put("id","2");
        dc.execute(map,response);
    }

    @Test(expected = WrongParameterException.class)
    public void testWrongParameterException() throws Exception{
        map.put("a","1");
        dc.execute(map,response);
    }

    @Test(expected = WrongArgumentException.class)
    public void testWrongArgumentException() throws Exception{
        map.put("id","a");
        dc.execute(map,response);
    }
}
