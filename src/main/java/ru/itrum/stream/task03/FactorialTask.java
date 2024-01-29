package ru.itrum.stream.task03;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {
    private final int n;

    public FactorialTask(int n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        if (n <= 1) {
            return 1L;
        } else {
            FactorialTask factorialTask = new FactorialTask(n - 1);
            factorialTask.fork();
            long temp = factorialTask.join();
            return n * temp;
        }
    }
}
