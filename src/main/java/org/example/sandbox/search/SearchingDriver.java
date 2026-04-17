package org.example.sandbox.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SearchingDriver {

    public static void main(String[] args) {

        int[] intArray = {2, 4, 6, 5, 3, 1, -1, -2, 0};
        String[] stringArray = {"a", "b", "c", "d", "e", "f", "g"};

        // Using Streams to create an ArrayList from the int array
        ArrayList<Integer> arrayList = Arrays.stream(intArray).boxed().collect(Collectors.toCollection(ArrayList::new));

        System.out.println(SearchUtility.linearSearch(intArray, 1));
        System.out.println(SearchUtility.linearSearch(intArray, 10));

        System.out.println(SearchUtility.linearSearch(arrayList, 1));
        System.out.println(SearchUtility.linearSearch(arrayList, 10));

        System.out.println(SearchUtility.linearSearch(stringArray, "c"));
        System.out.println(SearchUtility.linearSearch(stringArray, "z"));
    }
}
