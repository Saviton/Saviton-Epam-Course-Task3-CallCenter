import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Operator {
    private Integer operatorNo;
    private boolean free = true;
    private Semaphore semaphore;
    private List<Client> clients = new ArrayList<>();

    public Operator(Integer operatorNo) {
        this.operatorNo = operatorNo;
    }

    private ReentrantReadWriteLock passLock = new ReentrantReadWriteLock();

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public boolean isFree() {
        return free;
    }

    public void busy() {
        free = false;
    }

    public void release() {
        free = true;
        semaphore.release();
    }

    public Integer getTerminalNum() {
        return operatorNo;
    }

}
