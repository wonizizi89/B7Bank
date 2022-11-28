package bank.db;

import bank.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDB {
    public static List<Customer> customers = new ArrayList<>();

    public static void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public static void deleteBank(Customer customer) {
        customers.remove(customer);
    }
}
