package com.allen.concurrent;

/**
 * 死锁
 *
 * 总结：当两个线程互相得到两个锁，锁1和锁2，然后线程1等待线程2释放锁2，线程2等待线程1释放锁1
 *      互不相让，两个线程陷入了无尽的等待中，这个时候就造成了死锁
 *
 * 如何避免死锁？
 *
 * <ul>
 *     <li>1.按顺序加锁</li>
 *     <li>2.获取锁时限</li>
 *     <li>3.死锁检测，根据线程间获取锁的关系检测线程间是否发生死锁，如果发生死锁就执行一定的策略，比如说中断线程，回滚操作等</li>
 * </ul>
 * Created by Allen on 2017/10/24.
 */
public class DeadLockStudy {

	private static Object LOCK1 = new Object();
	private static Object LOCK2 = new Object();

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (LOCK1) {
					System.out.println("thread1 get the LOCK1");

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (LOCK2) {
						System.out.println("thread2 get the LOCK2");
					}
				}
				System.out.println("thread1 end ...");

			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (LOCK2) {
					System.out.println("thread2 get the LOCK2");

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					synchronized (LOCK1) {
						System.out.println("thread2 get the LOCK1");
					}
				}
				System.out.println("thread2 end ...");
			}
		}).start();
	}




}
