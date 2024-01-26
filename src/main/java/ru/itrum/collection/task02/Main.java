package ru.itrum.collection.task02;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Number[] numbers = {1, 2, 3, 1, 4, 5, 1, 2};
        Map<Object, Integer> numberMap = new Main().countOfElements(numbers);
        numberMap.forEach((key, value) -> System.out.println(key + " " + value));
    }

    public Map<Object, Integer> countOfElements(Object[] array) {
        Map<Object, Integer> result = new HashMap<>();

        int count;
        for (Object object : array) {
            count = result.getOrDefault(object, 0);
            result.put(object, count + 1);
        }

        return result;
    }
}
