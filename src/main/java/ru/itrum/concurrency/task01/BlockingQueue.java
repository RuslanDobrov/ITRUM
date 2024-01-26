package ru.itrum.concurrency.task01;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueue<T> {
    private Queue<T> queue;

    public BlockingQueue(int size) {
        this.queue = new ArrayBlockingQueue<>(size, true);
    }

    public synchronized void enqueue(T object) {
        queue.add(object);
    }

    public synchronized T dequeue() {
        return queue.poll();
    }

    public int size() {
        return queue.size();
    }
}
