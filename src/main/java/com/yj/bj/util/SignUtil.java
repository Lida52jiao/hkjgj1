package com.yj.bj.util;


import java.util.*;

/**
 * Created by bin on 2017/12/12.
 */
public class SignUtil {
    public static void main(String[] args) {
    }
    public static String createYJSign(HashMap<String,Object> hashMap){
        return createYJSign(hashMap, "SGNB");
    }
    public static String createYJSign(HashMap<String,Object> hashMap,String key){
        return MD5Util.getMD5String(convert(hashMap)+"&key="+key);
    }
    public static String convert(HashMap<String, Object> hashMap) {
        if (!hashMap.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            ArrayList<String> rest = new ArrayList<String>();
            Iterator<Map.Entry<String, Object>> iterator = hashMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                String key = next.getKey();
                rest.add(key);
            }
            Object[] array = rest.toArray();
            Arrays.sort(array, new Comparator<Object>() {
                public int compare(Object o1, Object o2) {
                    return o1.toString().compareTo(o2.toString());
                }
            });
            for (int i = 0; i < array.length; i++) {
                builder.append(array[i] + "=" + hashMap.get(array[i]));
                //builder.append(hashMap.get(array[i]));
                if (i != array.length - 1) {
                    builder.append("&");
                }
            }
            return builder.toString();
        } else {
            return null;
        }
    }
}
