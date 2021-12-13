package cn.chonggong;

/**
 * @author wenguanghua
 * @since 2021-06-11 09:43
 */
public class DaemonTest {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();
        Thread godThread = new Thread(god);
        godThread.setDaemon(true);//设置为守护线程
        godThread.start();
        new Thread(you).start();//启动你的人生
    }
}
class God implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println("主与你同在");
        }
    }
}
class You implements Runnable{
    @Override
    public void run() {
        for(int i=0; i<30000; i++){
            if(i<=10000){
                System.out.println("你在学习");
            }else if(i<=20000){
                System.out.println("你在工作");
            }else if(i<30000){
                System.out.println("你已退休");
            }
        }
    }
}
class WorkerThread extends Thread {
    public WorkerThread() {
        // When false, (i.e. when it's a user thread), the Worker thread continues to run.
        // When true, (i.e. when it's a daemon thread), the Worker thread terminates when the main thread terminates.
        setDaemon(false);
    }
    public void run() {
        int count = 0;
        while (true) {
            System.out.println("Hello from Worker " + count++);
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                // handle exception here
            }
        }
    }
}
