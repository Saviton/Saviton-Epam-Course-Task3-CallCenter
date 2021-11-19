import java.util.ArrayList;
import java.util.List;

/**
 * 4.	CallCenter. В организации работает несколько операторов. Оператор может
 * обслуживать одновременно только одного клиента. При отсутствии свободных операторов CallCenter
 * ставит звонки в очередь. Клиент, стоящий в очереди, может положить трубку и перезвонить еще раз через некоторое время.
 */
public class Starter {
    public static void main(String[] args) {
        List<Operator> operators = getOperators();
        CallCenter callCenter = new CallCenter(operators);
        List<Client> clients = getClients(callCenter);

        clients.forEach(Thread::start);

    }

    public static List<Operator> getOperators() {
        List<Operator> operators = new ArrayList<>();
        operators.add(new Operator(1));
        operators.add(new Operator(2));
        return operators;
    }

    public static List<Client> getClients(CallCenter callCenter) {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(1, callCenter));
        clients.add(new Client(2, callCenter));
        clients.add(new Client(3, callCenter));
        clients.add(new Client(4, callCenter));
        clients.add(new Client(5, callCenter));
        clients.add(new Client(6, callCenter));
        clients.add(new Client(7, callCenter));

        return clients;
    }
}
