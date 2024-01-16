package data.repository;

import domain.Bouquet;
import domain.Customer;

public interface BouquetsRepository {
    Bouquet generateBouquet(int size);

}
