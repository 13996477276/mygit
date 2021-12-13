package cn.chonggong;

/**
 * @author wenguanghua
 * @since 2021-06-11 07:56
 */
//多个线程操作统一资源，线程不安全
public class TicketsDemo implements Runnable {
    private  int ticketNum = 10;
    @Override
    public  void run() {
        while(true){
            if(ticketNum<=0){
                break;
            };
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"拿到了第"+ticketNum--+"票");
        }
    }
    public static void main(String[] args){
        TicketsDemo ticketsDemo = new TicketsDemo();
        Thread xiaoming = new Thread(ticketsDemo,"小明");
        Thread teacher = new Thread(ticketsDemo,"老师");
        Thread yellowBull = new Thread(ticketsDemo,"黄牛");
        xiaoming.start();
        teacher.start();
        yellowBull.start();
    };
}
