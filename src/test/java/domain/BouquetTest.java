package domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BouquetTest {

    @Test
    void calculateTotalPriceOfEmptyBouquet() {
        // Arrange
        Bouquet emptyBouquet = new Bouquet(List.of());

        // Act
        double totalPrice = emptyBouquet.calculateTotalPrice();

        // Assert
        assertEquals(0.00, totalPrice);
    }

    @Test
    void calculateTotalPriceOneFlower() {
        // Arrange
        Flower rose = new Flower("Rose", 10.0);
        Bouquet bouquetWithSingleFlower = new Bouquet(List.of(rose));

        // Act
        double totalPrice = bouquetWithSingleFlower.calculateTotalPrice();

        // Assert
        assertEquals(10.0, totalPrice);
    }

    @Test
    void calculateTotalPriceMultipleFlowers() {
        // Arrange
        Flower rose = new Flower("Rose", 10.0);
        Flower tulip = new Flower("Tulip", 8.0);
        Flower lily = new Flower("Lily", 12.0);

        Bouquet bouquetWithMultipleFlowers = new Bouquet(List.of(rose, tulip, lily));

        // Act
        double totalPrice = bouquetWithMultipleFlowers.calculateTotalPrice();

        // Assert
        assertEquals(30.0, totalPrice);
    }

    @Test
    void getFlowers() {
        // Arrange
        Flower rose = new Flower("Rose", 10.0);
        Flower tulip = new Flower("Tulip", 8.0);
        Flower lily = new Flower("Lily", 12.0);

        Bouquet bouquetWithMultipleFlowers = new Bouquet(List.of(rose, tulip, lily));

        // Act
        List<Flower> flowers = bouquetWithMultipleFlowers.getFlowers();

        // Assert
        assertEquals(Arrays.asList(rose, tulip, lily), flowers);
    }

    @Test
    void compareBouquets() {
        // Arrange
        Flower rose = new Flower("Rose", 10.0);
        Flower tulip = new Flower("Tulip", 8.0);
        Flower lily = new Flower("Lily", 12.0);

        Bouquet bouquet1 = new Bouquet(List.of(rose, tulip));
        Bouquet bouquet2 = new Bouquet(List.of(lily));

        // Act
        // Nothing to act here since we don't change or require anything extra

        // Assert
        assertNotEquals(bouquet1,bouquet2);
    }
}
