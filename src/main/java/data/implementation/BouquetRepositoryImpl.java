package data.implementation;

import data.repository.BouquetsRepository;
import domain.Bouquet;

import java.util.List;

public class BouquetRepositoryImpl implements BouquetsRepository {
    private static final String SQL_GET_ALL_FLOWERS = "SELECT * FROM flowers";

    public Bouquet generateBouquet(int size) {
        return null;
    }

    public Bouquet placeOrder(Bouquet bouquet, String customerName) {
        //order wegschrijven naar file
        // PAS ALS DEZE KLANT ZIJN EERST BESTELLING PLAATST
        // directory heeft de naam van de klant als directorynaam
        // inhoud bevat bestelde bouquets in object (niet humans readable)
        return null;
    }

    public List<Bouquet> getOrders(String customerName) {
        // order als lijst uit files halen
        // directory heeft de naam van de klant als directorynaam
        // inhoud bevat bestelde bouquets in object (niet human readable)
        return null;
    }
}
