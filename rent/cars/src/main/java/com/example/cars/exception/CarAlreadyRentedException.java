package com.example.cars.exception;

public class CarAlreadyRentedException extends RuntimeException {
    public CarAlreadyRentedException(String message) {
        super(message);
    }
}
