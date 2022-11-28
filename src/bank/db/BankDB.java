package bank.db;

import bank.entity.Bank;

import java.util.ArrayList;
import java.util.List;

public class BankDB {
    private static List<Bank> banks = new ArrayList<>();

    public static Bank getBankByIndex(int index) {
        return banks.get(index);
    }

    public static void addBank(Bank bank) {
        banks.add(bank);
    }

    public static List<Bank> getBanks() {
        return banks;
    }
}
