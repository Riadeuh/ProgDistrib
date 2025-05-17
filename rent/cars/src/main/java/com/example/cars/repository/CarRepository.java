package com.example.cars.repository;

import com.example.cars.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CarRepository {
    private final List<Car> cars = new ArrayList<>();

    public CarRepository() {
        // Initialisation avec quelques voitures
        cars.add(new Car("11AA22", "Ferrari", 100.0, false));
        cars.add(new Car("22BB33", "BMW", 80.0, false));
        cars.add(new Car("33CC44", "Audi", 90.0, false));
        cars.add(new Car("44DD55", "Mercedes", 110.0, false));
        cars.add(new Car("55EE66", "Porsche", 150.0, false));
    }

    public List<Car> findAll() {
        return new ArrayList<>(cars);
    }

    public Optional<Car> findById(String plateNumber) {
        return cars.stream()
                .filter(car -> car.getPlateNumber().equalsIgnoreCase(plateNumber))
                .findFirst();
    }

    public List<Car> findByBrand(String brand) {
        return cars.stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    public List<Car> findByIsrented(boolean isRented) {
        return cars.stream()
                .filter(car -> car.getIsRented() == isRented)
                .collect(Collectors.toList());
    }

    public List<Car> findCarsWithPriceLessThanOrEqual(double maxPrice) {
        return cars.stream()
                .filter(car -> car.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public Car save(Car car) {
        Optional<Car> existingCar = findById(car.getPlateNumber());
        if (existingCar.isPresent()) {
            cars.remove(existingCar.get());
        }
        cars.add(car);
        return car;
    }

    public boolean existsById(String plateNumber) {
        return findById(plateNumber).isPresent();
    }

    public void deleteById(String plateNumber) {
        findById(plateNumber).ifPresent(cars::remove);
    }

    public long count() {
        return cars.size();
    }
}