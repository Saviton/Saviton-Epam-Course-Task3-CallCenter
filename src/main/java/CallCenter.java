import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CallCenter {
    private List<Operator> Operator;
    private Semaphore semaphore;
    private ReentrantReadWriteLock passLock = new ReentrantReadWriteLock();

    public CallCenter(List<Operator> Operator) {
        this.Operator = Operator;
        semaphore = new Semaphore(Operator.size());
        Operator.forEach(t -> t.setSemaphore(semaphore));
    }

    public CallCenter() {
    }

    public Operator getOperator() throws InterruptedException {
        semaphore.acquire();

        passLock.writeLock().lock();
        Operator freeOperator = Operator.stream().filter(t -> t.isFree()).findFirst().get();
        freeOperator.busy();
        passLock.writeLock().unlock();

        return freeOperator;
    }
}
