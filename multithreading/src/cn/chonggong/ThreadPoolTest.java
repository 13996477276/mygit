package cn.chonggong;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wenguanghua
 * @since 2021-06-11 09:01
 * 【a】创建ExecutorService对象,根据具体需求创建合适的线程池.
【b】使用executorService.submit()方法,传入Runnable实例对象.(也可以传入Callable对象,Callable结合submit()这种方式可以获取线程执行完返回的结果)
【c】使用executorService.shutdown()方法销毁线程池.
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        //创建线程池
        //newFixedThreadPool: 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待.
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            //为线程池分配任务
            executorService.submit(new FirstThread());
            executorService.submit(new SecondThread());
        }
        //main线程
        for (int i = 0; i < 5; i++) {
            System.out.println("FourthThreadCreatedMethod.main[main线程]......" + i);
        }
        //销毁线程池
        executorService.shutdown();
    }
}
/*** 线程一*/
class FirstThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("FirstThread.run[线程一]......" + i);
        }
    }
}
/*** 线程二*/
class SecondThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("SecondThread.run[线程二]......" + i);
        }
    }
}
