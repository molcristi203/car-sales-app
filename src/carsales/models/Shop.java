package carsales.models;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<Car> cars;

    public Shop() {
        this.cars = new ArrayList<>();
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
