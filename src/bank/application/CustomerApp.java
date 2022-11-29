package bank.application;

import bank.db.CustomerDB;
import bank.entity.Customer;

import java.util.HashMap;

public class CustomerApp {
    private static HashMap<String, Customer> customers = new HashMap<>();

    public static Customer registerCustomer(String customerID, String password, String customerName) {
        Customer newCustomer = new Customer(customerID, password, customerName);
        customers.put(customerID, newCustomer);
        CustomerDB.addCustomer(newCustomer);
        return newCustomer;
    }

    public static Customer getCustomerOrNull(String customerID) {
        if (customers.containsKey(customerID)) {
            return customers.get(customerID);
        } else {
            return null;
        }
    }

    public static boolean loginCustomer(Customer customer, String password) {
        if (customer.getPassword().equals(password)) {
            return true;
        }

        return false;
    }

    public static boolean checkDuplicateID(String customerID) {
        if (customers.containsKey(customerID)) {
            return false;
        } else {
            return true;
        }
    }
}
