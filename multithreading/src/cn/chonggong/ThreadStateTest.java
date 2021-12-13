package cn.chonggong;

/**
 * @author wenguanghua
 * @since 2021-06-11 09:09
 */
public class ThreadStateTest extends Thread {
    @Override
    public void run(){
        for(int i=0;i<5;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("================");
        }

    }
    public static void main(String[] args){
        ThreadStateTest threadStateTest = new ThreadStateTest();
        Thread.State state = threadStateTest.getState();//获取当前state；
        System.out.println("线程启动之前的state:"+state);
        threadStateTest.start();
        state = threadStateTest.getState();//获取当前state；
        System.out.println("线程启动之后的state:"+state);
        while(state!= State.TERMINATED){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            state = threadStateTest.getState();//获取当前state；
            System.out.println("未停止线程前的状态变化state:"+state);
        }
    };

}
