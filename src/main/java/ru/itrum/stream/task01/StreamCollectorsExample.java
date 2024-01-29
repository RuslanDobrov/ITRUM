package ru.itrum.stream.task01;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        //Группируйте заказы по продуктам.
        Map<String, List<Order>> collect = orders.stream().collect(Collectors.groupingBy(Order::getProduct));
        collect.forEach((product, cost) -> System.out.println(product));
        System.out.println("------------------------------------------");

        //Для каждого продукта найдите общую стоимость всех заказов.
        Map<String, Double> totalCostByProduct = collect.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream().mapToDouble(Order::getCost).sum()
        ));
        totalCostByProduct.forEach((product, cost) -> System.out.println(product + " : " + cost));
        System.out.println("------------------------------------------");

        //Отсортируйте продукты по убыванию общей стоимости.
        List<String> sortProduct = totalCostByProduct.entrySet().stream()
                .sorted((cost1, cost2) -> Double.compare(cost2.getValue(), cost1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        sortProduct.forEach(System.out::println);
        System.out.println("------------------------------------------");

        //Выберите три самых дорогих продукта.
        List<String> topThreeProducts = sortProduct.stream().limit(3).collect(Collectors.toList());

        //Выведите результат: список трех самых дорогих продуктов и их общая стоимость.
        topThreeProducts.forEach(System.out::println);
        System.out.println(topThreeProducts.stream().mapToDouble(totalCostByProduct::get).sum());
    }
}
