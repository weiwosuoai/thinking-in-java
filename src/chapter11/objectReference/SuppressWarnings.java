package chapter11.objectReference;

import java.util.ArrayList;

/**
 * Created by Allen on 2018/3/23.
 */
public class SuppressWarnings {

	public static long COUNTER;

	static class Apple {
//		private static final long counter;
		private final long id = COUNTER++;

		public long getId() {
			return id;
		}
	}

	static class Orange {
//		private static final long counter;

	}

//	@java.lang.SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ArrayList apples = new ArrayList();
		for (int i = 0; i < 3; i++) {
			apples.add(new Apple());
		}

		apples.add(new Orange());

		for (int i = 0; i < apples.size(); i++) {
			((Apple) apples.get(i)).getId();
		}
	}
}
