package com.mathffreitas.inheritance;

import com.mathffreitas.inheritance.core.BankAccount;
import com.mathffreitas.inheritance.core.Product;
import com.mathffreitas.inheritance.core.SpecialAccount;

import java.lang.IO;
public class Main {
    public static void main(String[] args) {
    Product product = new Product(1, 10.99f, "Sample core.Product", 100);
    BankAccount bankAccount, specialAccount;
    bankAccount = new BankAccount(123, "Matheus");
    specialAccount = new SpecialAccount(456, "John Doe", 1000.0f);

    bankAccount.deposit(100);
    specialAccount.deposit(100);

    IO.println(bankAccount.toString());
    IO.println(specialAccount.toString());

//    bankAccount.withdraw(200);
    specialAccount.withdraw(200);

    IO.println(bankAccount.toString());
}
}
