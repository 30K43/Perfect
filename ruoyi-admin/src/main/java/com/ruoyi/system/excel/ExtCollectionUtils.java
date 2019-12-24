package com.ruoyi.system.excel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtCollectionUtils {
    /**
     *  list转map
     * @param list
     * @param keyField 需要成为为键的字段名称
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> Map<K,V> listToMap(List<V> list, String keyField){
        Map<K,V> result = new HashMap<>();
        if(list != null && list.size() > 0 && keyField != null && !"".equals(keyField)){
            String firstLetter = keyField.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + keyField.substring(1);
            try {
                for(Object obj : list){
                    Method method = obj.getClass().getMethod(getter,null);
                    K key = (K) method.invoke(obj,null);
                    if(key != null){
                        result.put(key,(V) obj);
                    }
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
