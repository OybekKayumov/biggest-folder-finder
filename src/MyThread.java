public class MyThread extends Thread {

    private int threadNumber;

    public MyThread(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        for (;;) {
            System.out.println(threadNumber);
        }
    }
}
