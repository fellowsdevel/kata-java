package com.fellowsdevel.katajava.banking;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankingKata {


    public static Account createAccount() {
        return Account.getNewAccount();

    }

    private static class Transaction {

        private final LocalDateTime localDateTime = LocalDateTime.now();
        private final int amount;
        private final int balance;

        public Transaction(int amount, int balance) {
            this.balance = balance;
            this.amount = amount;
        }

        public String printTransaction() {
            return "%s %s%d    %d".formatted(dateToString(localDateTime), getSignal(amount), amount, balance);
        }

        private String dateToString(LocalDateTime localDateTime) {
            return localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        }

        private String getSignal(int amount) {
            if (amount > 0) {
                return "+";
            }
            return "";
        }
    }

    public static class Account {
        private final List<Transaction> transactions = new ArrayList<>();
        private int balance = 0;

        private Account() {

        }

        public static Account getNewAccount() {
            return new Account();
        }

        public void deposit(int amount) {
            if (amount <= 0) {
                System.out.println("Amount shoiuld be positive");
                return;
            }

            this.balance += amount;
            transactions.add(new Transaction(amount, this.balance));
        }

        public String printStatement() {
            List<String> transacts = transactions.stream().map(Transaction::printTransaction).toList();
            return "Date                Amount  Balance\n%s".formatted(String.join("\n", transacts));
        }

        public void withdraw(int amount) {
            if (amount <= 0) {
                System.out.println("Amount shoiuld be positive");
                return;
            }
            if ((this.balance - amount) < 0) {
                System.out.println("Amount exceed balance");
                return;
            }
            this.balance -= amount;
            transactions.add(new Transaction(-amount, this.balance));
        }
    }
}
