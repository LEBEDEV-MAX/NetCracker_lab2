package Server.Actions;

import CommonPacage.Controller.Exceptions.WrongArgumentException;
import CommonPacage.Controller.Exceptions.WrongParameterException;
import CommonPacage.Model.Customer;
import Server.Controller.Command.FindCustomer;
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

public class FindCustomerTest {
    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();
    private CustomerDB db;
    private Customer customer;
    @Mock
    private ObjectOutput response;
    private Map<String, String> map;
    @InjectMocks
    private FindCustomer fc;

    @Before
    public void beforeTest() throws Exception{
        db = new CustomerDB();
        customer = new Customer(1, "Igor", "+09876543210", "USA");
        db.addCustomer(customer);
        fc = new FindCustomer(db);
        map = new HashMap<>();
    }

    @Test
    public void testExecute_ByEquals() throws Exception{
        map.put("name","Igor");
        map.put("operation","equals");

        fc.execute(map,response);

        InOrder inOrder = inOrder(response);
        inOrder.verify(response).writeUTF("SUCCESS");
        inOrder.verify(response).writeUTF("FindCustomer");
        inOrder.verify(response).writeInt(1);
        inOrder.verify(response).writeObject(customer);
        inOrder.verify(response).flush();
    }

    @Test
    public void testExecute_ByContains() throws Exception{
        map.put("name","or");
        map.put("operation","contains");

        fc.execute(map,response);

        InOrder inOrder = inOrder(response);
        inOrder.verify(response).writeUTF("SUCCESS");
        inOrder.verify(response).writeUTF("FindCustomer");
        inOrder.verify(response).writeInt(1);
        inOrder.verify(response).writeObject(customer);
        inOrder.verify(response).flush();
    }

    @Test
    public void testExecute_ByStartWith() throws Exception{
        map.put("name","Ig");
        map.put("operation","startWith");

        fc.execute(map,response);

        InOrder inOrder = inOrder(response);
        inOrder.verify(response).writeUTF("SUCCESS");
        inOrder.verify(response).writeUTF("FindCustomer");
        inOrder.verify(response).writeInt(1);
        inOrder.verify(response).writeObject(customer);
        inOrder.verify(response).flush();
    }

    @Test
    public void testExecute_ByLike() throws Exception{
        map.put("name","?g*");
        map.put("operation","like");

        fc.execute(map,response);

        InOrder inOrder = inOrder(response);
        inOrder.verify(response).writeUTF("SUCCESS");
        inOrder.verify(response).writeUTF("FindCustomer");
        inOrder.verify(response).writeInt(1);
        inOrder.verify(response).writeObject(customer);
        inOrder.verify(response).flush();
    }

    @Test(expected = WrongParameterException.class)
    public void testWrongParameterException_WrongName() throws Exception{
        map.put("a","Igor");
        fc.execute(map,response);
    }

    @Test(expected = WrongParameterException.class)
    public void testWrongParameterException_WrongOperation() throws Exception{
        map.put("name","Igor");
        map.put("a","like");
        fc.execute(map,response);
    }

    @Test(expected = WrongArgumentException.class)
    public void testWrongArgumentException_WrongOperation() throws Exception{
        map.put("name","Igor");
        map.put("operation","a");
        fc.execute(map,response);
    }
}
