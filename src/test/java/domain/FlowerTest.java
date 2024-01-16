package domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlowerTest {

    @Test
    void flowerGetters() {
        // Arrange
        String expectedName = "Rose";
        double expectedPrice = 5.99;

        // Act
        Flower rose = new Flower(expectedName, expectedPrice);

        // Assert
        assertEquals(expectedName, rose.getName());
        assertEquals(expectedPrice, rose.getPrice());
    }
}
