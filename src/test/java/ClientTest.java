import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ClientTest {
    private List<Operator> operators;
    private CallCenter callCenter;
    private List<Client> clients;

    @BeforeEach
    public void init() {
        operators = new ArrayList<>();
        operators.add(new Operator(1));
        callCenter = new CallCenter(operators);
        clients = new ArrayList<>();
        clients.add(new Client(1, callCenter));
        clients.add(new Client(2, callCenter));

        clients.forEach(Thread::start);
    }

    @Test
    //Занятость портов
    public void operator() throws InterruptedException {

        assertFalse(callCenter.getOperator().isFree());
    }

    @Test
    public void threadDied() throws InterruptedException {
        Thread.sleep(5000);
        for (Client client : clients) {
            assertEquals(client.getState(), Thread.State.TERMINATED);
        }
    }

}