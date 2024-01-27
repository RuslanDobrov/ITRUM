package ru.itrum.concurrency.task04;

class ComplexTask {
    public void execute() {
        System.out.println(Thread.currentThread().getName() + " executing");
    }
}
