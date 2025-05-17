package com.example.cars.controller;

import com.example.cars.Car;
import com.example.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/{plateNumber}")
    public ResponseEntity<Car> getCarByPlate(@PathVariable String plateNumber) {
        return ResponseEntity.ok(carService.getCarByPlate(plateNumber));
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Car>> getCarsByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(carService.getCarsByBrand(brand));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Car>> getAvailableCars() {
        return ResponseEntity.ok(carService.getAvailableCars());
    }

    @GetMapping("/rented")
    public ResponseEntity<List<Car>> getRentedCars() {
        return ResponseEntity.ok(carService.getRentedCars());
    }

    @GetMapping("/price")
    public ResponseEntity<List<Car>> getCarsByMaxPrice(@RequestParam double maxPrice) {
        return ResponseEntity.ok(carService.getCarsByMaxPrice(maxPrice));
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.addCar(car));
    }

    @DeleteMapping("/{plateNumber}")
    public ResponseEntity<Void> deleteCar(@PathVariable String plateNumber) {
        carService.deleteCar(plateNumber);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{plateNumber}/rent")
    public ResponseEntity<Car> rentCar(@PathVariable String plateNumber) {
        return ResponseEntity.ok(carService.rentCar(plateNumber));
    }

    @PutMapping("/{plateNumber}/return")
    public ResponseEntity<Car> returnCar(@PathVariable String plateNumber) {
        return ResponseEntity.ok(carService.returnCar(plateNumber));
    }
}