package cn.chonggong;

/**
 * @author wenguanghua
 * @since 2021-06-11 07:50
 */
public class RunnableTest implements Runnable {
    @Override
    public void run() {
        for(int i=0;i<20;i++){
            System.out.println("我在听runnable的课---"+Thread.currentThread().getName()+i);
        }
    }
    public static void main(String[] args){
        RunnableTest runnableTest = new RunnableTest();
        Thread thread = new Thread(runnableTest,"RunnableTest");
        thread.start();
        for(int i=0;i<20;i++){
            System.out.println("我在看学习多线程---"+i);
        }
    };
}
