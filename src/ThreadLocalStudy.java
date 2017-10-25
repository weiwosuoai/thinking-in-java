import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ThreadLocal 来管理变量，来保证同步性
 * Created by Allen on 2017/10/24.
 */
public class ThreadLocalStudy {

	private static ThreadLocal<Integer> totalMoney = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 101;
		}
	};

	public static void printMoney() {
		System.out.println("Current money is : " + totalMoney.get());
	}


	/**
	 * Save the money
	 *
	 * @param money
	 */
	public static void put(int money) {
		totalMoney.set(totalMoney.get() + money);
		System.out.println(System.currentTimeMillis() + " - put : " + money);
	}

	/**
	 * Get the money
	 *
	 * @param money
	 */
	public static void get(int money) {
		totalMoney.set(totalMoney.get() - money);
		System.out.println(System.currentTimeMillis() + " - get : " + money);
	}

	/**
	 * Test
	 *
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

		Thread.sleep(3000);
		printMoney();

	}


}
