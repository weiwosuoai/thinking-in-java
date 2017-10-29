package com.allen.concurrent;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 循环屏障
 *
 * 模拟场景：3个朋友约好到某餐厅吃饭，大家都到了，才能开吃
 * Created by Allen on 2017/10/28.
 */
public class CyclicBarrierStudy {

	public static void main(String[] args) {
		final CyclicBarrier cb = new CyclicBarrier(3);

		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < 3; i++) {
			final int user = i + 1;
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep((long) (Math.random()*10000));
						System.out.println(user + " 用户到达餐厅，准备吃饭");
						System.out.println("已有" + cb.getNumberWaiting() + "在等待");

						// 阻塞
						cb.await();

						System.out.println("人员到齐，开始吃饭");
						Thread.sleep((long) (Math.random()*10000));
						System.out.println(user + " 吃完了，准备回家");

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};

			executorService.execute(runnable);
		}
			executorService.shutdown();
	}

}
