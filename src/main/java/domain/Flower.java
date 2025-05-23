package domain;

import java.io.Serializable;

public class Flower implements Serializable {
    private final int id = -1;

    /*
    Initiate ID here but SQL handles auto_incr (if not => -1)
    Since ID is not handled here, the auto_increment id can be reset to 0 using these SQL statements:
    DROP TABLE flowers;

    CREATE TABLE `flowers` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `name` VARCHAR(50) NOT NULL,
        `price` DOUBLE NOT NULL,
        PRIMARY KEY (`id`)
    ) ENGINE=INNODB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

    INSERT INTO `flowers` VALUES (1,'Rose',5.99),(2,'Tulip',3.99),(3,'Daisy',2.49),(4,'Lily',4.25),(5,'Sunflower',6.75),(6,'Orchid',8.99),(7,'Carnation',3.49),(8,'Hydrangea',7.25),(9,'Peony',6.49),(10,'Daffodil',2.99),(11,'Marigold',3.25),(12,'Gerbera',4.99),(13,'Hibiscus',5.49),(14,'Zinnia',3.75),(15,'Poppy',4.29),(16,'Crocus',2.75),(17,'Chrysanthemum',5.99),(18,'Anemone',3.99),(19,'Begonia',4.49),(20,'Freesia',6.25),(21,'Snapdragon',5.75),(22,'Gladiolus',6.99),(23,'Aster',4.79),(24,'Cosmos',3.89),(25,'Foxglove',5.15),(26,'Hollyhock',4.65),(27,'Iris',6.49),(28,'Lavender',3.99),(29,'Narcissus',4.85),(30,'Dahlia',3.25);
    */

    private final String name;
    private final double price;

    public Flower(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
