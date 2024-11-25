package com.example.mymusic_backend.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChartUtils {


    public static  boolean areKeysSequential(Map<Integer, Long> map) {
        if (map == null || map.isEmpty()) {
            return false;
        }

        Set<Integer> keys = new HashSet<>(map.keySet());
        int size = map.size();

        for (int i = 1; i <= size; i++) {
            if (!keys.contains(i)) {
                return false;
            }
        }

        return true;
    }
}

