package concurrency.chapter02.exam02;

public class my01_ThreadStartRun {

	public static void main(String[] args) {

        MyRunnable myRunnable = new MyRunnable();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " :스레드 실행중..");
            }
        });
        
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " :스레드 실행중..");
            }
        });
        
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " :스레드 실행중..");
            }
        });

        // start() 메소드가 실행되어야 thread가 생성된다
        thread1.start();
        thread2.start();
        thread3.start();
//        thread.run();
//        myRunnable.run();

        System.out.println(Thread.currentThread().getName() + " : 메인 스레드 종료");
    }

    static class MyRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": 스레드 실행 중...");
        }
    }
}
