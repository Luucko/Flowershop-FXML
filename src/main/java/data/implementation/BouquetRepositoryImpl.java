package data.implementation;

import data.repository.BouquetsRepository;
import domain.Bouquet;

public class BouquetRepositoryImpl implements BouquetsRepository {
    private static final String SQL_GET_ALL_FLOWERS = "SELECT * FROM flowers";


    public Bouquet generateBouquet(int size) {
        return null;
    }


    public Bouquet placeOrder(Bouquet bouquet, String customerName) {
        return null;
    }
}
