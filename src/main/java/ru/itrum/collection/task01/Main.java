package ru.itrum.collection.task01;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Integer[] integers = {1, 2, 3, 4, 5};

        Filter multiplyBy2 = o -> {
            if (o instanceof Integer) return (int) o * 2;
            return o;
        };
        
        Object[] result = new FilterClass().filter(integers, multiplyBy2);
        Arrays.stream(result).forEach(System.out::println);
    }
}
