package org.example.sandbox.search;

import java.util.List;

public class SearchUtility {

    public static int linearSearch(int[] array, int targetValue) {

        int found = Integer.MIN_VALUE;
        // O(n)
        for (int i : array) {

            if (i == targetValue) {
                found = targetValue;
                break;
            }
        }

        return found;
    }

    public static <T> T linearSearch(List<T> objectList, T target) {

        T result = null;
        if (objectList.contains(target)) {
            result = objectList.get(objectList.indexOf(target));
        }
        return result;
    }

    // O(n)
    public static <T extends Comparable<T>> boolean linearSearch(T[] array, T target) {

        boolean found = false;

        // O(n)
        for (T t : array) {
            if (t.compareTo(target) == 0) {
                found = true;
                break;
            }
        }

        return found;
    }
}
