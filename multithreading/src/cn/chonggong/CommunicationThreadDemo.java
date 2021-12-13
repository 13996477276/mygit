package cn.chonggong;

/**
 * @author wenguanghua
 * @since 2021-06-11 11:30
 * 多线程通信
 */
public class CommunicationThreadDemo {
    public static void main(String[] args){
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    };
}

/** * 生产者--演员 */
class Player extends Thread{
    TV tv;
    public Player(TV tv) {
        this.tv = tv;
    }
    @Override
    public void run() {
        for(int i=0; i<30;i++){
            if(i%2 ==0){
                this.tv.play("正在播放小猪佩奇");
            }else{
                this.tv.play("正在播放洗发水广告");
            }
        }
    }
};
/** * 消费者--观众 */
class Watcher extends Thread{
    TV tv;
    public Watcher(TV tv) {
        this.tv = tv;
    }
    @Override
    public void run() {
        for(int i=0; i<30;i++){
            tv.watch();
        }
    }
};
/** * 电视 */
class TV extends Thread{
    String item;//表演的节目
    boolean flag = true;
    public synchronized void play(String item){
        if(!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("演员表演了："+item);
        //通知观众观看
        this.notifyAll();//通知唤醒所有wait（）线程
        this.item = item;
        this.flag = !this.flag;//赋值取反
    }
    public synchronized void watch(){
        if(flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观众观看了："+item);
        this.notifyAll();//通知唤醒所有wait（）线程
        this.flag = !this.flag;//赋值取反
    }
};