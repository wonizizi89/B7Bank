package program;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Main {
    public static void main(String[] args) {

        BigDecimal amount = BigDecimal.valueOf(1004d);
        BigDecimal balance = BigDecimal.valueOf(10_000d);

        Account account = new Account("백승범", "110-403-xxxx", "신한은행");
        account.addHistory(TradeType.DEPOSIT, amount, balance, "손오공");
        amount = BigDecimal.valueOf(1005d);
        balance = balance.subtract(amount);
        account.addHistory(TradeType.TRANSFER, amount, balance, "저팔계");
        amount = BigDecimal.valueOf(1010.5d);
        balance = balance.subtract(amount);
        account.addHistory(TradeType.WITHDRAW, amount, balance, "사오정");

        account.printHistories();
        account.printTargetHistory(3);
    }
}
