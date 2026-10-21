package concurrency.chapter02.exam01;

public class my03_AnonymousThreadClassExample {

	public static void main(String[] args) {
		/**
		 * 3. Thread를 익명 클래스로 사용한다.
		 *   - 스레드 객체를 참조하거나 재활용하지 않고, 일회용으로몬 사용할 경우
		 */
		Thread thread = new Thread() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " : 스레드 실행중");
			}
		};
		
		thread.start();
	}

}

