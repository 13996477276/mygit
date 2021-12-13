package cn.chonggong;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author wenguanghua
 * @since 2021-06-09 10:47
 * 多个线程一起执行，等待所有线程执行完毕之后一起执行
 */
public class CountDownLatchTest {
    @Test
    public void test(){
        int threadTotals = 10;//定义总共10个线程
        CountDownLatch begin = new CountDownLatch(1);//所有线程统一开始
        CountDownLatch end  = new CountDownLatch(threadTotals);//主线程阻塞，直到所有线程完成之后统一开始
        begin.countDown();//开始线程
        for(int i=0; i< threadTotals;i++){
            Runnable runnable = allThreadExecute(i,begin,end);//所有线程执行
            new Thread(runnable).start();//使用thread调用start，开始线程
        }
        try {
            end.await();
            System.out.println("所有线程都已执行结束，开始后续流程");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("线程执行出现问题了");
        }

    };



    public Runnable allThreadExecute(int threadNum, CountDownLatch begin, CountDownLatch end){
        Runnable runnable = new Runnable() {//采用匿名函数
            @Override
            public void run() {
                try {
                    System.out.println("线程："+threadNum+"开始工作");
                    begin.await();
                    System.out.println("线程："+threadNum+"正在完成自己的逻辑");
                    end.countDown();
                    System.out.println("线程："+threadNum+"结束工作");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        return runnable;
    };
}
