import com.sun.deploy.net.socket.UnixDomainSocketException;

/**
 * @author wenguanghua
 * @since 2021-05-05 15:26
 */
public class Worker {
    String name;
    int age;
    int id;
    Mobile mobile;
    public Worker() {
    }
    void play(){
        System.out.println("工人在玩手机");
    };
    void work(){
        System.out.println("工人在上班");
    }
    public static void main(String[] args){
        Worker worker = new Worker();
        System.out.println(worker);
        worker.id = 111;
        worker.name = "张飞";
        worker.age = 55;

        Mobile mobile = new Mobile();
        mobile.brand = "华为";
        worker.mobile = mobile;
        worker.play();
        worker.work();
    }
}
class Mobile{
    String brand;
    public Mobile() {
    }
}

