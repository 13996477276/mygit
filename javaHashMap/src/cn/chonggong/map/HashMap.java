package cn.chonggong.map;

/**
 * @author wenguanghua
 * @since 2021-06-03 13:34
 */
public class HashMap<K, V> implements Map<K, V> {
    Entry<K, V> table[] = null;
    int size = 0;

    public HashMap() {
        table = new Entry[16];
    }

    /****     *
     * 1、hash》hashcode》取模
     * *  2、拿到下标值 对应Entry数组去找到当前下标值
     * *  3、如果为空 直接存储》数组
     * *  4、如果不为空 用链表
     * * @param k     * @param v     * @return
     * */
    @Override
    public V put(K k, V v) {
        int index = hash(k);//计算下标
        Entry<K, V> entry = table[index];//将table里面的index下标数据赋值给entry
        if (null == entry) {//通过index查看table里面的数据是否为null
            //张三、李四，王五
            table[index] = new Entry<>(k, v, index, null);//如果为null，将new出来的 Entry直接赋值给table[index]就可以了
            size++;
        } else {//将entry赋值给next
            //孙悟空，Monkey
            table[index] = new Entry<>(k, v, index, entry);
        }
        return null;
    }

    private int hash(K k) {
        int i = k.hashCode() % 15;//为什么取15，因为底层涉及到移位的操作，这样效率更高，15取模的数据更加均匀
//        System.out.println("取模的数据位置："+Math.abs(i));
        //return i>=0?i:-i;
        return Math.abs(i);//因为有些hash值为负数，所以取绝对值，获取下标
    }

    // /***
    // * 1、k 去hash
    // * 2、数组下标
    // * 3、当前下标 和我查询的下标值是否相等、
    // * 4、相等》直接放回 说明查询到
    // * 5、不相等》判断当前next是否为空
    // * 6、为空直接返回null 相等不为空 直接返回
    // * @param k     * @return     */
    public V get(K k) {
        int index = hash(k);
        Entry<K, V> entry = table[index];
        if (null == entry) {
            return null;
        }
        return findValue(k, entry);
    }

    private V findValue(K k, Entry<K, V> entry) {
        if (k == entry.getKey() || k.equals(entry.getKey())) {//通过key比对获取value
            return entry.getValue();
        } else {
            if (entry.next != null) {//如果没有找到，发现next不为空，循环查找
                return findValue(k, entry.next);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    class Entry<K, V> implements Map.Entry<K, V> {
        K k;
        V v;
        int hash;
        Entry<K, V> next;

        public Entry(K k, V v, int hash, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }
}
