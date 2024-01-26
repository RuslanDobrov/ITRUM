package ru.itrum.collection.task01;

public class FilterClass {

    public Object[] filter(Object[] array, Filter filter) {
        Object[] resultArray = new Object[array.length];

        for (int i = 0; i < array.length; i++) {
            resultArray[i] = filter.apply(array[i]);
        }

        return resultArray;
    }

}
