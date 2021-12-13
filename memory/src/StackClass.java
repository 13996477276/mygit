import sun.applet.Main;

/**
 * @author wenguanghua
 * @since 2021-05-05 16:46
 */
public class StackClass {
    public static void main(String[] args){
        test1(10);
        test2(20);
    }
    public static void test1(int v){
        String s1 = "china";
        String s2 = "china";
        String s3 = "china";
        String ss1 = new String("china");
        String ss2 = new String("china");
        String ss3 = new String("china");
        System.out.println(s1==s2);
        System.out.println(s1==ss1);
        System.out.println(ss1==ss2);
        System.out.println("ss1.equals(ss2):"+ss1.equals(ss2));
    }
    public static void test2(int v){
        test3(v);
    }
    public static void test3(int v){
        System.out.println(v);
    }
}
