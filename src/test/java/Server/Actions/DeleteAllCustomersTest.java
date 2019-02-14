package Server.Actions;

import CommonPacage.Model.Customer;
import Server.Controller.Command.DeleteAllCustomers;
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

public class DeleteAllCustomersTest {
    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();
    private CustomerDB db;
    private Customer customer;
    @Mock
    private ObjectOutput response;
    private Map<String, String> map;
    @InjectMocks
    private DeleteAllCustomers dac;

    @Before
    public void beforeTest() throws Exception{
        db = new CustomerDB();
        customer = new Customer(1, "Igor", "+09876543210", "USA");
        db.addCustomer(customer);
        dac = new DeleteAllCustomers(db);
        map = new HashMap<>();
    }

    @Test
    public void testExecute() throws Exception{
        dac.execute(map,response);

        Assert.assertEquals(db.getCustomers().size(),0);
        InOrder inOrder = inOrder(response);
        inOrder.verify(response).writeUTF("SUCCESS");
        inOrder.verify(response).writeUTF("DeleteAllCustomers");
        inOrder.verify(response).flush();
    }
}
