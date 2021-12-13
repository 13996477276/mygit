package cn.chonggong;

import org.junit.Test;

/**
 * @author wenguanghua
 * @since 2021-06-11 11:58
 */
public class DeadlockDemo implements Runnable {
    private int flag = 1;
    private static Object obj1 = new Object(), obj2 = new Object();
    @Override
    public void run() {
        System.out.println("flag=" + flag);
        if (flag == 1) {
            synchronized (obj1) {
                System.out.println("我已经锁定obj1，休息0.5秒后锁定obj2去！");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (obj2) {
                System.out.println("1");
            }
        }
        if (flag == 0) {
            synchronized (obj2) {
                System.out.println("我已经锁定obj2，休息0.5秒后锁定obj1去！");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (obj1) {
                System.out.println("0");
            }
        }
    }

    public static void main(String[] args) {
        DeadlockDemo run01 = new DeadlockDemo();
        DeadlockDemo run02 = new DeadlockDemo();
        run01.flag = 1;
        run02.flag = 0;
        Thread thread01 = new Thread(run01);
        Thread thread02 = new Thread(run02);
        System.out.println("线程开始喽！");
        thread01.start();
        thread02.start();
    }
}
