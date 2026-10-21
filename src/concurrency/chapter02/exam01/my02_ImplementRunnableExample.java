package concurrency.chapter02.exam01;

public class my02_ImplementRunnableExample {

	public static void main(String[] args) {
		MyRunnable task = new MyRunnable();
		Thread thread = new Thread(task);
		thread.start();
	}

}

/**
 * 2. Runnable를 구현하여 run() 메소드를 재정의한다.
 *   - Runnable을 태스크로 활용하는 방식. 선호하는 방식
 *   - 스레드와 실행하고자 하는 태스크를 분리함으로써, 좀 더 유연하고 확장 가능한 구조로 구현이 가능한다.
 */
class MyRunnable implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " : 스레드 실행중");
	}
}