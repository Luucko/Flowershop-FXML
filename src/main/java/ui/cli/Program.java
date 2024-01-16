package ui.cli;

import data.implementation.BouquetRepositoryImpl;
import data.implementation.CustomersRepositoryImpl;

public class Program {

    public static void main(String[] args) {
        new Program().run();
    }

    private void run() {
        registerCustomer("Lucas", "Password");
        loginCustomer("Lucas", "Password");
        generateFlowers(20);
    }

    private void generateFlowers(int size) {
        BouquetRepositoryImpl bouquetRepository = new BouquetRepositoryImpl();
        System.out.println(bouquetRepository.generateBouquet(size).toString());
    }

    private void registerCustomer(String login, String password) {
        CustomersRepositoryImpl customersRepository = new CustomersRepositoryImpl();
        System.out.println(customersRepository.registerCustomer(login, password));
    }

    private void loginCustomer(String login, String password) {
        CustomersRepositoryImpl customersRepository = new CustomersRepositoryImpl();
        System.out.println(customersRepository.checkCustomerCredentials(login, password));
    }
}
