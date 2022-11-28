package bank.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String ownerName;
    private String ownerId;
    private String accountNumber;
    private BigDecimal balance;
    private String bankName;
    private List<History> histories;
    private BigDecimal interestRate;

    public Account(String ownerName, String ownerId, String accountNumber, String bankName, BigDecimal interestRate) {
        this.ownerName = ownerName;
        this.ownerId = ownerId;
        this.accountNumber = accountNumber;
        this.balance = new BigDecimal(0);
        this.bankName = bankName;
        this.histories = new ArrayList<>();
        this.interestRate = interestRate;
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
        hypenAttacher.append(accountNumber.substring(6));

        return hypenAttacher.toString();
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public String getBalanceApplyInterestRate() {
        DecimalFormat decimalFormatter = new DecimalFormat("0.##");
        BigDecimal result = this.interestRate.add(BigDecimal.valueOf(1));
        return decimalFormatter.format(this.balance.multiply(result));
    }

    public BigDecimal getInterestRate() {
        return this.interestRate;
    }

    public String getBankName() {
        return this.bankName;
    }

    public List<History> getHistories() {
        return this.histories;
    }

    public void addHistory(ETradeType type, BigDecimal amount, BigDecimal fee, BigDecimal afterBalance, String traderName) {
        History history = new History(OffsetDateTime.now(), this.accountNumber, type, amount, fee, afterBalance, traderName);
        histories.add(history);
    }

    public String printAllHistoriesOrNull() {
        StringBuilder historyBuilder = new StringBuilder();
        DecimalFormat decimalFormatter = new DecimalFormat("0.##");

        if (histories.size() == 0) {
            return null;
        }

        for (int i = 0; i < histories.size(); i++) {
            History singleHistory = histories.get(i);

            historyBuilder.append(String.format("%d. %s, %s, ", i + 1, singleHistory.getTraderName(),
                    singleHistory.getTypeByString()));
            // 만약 거래 금액이 0보다 크다면!
            if (singleHistory.getType() == ETradeType.DEPOSIT) {
                historyBuilder.append(String.format("+%s원", decimalFormatter.format(singleHistory.getAmount())));
            } else {
                historyBuilder.append(String.format("-%s원", decimalFormatter.format(singleHistory.getAmount())));
            }

            historyBuilder.append(String.format("[%s]%s", singleHistory.getTransactionDate(), System.lineSeparator()));
        }

        return historyBuilder.toString();
    }

    public String printHistory(int index) {
        StringBuilder historyBuilder = new StringBuilder();
        DecimalFormat decimalFormatter = new DecimalFormat("0.##");

        History targetHistory = histories.get(index);
        historyBuilder.append(String.format("%s%s", targetHistory.getTransactionDate(), System.lineSeparator()));

        historyBuilder.append(String.format("거래금액: %s%s", decimalFormatter.format(targetHistory.getAmount()),
                System.lineSeparator()));
        if (targetHistory.getType() == ETradeType.DEPOSIT) {
            historyBuilder.append(String.format("거래후 잔액: +%s%s", decimalFormatter.format(targetHistory.getAfterBalance()),
                    System.lineSeparator()));
        } else {
            historyBuilder.append(String.format("거래후 잔액: -%s%s", decimalFormatter.format(targetHistory.getAfterBalance()),
                    System.lineSeparator()));
        }
        historyBuilder.append(String.format("거래유형: %s", targetHistory.getTypeByString()));

        if (targetHistory.getFee().compareTo(BigDecimal.ZERO) != 0) {
            historyBuilder.append(String.format("수수료: %s%s", System.lineSeparator(), targetHistory.getFee()));
        }

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
            addHistory(ETradeType.WITHDRAW, amount, BigDecimal.ZERO, this.balance, ownerName);
            return amount;
        }
    }

    public BigDecimal deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        addHistory(ETradeType.DEPOSIT, amount, BigDecimal.ZERO, this.balance, ownerName);
        return amount;
    }

    public boolean transfer(Account yourAccount, BigDecimal amount, BigDecimal fee){
        BigDecimal finalAmount = amount.add(fee);

        if (this.balance.compareTo(finalAmount) < 0) {
            return false;
        } else {
            this.balance = this.balance.subtract(finalAmount);
            yourAccount.balance = yourAccount.balance.add(finalAmount);
            addHistory(ETradeType.TRANSFER, amount, fee, this.balance, ownerName);
            yourAccount.addHistory(ETradeType.DEPOSIT, amount, fee, yourAccount.balance, ownerName);
            return true;
        }
    }
}


