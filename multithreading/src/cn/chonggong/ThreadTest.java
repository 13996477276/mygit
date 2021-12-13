package cn.chonggong;

/**
 * @author wenguanghua
 * @since 2021-06-11 07:44
 */
public class ThreadTest extends Thread {
    @Override
    public void run(){
        for(int i=0;i<20;i++){
            System.out.println("我在看thread的代码---"+this.getName()+i);
        }
    }
    public static void main(String[] args){
        Thread thread = new ThreadTest();
//        thread.start();
        thread.run();
        for(int i=0;i<20;i++){
            System.out.println("我在看学习多线程---"+i);
        }
    };
}
