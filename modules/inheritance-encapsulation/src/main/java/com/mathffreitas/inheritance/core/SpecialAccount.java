package com.mathffreitas.inheritance.core;

public class SpecialAccount extends BankAccount {
    private double limit;

    public SpecialAccount(int accountNumber, String accountHolder, double limit) {
        super(accountNumber, accountHolder);
        this.setLimit(limit);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > super.balance + this.limit) {
            throw new IllegalArgumentException("Insufficient funds, including limit");
        }
        super.balance -= amount;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Limit cannot be negative");
        }
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "SpecialAccount{" +
                "limit=" + limit +
                ", accountNumber=" + accountNumber +
                ", accountHolder='" + accountHolder + '\'' +
                ", balance=" + balance +
                '}';
    }
}
