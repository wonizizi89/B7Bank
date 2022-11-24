package program;

import java.util.HashMap;

public class Customer {
    private String name;
    private HashMap<String, String> accounts;
    private String customerId; // 동명 이인일 수 있어서 이걸로 식별

    public Customer(String name, String customerId) {
        this.name = name;
        this.accounts = new HashMap<>();
        this.customerId = customerId;
    }

}
