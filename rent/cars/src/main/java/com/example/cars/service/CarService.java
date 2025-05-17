package com.example.cars.service;

import com.example.cars.Car;
import com.example.cars.exception.CarAlreadyRentedException;
import com.example.cars.exception.CarNotFoundException;
import com.example.cars.exception.CarNotRentedException;
import com.example.cars.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarByPlate(String plateNumber) {
        return carRepository.findById(plateNumber)
                .orElseThrow(() -> new CarNotFoundException("La voiture avec le numéro " + plateNumber + " n'existe pas"));
    }

    public List<Car> getCarsByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    public List<Car> getAvailableCars() {
        return carRepository.findByIsrented(false);
    }

    public List<Car> getRentedCars() {
        return carRepository.findByIsrented(true);
    }

    public List<Car> getCarsByMaxPrice(double maxPrice) {
        return carRepository.findCarsWithPriceLessThanOrEqual(maxPrice);
    }

    public Car addCar(Car car) {
        if (carRepository.existsById(car.getPlateNumber())) {
            throw new IllegalArgumentException("Une voiture avec ce numéro d'immatriculation existe déjà");
        }
        return carRepository.save(car);
    }

    public void deleteCar(String plateNumber) {
        if (!carRepository.existsById(plateNumber)) {
            throw new CarNotFoundException("La voiture avec le numéro " + plateNumber + " n'existe pas");
        }
        carRepository.deleteById(plateNumber);
    }

    public Car rentCar(String plateNumber) {
        Car car = getCarByPlate(plateNumber);

        if (car.getIsRented()) {
            throw new CarAlreadyRentedException("La voiture est déjà louée !");
        }

        car.setIsrented(true);
        return carRepository.save(car);
    }

    public Car returnCar(String plateNumber) {
        Car car = getCarByPlate(plateNumber);

        if (!car.getIsRented()) {
            throw new CarNotRentedException("La voiture n'est pas actuellement louée !");
        }

        car.setIsrented(false);
        return carRepository.save(car);
    }
}