import Server.Controller.TerminalController;
import Server.Model.CustomerDB;
import Server.Server;
import org.junit.Before;
import org.junit.Rule;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class Test {
    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    private CustomerDB db;
    @Mock
    private TerminalController tc;
    @InjectMocks
    private Server server;


    @Before
    public void beforeTest(){
        
    }
}
