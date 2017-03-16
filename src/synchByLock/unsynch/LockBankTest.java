package synchByLock.unsynch;

/**
 * This program shows data corruption when multiple threads access a data structure.
 * Created by ZouLe on 2017/3/16.
 */
public class LockBankTest {
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;

    public static void main(String[] args) {
        LockBank lockBank = new LockBank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; ++i) {
            int fromAccount = i;
            Runnable r = () -> {
                try {
                    while (true) {
                        int toAccount = (int) (lockBank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        lockBank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {
                    // PASS
                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }
}
