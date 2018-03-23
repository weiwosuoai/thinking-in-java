package concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程间数据交互
 *
 * 模拟场景：一场绑架案，匪徒搬家了 a 女子，并告知 a 女子家人带上 1000w 到某地点，一手交钱一手交货
 * Created by Allen on 2017/10/28.
 */
public class ExchangerStudy {

	public static void main(String[] args) {
		// 交换器，交换String类型数据
		final Exchanger<String> ec = new Exchanger<String>();

		ExecutorService es = Executors.newCachedThreadPool();
		// 匪徒团伙
		es.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String str = ec.exchange("a 女子");
					System.out.println("绑架者用 a 女子交换回：" + str);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		// 家属
		es.execute(new Runnable() {
			@Override
			public void run() {
				try {

					Thread.sleep((long) (Math.random()*10000));
					Thread.sleep((long) (Math.random()*10000));
					String str = ec.exchange("1000w");
					System.out.println("家属拿 1000w 交换回：" + str);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		es.shutdown();

	}

}
