package concurrency.chapter02.exam02;

public class my04_MultiThreadTerminated {

	/**
	 * 멀티스레드 환경에서는 모든 스레드가 종료 되어야 어플리케이션이 종료된다
	 */
	public static void main(String[] args) {

		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(new MyRunnable(i));
			thread.start();
		}
		
		System.out.println("메인 스레드 종료");
	}
	
	static class MyRunnable implements Runnable{

		private int threadId; 
		
		public MyRunnable() {
			// TODO Auto-generated constructor stub
		}
		
		public MyRunnable(int threadId) {
			this.threadId = threadId;
		}
		
		
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": 스레드 실행 중...");
            firstMethod(threadId);
        }
        
        private void firstMethod(int threadId) {
        	int localValue = threadId + 100;
        	secondMethod(localValue);
        }
        
        private void secondMethod(int localValue) {
        	String str = threadId + ": hello world";
        	System.out.println(Thread.currentThread().getName() + " : 스레드 Id : "+ threadId + ", value : " +localValue);
        }
    }
	
}
