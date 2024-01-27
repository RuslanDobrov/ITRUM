package ru.itrum.concurrency.task02;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        Thread depositThread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                account.deposit(100);
                System.out.println("Deposited: " + account.getBalance());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread depositThread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                account.deposit(150);
                System.out.println("Deposited: " + account.getBalance());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread withdrawThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                account.withdraw(180);
                System.out.println("Withdrawn: " + account.getBalance());
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Запуск потоков
        depositThread1.start();
        depositThread2.start();
        withdrawThread.start();

        try {
            // Даем потокам время выполниться
            depositThread1.join();
            depositThread2.join();
            withdrawThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

