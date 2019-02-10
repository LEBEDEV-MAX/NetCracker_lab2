package Server.Actions;

import CommonPacage.Model.Customer;
import Server.Controller.Command.GetAllCustomers;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

public class GetAllCustomersTest {
    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    private CustomerDB db;
    private Customer customer;
    @Mock
    private ObjectOutput response;
    private Map<String, String> map;
    @InjectMocks
    private GetAllCustomers gac;

    @Before
    public void beforeTest(){
        gac = new GetAllCustomers(db);
        customer = new Customer(1, "Igor", "+09876543210", "USA");
        when(db.getCustomers()).thenReturn(Collections.singletonList(customer));
        map = new HashMap<>();
    }

    @Test
    public void testExecute() throws Exception{
        gac.execute(map, response);

        InOrder inOrder = inOrder(response);
        inOrder.verify(response).writeUTF("SUCCESS");
        inOrder.verify(response).writeUTF("GetAllCustomers");
        inOrder.verify(response).writeInt(1);
        inOrder.verify(response).writeObject(customer);
        inOrder.verify(response).flush();
    }
}
