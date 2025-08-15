package carsales.models;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private int id;
    private String carName;
    private String vehicleCategory;
    private double price;
    private int mileage;
    private int year;
    private List<String> features;

    public Car(int id, String carName, String vehicleCategory, double price, int mileage, int year) {
        this.id = id;
        this.carName = carName;
        this.vehicleCategory = vehicleCategory;
        this.price = price;
        this.mileage = mileage;
        this.year = year;
        features = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(String vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }
}
