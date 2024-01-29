package ru.itrum.concurrency.task01;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private Queue<T> queue;
    private int capacity;

    public BlockingQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void enqueue(T object) throws InterruptedException {
        while (queue.size() == capacity) {
            this.wait();
        }
        queue.add(object);
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
        }
        T object = queue.poll();
        notifyAll();
        return object;
    }

    public synchronized int size() {
        return queue.size();
    }
}
