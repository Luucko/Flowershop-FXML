package domain;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Bouquet {
    private final List<Flower> flowers;
    private final String bouquetId;

    public Bouquet(List<Flower> flowers) {
        this.flowers = flowers;
        this.bouquetId = generateBouquetId();
    }

    private String generateBouquetId() {
        return UUID.randomUUID().toString();
    }

    public List<Flower> getFlowers() {
        return this.flowers;
    }

    public String getBouquetId(){
        return this.bouquetId;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Flower flower : flowers) {
            totalPrice += flower.getPrice();
        }
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bouquet bouquet = (Bouquet) o;
        return Objects.equals(bouquetId, bouquet.bouquetId);
    }

    @Override
    public String toString() {
        return "Bouquet{" +
                "flowers=" + flowers +
                ", bouquetId='" + bouquetId + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(bouquetId);
    }
}
