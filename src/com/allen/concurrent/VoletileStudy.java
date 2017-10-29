package com.allen.concurrent;

/**
 * 关于 voletile 的学习
 * Created by Allen on 2017/10/24.
 */
public class VoletileStudy {

	public static double count = 0.0;

	// Note: volatile 无法修饰 final 类型的变量
	//public static volatile final Integer num;

	public static void inc() {
		count++;
	}

	public static void main(String[] args) {
		// 启动1000个线程，去计算i++,看下结果
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					inc();
				}
			}).start();
		}

		// In the end, we print the number of count.
		System.out.println("The result is : " + count);
	}

	/**
	 * 总结：如果一个 voletile 修饰了一个非原子性变量，如 int double float 等这些基本类型变量
	 *		多线程情况下，虽然 voletile 能够保证被修改的变量同步到主内存中，但是由于 cpu 时间片切换的
	 *		问题下，还是会导致变量不一致。
	 */
}
