package program;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String ownerName;
    private String accountNumber;
    private int balance; // 일단은 int로 해놨으나 돈을 표현하는데 있어서 Decimal 형이 적절하므로 추후 수정
    private String bankName; // 필요?
    private List<History> histories;

    public String getOwnerName() {
        return ownerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public String getBankName() {
        return bankName;
    }

    public Account(String ownerName, String accountNumber, String bankName) {
        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.bankName = bankName;
        this.histories = new ArrayList<>();
    }

    public void addHistory(TradeType type, BigDecimal amount, BigDecimal balance, String traderName) {
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

    public void printTargetHistory(int index) {
        History targetHistory = histories.get(--index);
        StringBuilder historyBuilder = new StringBuilder();

        historyBuilder.append(String.format("• 거래자: %s%s", targetHistory.getTraderName(), System.lineSeparator()));
        historyBuilder.append(String.format("• 거래 유형: %s%s", targetHistory.getTypeByString(), System.lineSeparator()));
        historyBuilder.append(String.format("• 거래 금액: %s원%s", targetHistory.getAmount(), System.lineSeparator()));
        historyBuilder.append(String.format("• 거래후 잔액: %s원%s", targetHistory.getBalance(), System.lineSeparator()));
        historyBuilder.append(String.format("• 거래 시각: %s %s%s", targetHistory.getTransactionDate(),
                targetHistory.getTransactionTime(), System.lineSeparator()));

        System.out.println(historyBuilder.toString());
    }
}
