package data.repository;

import domain.Bouquet;
import domain.Customer;

import java.util.List;

public interface BouquetsRepository {
    Bouquet generateBouquet(int size);
    Bouquet placeOrder(Bouquet bouquet, String customerName);
    List<Bouquet> getOrders(String customerName);
}
