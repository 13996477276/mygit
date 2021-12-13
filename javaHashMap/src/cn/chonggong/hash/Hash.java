package cn.chonggong.hash;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author wenguanghua
 * @since 2021-06-04 08:15
 * 哈希算法演示
 */
public class Hash {
    public static void main(String[] args){
        // 创建一个MessageDigest实例:
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            // 反复调用update输入数据:
            md.update("Hello".getBytes("UTF-8"));
//            md.update("World".getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] result = md.digest(); // 16 bytes: 68e109f0f40ca72a15e05cc22786f8e6
        System.out.println("MD5哈希值："+new BigInteger(1, result).toString(16));
    };
}
