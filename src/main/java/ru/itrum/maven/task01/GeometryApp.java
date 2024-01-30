package ru.itrum.maven.task01;

import ruslan.dobrov.*;

public class GeometryApp {
    public static void main(String[] args) {
        Circle circle = new Circle(7);
        Rectangle rectangle = new Rectangle(8, 10);
        Triangle triangle = new Triangle(2, 6, 8);

        System.out.println("Circle area: " + circle.getArea());
        System.out.println("Rectangle area: " + rectangle.getArea());
        System.out.println("Triangle area: " + triangle.getArea());

        System.out.println("------------------------------------------");

        System.out.println("Circle perimeter: " + circle.getPerimeter());
        System.out.println("Rectangle perimeter: " + rectangle.getPerimeter());
        System.out.println("Triangle perimeter: " + triangle.getPerimeter());

        System.out.println("------------------------------------------");

        circle.draw();
        rectangle.draw();
        triangle.draw();

        Cube cube = new Cube(3);
        Sphere sphere = new Sphere(2);

        System.out.println("------------------------------------------");

        System.out.println("Cube area: " + cube.getArea());
        System.out.println("Sphere area: " + sphere.getArea());
    }
}
