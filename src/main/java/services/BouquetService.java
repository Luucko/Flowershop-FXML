package services;

import data.implementation.BouquetRepositoryImpl;
import domain.Bouquet;

import java.util.List;

public class BouquetService {
    private BouquetRepositoryImpl repository = new BouquetRepositoryImpl();

    public Bouquet generateBouquet(int size){
        return repository.generateBouquet(size);
    }

    public Bouquet placeOrder(Bouquet bouquet, String customerName){
        return repository.placeOrder(bouquet, customerName);
    }

    public List<Bouquet> getOrders(String customerName){
        return repository.getOrders(customerName);
    }
}
