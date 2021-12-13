package cn.chonggong.array;

/**
 * @author wenguanghua
 * @since 2021-06-03 13:57
 */
public class Array {
    /**
     * 数组：采用一段连续的存储单元来进行存储数据
     * 特点：指定下标0（1），删除插入O(N),数组查询快，插入慢
     */
    public static void main(String[] args){
        Integer integers[] = new Integer[10];
        integers[0] = 0;
        integers[1] = 1;
        integers[4] = 2;
        integers[4] = 500;
        System.out.println(integers[4]);
    }

}
