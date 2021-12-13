package cn.chonggong.map;

/**
 * @author wenguanghua
 * @since 2021-06-03 13:33
 */
public interface Map<K,V> {
    V put(K k ,V v);
    V get(K k);
    int size();
    interface  Entry<K,V>{
        K getKey();
        V getValue();
    }
}
