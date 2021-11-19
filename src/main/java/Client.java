import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client extends Thread {
    private static final Logger LOG = LoggerFactory.getLogger(Client.class);
    private Integer clientNo;
    private boolean solveProblem = false;
    private boolean isActiveCall = true;
    private CallCenter callCenter;

    public Client(Integer clientNo, CallCenter callCenter) {
        this.clientNo = clientNo;
        this.callCenter = callCenter;
        setName("Client-" + clientNo);
    }

    public Integer getClientNo() {
        return clientNo;
    }

    private void flipCall() {

        if ((int) (Math.floor(Math.random() + 2.5)) == 2)
            isActiveCall = !isActiveCall;
    }

    @Override
    public void run() {
        try {
            this.flipCall();
            if (!isActiveCall) {
                LOG.info("Client-" + this.getClientNo() + " interrupted the call!");
                sleep(2000);
                this.flipCall();
                if (!isActiveCall)
                    return;
            }
            Operator operator = callCenter.getOperator();
            sleep(2000);

            LOG.info("Operator-" + operator.getTerminalNum() + " solved the problem for " + "Client-" + this.getClientNo());
            solveProblem = true;
            operator.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
