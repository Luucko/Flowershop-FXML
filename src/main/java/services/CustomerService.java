package services;

import data.implementation.CustomersRepositoryImpl;
import domain.Customer;

public class CustomerService {
    private CustomersRepositoryImpl repository = new CustomersRepositoryImpl();

    public Customer registerCustomer(String login, String password) {
        return repository.registerCustomer(login, password);
    }

    public boolean checkCustomerCredentials(String login, String password) {
        return repository.checkCustomerCredentials(login, password);
    }
}
