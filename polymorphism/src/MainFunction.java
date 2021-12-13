import po.HuaFu;
import po.HuaMuLan;

/**
 * @author wenguanghua
 * @since 2021-04-23 11:13
 */
public class MainFunction {
    public static void main(String[] args){
        HuaFu huaFu = new HuaMuLan();
        huaFu.killEnemy();
        System.out.println("huahu:"+huaFu.name);
        System.out.println("huahu:"+huaFu.sex);
        HuaMuLan huaMuLan = (HuaMuLan)huaFu;
        huaMuLan.killEnemy();
        huaMuLan.makeUp();
        System.out.println("huamulan："+huaMuLan.name);
    }
}
