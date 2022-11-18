package com.library_management.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    public static <T> List<T> slice(List<T> list, int index, int count) {
        List<T> result = new ArrayList<>();
        if (index >= 0 && index < list.size()) {
            int end = Math.min(index + count, list.size());
            for (int i = index; i < end; i++) {
                result.add(list.get(i));
            }
        }
        return result;
    }
}
