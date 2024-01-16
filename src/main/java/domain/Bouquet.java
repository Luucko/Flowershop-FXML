package domain;

import java.util.List;

public class Bouquet {
    private final List<Flower> flowers;

    public Bouquet(List<Flower> flowers) {
        this.flowers = flowers;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Flower flower : flowers) {
            totalPrice += flower.getPrice();
        }
        return totalPrice;
    }
}
