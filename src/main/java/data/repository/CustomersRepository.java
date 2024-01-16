package data.repository;

import domain.Customer;

public interface CustomersRepository {
    Customer registerCustomer(String login, String password);
    boolean checkCustomerCredentials(String login, String password);
}
