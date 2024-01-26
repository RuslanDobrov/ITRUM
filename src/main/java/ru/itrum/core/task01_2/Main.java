package ru.itrum.core.task01_2;

public class Main {
    public static void main(String[] args) {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();

        customStringBuilder.append("Hello, ");
        System.out.println(customStringBuilder);

        customStringBuilder.append("world!");
        System.out.println(customStringBuilder);

        customStringBuilder.undo();
        System.out.println(customStringBuilder);

        customStringBuilder.append("world!");
        System.out.println(customStringBuilder);

        customStringBuilder.undo();
        System.out.println(customStringBuilder);

        customStringBuilder.append("world!");
        System.out.println(customStringBuilder);

        customStringBuilder.undo();
        System.out.println(customStringBuilder);

        customStringBuilder.undo();
        System.out.println(customStringBuilder);
    }
}
