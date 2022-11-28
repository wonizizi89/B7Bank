package bank.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Account {
    private String ownerName;
    private String accountNumber;
    private BigDecimal balance;
    private String bankName;
    private List<History> histories;

    public Account(String ownerName, String accountNumber, String bankName) {
        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.balance = new BigDecimal(0);
        this.bankName = bankName;
        this.histories = new ArrayList<>();
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountNumberWithHypen() {
        StringBuilder hypenAttacher = new StringBuilder();
        hypenAttacher.append(accountNumber.substring(0, 3));
        hypenAttacher.append("-");
        hypenAttacher.append(accountNumber.substring(3, 6));
        hypenAttacher.append("-");
        hypenAttacher.append(accountNumber.substring(6, accountNumber.length()));

        return hypenAttacher.toString();
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void addHistory(ETradeType type, BigDecimal amount, BigDecimal balance, String traderName) {
        History history = new History(OffsetDateTime.now(), this.accountNumber, type, amount, balance, traderName);
        histories.add(history);
    }

    public String printAllHistoriesOrNull() {
        StringBuilder historyBuilder = new StringBuilder();
        DecimalFormat decimalFormatter = new DecimalFormat("0.##");

        for (int i = 0; i < histories.size(); i++) {
            History singleHistory = histories.get(i);

            historyBuilder.append(String.format("%d. %s, %s, ", i + 1, singleHistory.getTraderName(),
                    singleHistory.getTypeByString()));
            // 만약 거래 금액이 0보다 크다면!
            if (singleHistory.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                historyBuilder.append(String.format("+%s원", decimalFormatter.format(singleHistory.getAmount())));
            } else {
                historyBuilder.append(String.format("%s원", decimalFormatter.format(singleHistory.getAmount())));
            }

            historyBuilder.append(String.format("[%s]%s", singleHistory.getTransactionDate(), System.lineSeparator()));
        }

        return historyBuilder.toString();
    }

    public String printHistory(int index) {
        StringBuilder historyBuilder = new StringBuilder();
        DecimalFormat decimalFormatter = new DecimalFormat("0.##");

        History targetHistory = histories.get(index - 1);
        historyBuilder.append(String.format("%s%s", targetHistory.getTransactionDate(), System.lineSeparator()));
        historyBuilder.append(String.format("거래금액: %s%s", decimalFormatter.format(targetHistory.getAmount()),
                System.lineSeparator()));
        historyBuilder.append(String.format("거래후 잔액: %s%s", decimalFormatter.format(targetHistory.getBalance()),
                System.lineSeparator()));
        historyBuilder.append(String.format("거래유형: %s", targetHistory.getTypeByString()));

        return historyBuilder.toString();
    }

    public void editAccount(String newOwnerName, String newAccountNumber, String newBankName) {
        this.ownerName = newOwnerName;
        this.accountNumber = newAccountNumber;
        this.bankName = newBankName;
    }

    public BigDecimal withdraw(BigDecimal amount) {
        if (this.balance.compareTo(amount) < 0) {
             return BigDecimal.ZERO;
        } else {
            this.balance = this.balance.subtract(amount);
            addHistory(ETradeType.WITHDRAW, amount, this.balance, ownerName);
            return amount;
        }
    }

    public BigDecimal deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        addHistory(ETradeType.DEPOSIT, amount, this.balance, ownerName);
        return amount;
    }

}


