package Server.Actions;

import CommonPacage.Controller.Exceptions.ExistCustomerException;
import CommonPacage.Controller.Exceptions.WrongArgumentException;
import CommonPacage.Controller.Exceptions.WrongParameterException;
import CommonPacage.Model.Customer;
import Server.Controller.Command.CreateCustomer;
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

public class CreateCustomerTest {
    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();
    private CustomerDB db;
    @Mock
    private ObjectOutput response;
    private Map<String, String> map;
    @InjectMocks
    private CreateCustomer cc;

    @Before
    public void beforeTest(){
        db = new CustomerDB();
        cc = new CreateCustomer(db);
        map = new HashMap<>();
    }

    @Test
    public void testExecute() throws Exception{
        map.put("id","1");
        map.put("name","Igor");
        map.put("phone","+09876543210");
        map.put("address","USA");

        cc.execute(map, response);

        Assert.assertEquals(1, db.getCustomers().size());
        Customer customer = db.getCustomer(1);
        Assert.assertEquals(customer.getId(), 1);
        Assert.assertEquals(customer.getName(), "Igor");
        Assert.assertEquals(customer.getPhone(),"+09876543210" );
        Assert.assertEquals(customer.getAddress(), "USA");

        InOrder inOrder = inOrder(response);
        inOrder.verify(response).writeUTF("SUCCESS");
        inOrder.verify(response).writeUTF("CreateCustomer");
        inOrder.verify(response).flush();
    }

    @Test(expected = ExistCustomerException.class)
    public void testExistCustomerException() throws Exception{
        map.put("id","1");
        map.put("name","Igor");
        map.put("phone","+09876543210");
        map.put("address","USA");

        cc.execute(map, response);
        cc.execute(map, response);
    }

    @Test(expected = WrongParameterException.class)
    public void testWrongParameterException() throws Exception{
        map.put("a", "1");
        cc.execute(map,response);
    }

    @Test(expected = WrongArgumentException.class)
    public void testWrongArgumentException() throws Exception{
        map.put("id", "a");
        cc.execute(map,response);
    }
}
