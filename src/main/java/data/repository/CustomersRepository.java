package data.repository;

import domain.User;

public interface CustomersRepository {
    User registerUser(String username, String password);
    boolean checkUserCredentials(String username, String password);
}
