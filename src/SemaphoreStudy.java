import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 信号量
 *
 * 模拟场景：20个人同时去购票站购票，只有两个购票窗口，同时只能让两个人进去购票
 * Created by Allen on 2017/10/28.
 */
public class SemaphoreStudy {

	/**
	 * 执行任务类，获取信号量和释放信号量
	 */
	class SemphoreRunnable implements Runnable {

		private Semaphore semaphore; // 信号量
		private int user; // 记录第几个用户

		public SemphoreRunnable(Semaphore semaphore, int user) {
			this.semaphore = semaphore;
			this.user = user;
		}

		@Override
		public void run() {
			try {
				// 获取信号量
				semaphore.acquire();
				System.out.println("第 " + user + "个用户进来准备购票");
				Thread.sleep((long) (Math.random()*10000));
				System.out.println("第 " + user + "个用户购票完成，准备离开");
				Thread.sleep((long) (Math.random()*10000));
				System.out.println("第 " + user + "个用户离开");

				// 释放信号量
				semaphore.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	private void excute() {
		final Semaphore semaphore = new Semaphore(2);
		// 线程池
		ExecutorService threadPool = Executors.newCachedThreadPool();
		// 模拟20个用户
		for (int i = 0; i < 20; i++) {
			// 去买票
			threadPool.execute(new SemphoreRunnable(semaphore, i + 1));
		}
		threadPool.shutdown();
	}

	public static void main(String[] args) {
		SemaphoreStudy semaphore = new SemaphoreStudy();
		semaphore.excute();
	}


}
