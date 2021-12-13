package cn.chonggong;

/**
 * @author wenguanghua
 * @since 2021-06-11 08:25
 */
public class Race implements Runnable {
    private static String winner;
    public static void main(String[] args){
        Race race = new Race();
        new Thread(race,"兔子").start();
        new Thread(race,"乌龟").start();
    };
    @Override
    public void run() {
        for(int i=0;i<=100;i++){
//            if(Thread.currentThread().getName().equals("兔子")&&i%10==0){//如果是兔子，强行睡10毫秒
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            boolean flag = gameOver(i);//当为flase时，输出步数
            if(flag){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"跑了"+i+"步");
        }
    }
    private  boolean gameOver(int step){
        if(null!=winner){//当有胜利者产生之后，返回true，退出循环，因为winner是静态的成员变量，所以值统一改变
            return true;
        }else{
            if(step>=100){
                winner = Thread.currentThread().getName();
                System.out.println("winner is "+winner);
                return true;
            }
        }
        return false;
    }
}
