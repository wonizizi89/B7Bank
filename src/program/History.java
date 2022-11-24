package program;

import java.time.OffsetDateTime;

public class History {
    private OffsetDateTime transactionDate;
    private String accountNumber;
    private boolean type; // True: 입금, False: 출금
    private int amount; // 마찬가지로 int로 했지만 후에 Decimal 등 적절한 형태로 변환
    private String traderName;

    public History(OffsetDateTime transactionDate, String accountNumber, boolean type, int amount, String traderName) {
        this.transactionDate = transactionDate;
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.traderName = traderName;
    }


}
