package cn.chonggong.dynamicProxy;

import cn.chonggong.service.Girl;
import cn.chonggong.service.impl.HuaMuLan;

/**
 * @author wenguanghua
 * @since 2021-05-21 10:47
 */
public class HuangZhi {
    public static void main(String[] args){
        //有个女孩叫花木兰
//        Girl girl = new HuaMuLan();
        HuaMuLan huaMuLan = new HuaMuLan();
        huaMuLan.setAge(18);
        huaMuLan.setName("花木兰");
        //她回家之后就由父母管着，要约花木兰，得经过家人同意
        HuaMuLanProxy family = new HuaMuLanProxy(huaMuLan);
        //这次遇到花木兰的妈妈，经过花木兰妈妈同意，才能将花木兰约出来
        Girl mother = (Girl) family.getProxyInstance();
        //妈妈同意了，可以约会
        mother.date("2021年5月26日");
        System.out.println("============================");
        mother.watchMovie();

    };
}
