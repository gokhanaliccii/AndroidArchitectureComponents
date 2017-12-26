package com.gokhanaliccii.flavorhunter.util;

import java.util.List;

/**
 * Created by gokhan on 27/12/17.
 */

public class CollectionUtil {

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }

    public static <T> boolean hasItem(List<T> list) {
        return !isEmpty(list);
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }


}
