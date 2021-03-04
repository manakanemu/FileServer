package com.simplefileserver.utils;

import java.util.Comparator;

public abstract class dateCompareUtils implements Comparator<String> {
    @Override
    public int compare(String str1, String str2) {
        return str2.compareTo(str1);
    }
}
