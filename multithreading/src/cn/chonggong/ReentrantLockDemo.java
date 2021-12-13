package cn.chonggong;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wenguanghua
 * @since 2021-06-11 10:55
 */
public class ReentrantLockDemo {
    public static void main(String[] args){
        LockTest lockTest = new LockTest();
        new  Thread(lockTest,"学生").start();
        new  Thread(lockTest,"老师").start();
        new  Thread(lockTest,"黄牛").start();
    };
}
class LockTest implements  Runnable{
    int ticketNum = 10;
    private final ReentrantLock reentrantLock = new ReentrantLock();
    @Override
    public void run() {
        while(true){
            reentrantLock.lock();//加锁
            try{
                if(ticketNum>0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"拿到了第"+ticketNum--+"票");
                }else{
                    break;
                }
            }finally {
                    reentrantLock.unlock();//解锁
            }
        };
    }
}
