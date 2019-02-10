package Server.Actions;

import CommonPacage.Controller.Exceptions.CustomerNotFoundException;
import CommonPacage.Controller.Exceptions.WrongArgumentException;
import CommonPacage.Controller.Exceptions.WrongParameterException;
import CommonPacage.Model.Customer;
import Server.Controller.Command.GetCustomer;
import Server.Model.CustomerDB;
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
import static org.mockito.Mockito.when;

public class GetCustomerTest {
    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    private CustomerDB db;
    private Customer customer;
    @Mock
    private ObjectOutput response;
    private Map<String, String> map;
    @InjectMocks
    private GetCustomer gc;

    @Before
    public void beforeTest(){
        customer = new Customer(1, "Igor", "+09876543210", "USA");
        gc = new GetCustomer(db);
        map = new HashMap<>();
    }

    @Test
    public void testExecute() throws Exception{
        map.put("id","1");
        when(db.getCustomer(1)).thenReturn(customer);

        gc.execute(map,response);

        InOrder inOrder = inOrder(response);
        inOrder.verify(response).writeUTF("SUCCESS");
        inOrder.verify(response).writeUTF("GetCustomer");
        inOrder.verify(response).writeObject(customer);
        inOrder.verify(response).flush();
    }

    @Test(expected = CustomerNotFoundException.class)
    public void testCustomerNotFoundException() throws Exception{
        map.put("id","2");
        gc.execute(map, response);
    }

    @Test(expected = WrongArgumentException.class)
    public void testWrongArgumentException() throws Exception{
        map.put("id", "a");
        gc.execute(map,response);
    }

    @Test(expected = WrongParameterException.class)
    public void testWrongParameterException() throws Exception{
        map.put("a","1");
        gc.execute(map,response);
    }
}
