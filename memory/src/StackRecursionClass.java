/**
 * @author wenguanghua
 * @since 2021-05-05 16:46
 */
public class StackRecursionClass {
    public static void main(String[] args){
       int result =  sum(3);
        System.out.println(result);
    }
    public static int sum(int n){
        if(n<=1){
            return n;
        }else{
            return n + sum(n-1);
        }
    }
}
