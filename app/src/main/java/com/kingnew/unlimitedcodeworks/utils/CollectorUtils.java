package com.kingnew.unlimitedcodeworks.utils;


import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * author : wjy
 * time   : 2017/1/17
 * desc   : 容器工具集
 */
@SuppressWarnings("unchecked")
public class CollectorUtils {

    /**
     * 判断对象数组是否为空
     *
     * @param ts 对象列表
     * @return boolean 是否为空
     */
    @SafeVarargs
    public static synchronized <T> boolean isEmpty(T... ts) {
        return (ts == null || ts.length <= 0);
    }

    /**
     * 判断map是否为空
     *
     * @param map map对象
     * @return boolean 是否为空
     */
    public static synchronized boolean isEmpty(@Nullable Map map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断容器是否为空
     *
     * @param collection 容器类
     * @return boolean 是否为空
     */
    public static synchronized boolean isEmpty(@Nullable Collection collection) {
        return collection == null || collection.size() <= 0;
    }

    /**
     * 从容器中找出最大对象（对象必须实现了Comparable接口）
     *
     * @param c   容器
     * @param <T> 对象类型
     * @return 最大对象
     */
    public static synchronized <T extends Comparable> T findMax(@Nullable Collection<T> c) {
        if (c == null || c.isEmpty()) {
            throw new NoSuchElementException();
        }
        Iterator<T> iterator = c.iterator();
        T largest = iterator.next();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (largest.compareTo(next) < 0) {
                largest = next;
            }
        }
        return largest;
    }

    /**
     * 从容器中找出最小对象（对象必须实现了Comparable接口）
     *
     * @param c   容器
     * @param <T> 对象类型
     * @return 最小对象
     */
    public static synchronized <T extends Comparable> T findMin(@Nullable Collection<T> c) {
        if (c == null || c.isEmpty()) {
            throw new NoSuchElementException();
        }
        Iterator<T> iterator = c.iterator();
        T miniest = iterator.next();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (miniest.compareTo(next) > 0) {
                miniest = next;
            }
        }
        return miniest;
    }
}
