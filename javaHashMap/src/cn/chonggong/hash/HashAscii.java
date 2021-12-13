package cn.chonggong.hash;

/**
 * @author wenguanghua
 * @since 2021-06-04 08:27
 */
public class HashAscii {
    public static void main(String[] args){
        String s="foes";//foes怎么办
        char[] cs=s.toCharArray();
        int sum=0;
        for(int i:cs){
            System.out.println(i+"");
            sum=i+sum;
        }
        System.out.println("sum："+sum+"");
        System.out.println("sum取模："+sum%15+"");
    };
}
