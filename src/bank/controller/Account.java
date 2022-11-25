package bank.controller;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String ownerName;
    private String accountNumber;
    private int balance;
    private String bankName;
    private List<History> histories;

    public Account(String ownerName, String accountNumber, String bankName) {
        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.bankName = bankName;
        this.histories = new ArrayList<>();
    }

    protected String getOwnerName() {
        return this.ownerName;
    }

    protected String getAccountNumber() {
        return accountNumber;
    }

    protected int getBalance() {
        return balance;
    }

    protected String getBankName() {
        return bankName;
    }

    public void addHistory(ETradeType type, BigDecimal amount, BigDecimal balance, String traderName) {
        History history = new History(OffsetDateTime.now(), this.accountNumber, type, amount, balance, traderName);
        histories.add(history);
    }

    public void printHistories() {
        StringBuilder historyBuilder = new StringBuilder();

        for (int i = 0; i < histories.size(); i++) {
            History singleHistory = histories.get(i);
            historyBuilder.append(String.format("%d. %s, %s, %s원 ", i + 1, singleHistory.getTraderName(),
                    singleHistory.getTypeByString(), singleHistory.getAmount()));
            historyBuilder.append(String.format("[%s %s]%s", singleHistory.getTransactionDate(),
                    singleHistory.getTransactionTime(), System.lineSeparator()));
        }

        System.out.println(historyBuilder.toString());
    }

    public void editAccount(String newOwnerName, String newAccountNumber, String newBankName) {
        this.ownerName = newOwnerName;
        this.accountNumber = newAccountNumber;
        this.bankName = newBankName;
    }

    public int withdraw(int amount) throws Exception {
        if (this.balance < amount) {
            throw new Exception("잔액이 모자랍니다.");
        } else {
            this.balance -= amount;
            return amount;
        }
    }
}
