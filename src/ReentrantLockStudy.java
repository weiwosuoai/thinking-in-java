import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁的用法
 * Created by Allen on 2017/10/24.
 */
public class ReentrantLockStudy {

	private static int TOTAL_MONEY = 100;

	public static void printMoney() {
		System.out.println("Current money is : " + TOTAL_MONEY);
	}

	// Init the ReentrankLock
	private static Lock lock = new ReentrantLock();

	/**
	 * Save the money
	 * @param money
	 */
	public static void put(int money) {
		// lock
		lock.lock();
		try {
			TOTAL_MONEY += money;
			System.out.println(System.currentTimeMillis() + " - put : " + money);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * Get the money
	 * @param money
	 */
	public static void get(int money) {
		lock.lock();
		try {
			TOTAL_MONEY -= money;
			System.out.println(System.currentTimeMillis() + " - get : " + money);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * Test
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					put(100);
				}
			}).start();
		}

		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					get(100);
				}
			}).start();
		}


		Thread.sleep(5000);
		printMoney();

	}





}
