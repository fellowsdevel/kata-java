package com.fellowsdevel.katajava.banking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankingKataTest {

    @Test
    void classExists() {
        BankingKata.Account account = BankingKata.createAccount();
        assertNotNull(account);
    }
    @Test
    void printAmount() {
        BankingKata.Account account = BankingKata.createAccount();

        account.deposit(500);

        assertNotNull(account);

        assertTrue(account.printStatement().startsWith("Date                Amount  Balance\n"));
        assertTrue(account.printStatement().endsWith(" +500    500"));

        account.withdraw(100);
        assertTrue(account.printStatement().endsWith(" -100    400"));

        System.out.println(account.printStatement());
    }
}
