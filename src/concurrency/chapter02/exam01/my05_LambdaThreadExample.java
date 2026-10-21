package concurrency.chapter02.exam01;

public class my05_LambdaThreadExample {

	public static void main(String[] args) {
		/**
		 * 5. Runnable을 람다로 사용
		 *   - 코드가 간결해짐.
		 */
		Thread thread = new Thread(() -> 
			System.out.println(Thread.currentThread().getName() + " : 스레드 실행중")
		);
		thread.start();
	}

}
