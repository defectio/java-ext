package concurrency.chapter02.exam01;

public class my04_ImplementRunnableExample {

	public static void main(String[] args) {
		/**
		 * 4. Runnable를 익명 클래스로 사용
		 *   - Runnable 타입을 참조하거나 재활용하지 않고 일회용으로만 사용한다.
		 */
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " : 스레드 실행중");
			}
		});
		
		thread.start();
	}

} 