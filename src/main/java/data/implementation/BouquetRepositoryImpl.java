package data.implementation;

import data.repository.BouquetsRepository;
import data.util.MySqlConnection;
import domain.Bouquet;
import domain.Flower;
import util.exceptions.FlowershopException;

import java.io.*;
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
        File bouquetsDir = new File("bouquets");
        if (!bouquetsDir.exists()) {
            bouquetsDir.mkdir();
        }

        File orderFile = new File(bouquetsDir, customerName + ".dat");

        List<Bouquet> existingOrders = getOrders(customerName);
        existingOrders.add(bouquet);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(orderFile))) {
            oos.writeObject(existingOrders);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Unable to write order to file", e);
            throw new FlowershopException("Unable to write order to file");
        }

        return bouquet;
    }



    public List<Bouquet> getOrders(String customerName) {
        List<Bouquet> orders = new ArrayList<>();
        File bouquetsDir = new File("bouquets");

        if (!bouquetsDir.exists() || !bouquetsDir.isDirectory()) {
            return orders; // "bouquets" directory doesn't exist or is not a directory
        }

        File[] customerFiles = bouquetsDir.listFiles((dir, name) -> name.startsWith(customerName) && name.endsWith(".dat"));

        if (customerFiles != null) {
            for (File file : customerFiles) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    List<Bouquet> orderList = (List<Bouquet>) ois.readObject(); // <-- Corrected line
                    orders.addAll(orderList);
                } catch (IOException | ClassNotFoundException e) {
                    LOGGER.log(Level.SEVERE, "Error reading bouquet order from file: " + file.getName(), e);
                    throw new FlowershopException("Error reading bouquet order from file: " + file.getName());
                }
            }
        }

        return orders;
    }
}
