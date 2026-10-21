package concurrency.chapter02.exam01;

public class my01_ExtendThreadExample {

	public static void main(String[] args) {
		Thread thread = new MyThread();
		thread.start();
	}
}

/**
 * 1. Thread를 상속받아 run() 메소드를 재정의한다.
 *   - 가장 기본적인 방식. 
 *   - 상속의 특성상 컴파일 시점에 실행 코드가 결졍되어, 동적인 기능 변경이 불가능하다는 단점이 있음
 */
class MyThread extends Thread {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " : 스레드 실행중");
	}
}
