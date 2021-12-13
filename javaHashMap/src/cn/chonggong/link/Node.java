package cn.chonggong.link;

/**
 * @author wenguanghua
 * @since 2021-06-03 14:06
 */
public class Node {
    public Node next;
    private Object data;

    public Node(Object data) {
        this.data = data;
    }
    //链表：链表是一种物理存储单元上非连续、非顺序的存储结构
    //特点：插入、删除时间复杂度0（1），查找遍历时间复杂度0（N),插入快，查找慢
    public static void main(String[] args){
        Node node = new Node("赵六");
        node.next = new Node("张三");
        node.next.next = new Node("六一");
        System.out.println("node数据："+node.data);
        System.out.println("node.next数据："+node.next.data);
        System.out.println("node.next.next数据："+node.next.data);
    };
}
