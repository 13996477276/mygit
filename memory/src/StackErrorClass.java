/**
 * @author wenguanghua
 * @since 2021-05-07 11:45
 * -Xss设置栈大小
 */
public class StackErrorClass {
    private static int count = 1;
    public static void count(){
        System.out.println(count++);
        count();
    }

    public static void main(String[] args) {
        StackErrorClass.count();
    }
}
