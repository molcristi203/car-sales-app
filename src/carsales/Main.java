package carsales;

import carsales.controllers.CarSalesController;
import carsales.models.Shop;
import carsales.views.CarSalesView;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        CarSalesView carSalesView = new CarSalesView();
        Shop shop = new Shop();
        CarSalesController carSalesController = new CarSalesController(databaseConnection, carSalesView, shop);
    }
}
