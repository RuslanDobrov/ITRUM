package ru.itrum.concurrency.task01;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new BlockingQueue<>(5);

        Thread threadProduce = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    blockingQueue.enqueue(i);
                    System.out.println("Задача добавлена " + i);
                    System.out.println("Размер очереди добавления " + blockingQueue.size());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread threadConsume = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("Размер очереди выполнения " + blockingQueue.size());
                    blockingQueue.dequeue();
                    Thread.sleep(1000);
                    System.out.println("Задача выполнена " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        threadProduce.start();
        threadConsume.start();
    }
}
