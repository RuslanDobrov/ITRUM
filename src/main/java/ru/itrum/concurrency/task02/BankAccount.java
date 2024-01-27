package ru.itrum.concurrency.task02;

public class BankAccount {
    private double balance;

    public synchronized void deposit(double amount) {
        balance += amount;
    }

    public synchronized void withdraw(double amount) {
        if (balance - amount < 0) {
            System.out.println("There are insufficient funds in the account");
        } else {
            balance -= amount;
        }
    }

    public synchronized double getBalance() {
        return balance;
    }
}
