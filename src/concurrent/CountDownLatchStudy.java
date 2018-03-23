package concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 倒数计数器
 *
 * 模拟场景：有一个任务，需要等待其他几个任务执行完毕之后才能执行
 * Created by Allen on 2017/10/28.
 */
public class CountDownLatchStudy {

	public static void main(String[] args)  {
		// 需要等待两个任务执行完毕完成
		final CountDownLatch cdl = new CountDownLatch(2);

		// 任务1
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("任务1准备执行。。。");
					Thread.sleep((long) (Math.random()*10000));
					System.out.println("任务1执行完毕。。。");
					cdl.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}).start();

		// 任务2
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("任务2准备执行。。。");
					Thread.sleep((long) (Math.random()*10000));
					System.out.println("任务2执行完毕。。。");
					cdl.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}).start();

		System.out.println("主线程等待其他线程执行完毕");
		try {
			// 阻塞，等待其他线程执行完毕
			cdl.await();

			System.out.println("其他任务执行完毕，主线程开始执行任务 - " + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}
}
