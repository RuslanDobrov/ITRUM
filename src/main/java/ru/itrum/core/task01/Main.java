package ru.itrum.core.task01;

public class Main {
    public static void main(String[] args) {
        CustomStringBuilder myStringBuilder = new CustomStringBuilder();

        myStringBuilder.append("Hello, ");
        myStringBuilder.append("world!");

        System.out.println("After appending: " + myStringBuilder.toString());

        myStringBuilder.undo();

        System.out.println("After undo: " + myStringBuilder.toString());

        myStringBuilder.append("world!");

        System.out.println("After appending: " + myStringBuilder.toString());
    }
}