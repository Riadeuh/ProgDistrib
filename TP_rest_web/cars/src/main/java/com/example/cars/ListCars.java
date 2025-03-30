package com.example.cars;
import java.util.ArrayList;
import java.util.List;

public class ListCars {
    private List<Car> cars = new ArrayList<>();

    public ListCars() {
        cars.add(new Car("11AA22", "Ferrari", 100,false));
        cars.add(new Car("22BB33", "BMW", 80, false));
        cars.add(new Car("33CC44", "Audi", 90, false));
    }

    public List<Car> getAllcars(){
        return cars;
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public Car getCarByPlate(String plateNumber) {
        return cars.stream()
                .filter(car -> car.getPlateNumber().equalsIgnoreCase(plateNumber))
                .findFirst()
                .orElse(null);
    }

    public boolean rentCar(String plateNumber) {
        Car car = getCarByPlate(plateNumber);
        if (car != null && !car.getIsRented()) {
            car.setIsrented(true);
            return true;
        }
        return false;
    }

    public boolean returnCar(String plateNumber) {
        Car car = getCarByPlate(plateNumber);
        if (car != null && car.getIsRented()) {
            car.setIsrented(false);
            return true;
        }
        return false;
    }
}
