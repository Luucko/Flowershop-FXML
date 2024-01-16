package data.implementation;

import data.repository.BouquetsRepository;
import data.util.MySqlConnection;
import domain.Bouquet;
import domain.Flower;
import util.exceptions.FlowershopException;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BouquetRepositoryImpl implements BouquetsRepository {
    private static final Logger LOGGER = Logger.getLogger(BouquetRepositoryImpl.class.getName());
    private static final String SQL_GET_ALL_FLOWERS = "SELECT * FROM flowers";

    public Bouquet generateBouquet(int size) {
        List<Flower> flowers = getRandomFlowers(size);
        return new Bouquet(flowers);
    }

    private List<Flower> getRandomFlowers(int size) {
        List<Flower> allFlowers = getAllFlowers();
        List<Flower> randomFlowers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int index = random.nextInt(allFlowers.size());
            Flower randomFlower = allFlowers.get(index);
            randomFlowers.add(randomFlower);
        }
        return randomFlowers;
    }

    private List<Flower> getAllFlowers() {
        List<Flower> flowers = new ArrayList<>();

        try (Connection connection = MySqlConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_FLOWERS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");

                Flower flower = new Flower(name, price);
                flowers.add(flower);
            }

        } catch (SQLException e) {
            LOGGER.severe("Error retrieving flowers from the database: " + e.getMessage());
            throw new FlowershopException("Error retrieving flowers from the database: " + e.getMessage());
        }

        return flowers;
    }


    public Bouquet placeOrder(Bouquet bouquet, String customerName) {
        File customerDir = new File("orders/" + customerName);
        if (!customerDir.exists()) {
            customerDir.mkdirs();
        }

        File orderFile = new File(customerDir, "order.txt");

        try (PrintStream ps = new PrintStream(orderFile)) {
            ps.println("Order details:");
            for (Flower flower : bouquet.getFlowers()) {
                ps.println("- " + flower.getName() + " - $" + flower.getPrice());
            }
            ps.println("Total Price: $" + bouquet.calculateTotalPrice());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Unable to write order to file", e);
            throw new FlowershopException("Unable to write order to file");
        }

        return bouquet;
    }

    public List<Bouquet> getOrders(String customerName) {
        // order als lijst uit files halen
        // directory heeft de naam van de klant als directorynaam
        // inhoud bevat bestelde bouquets in object (niet human readable)
        return null;
    }
}
