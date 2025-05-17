package com.example.cars.exception;

public class CarNotRentedException extends RuntimeException {
    public CarNotRentedException(String message) {
        super(message);
    }
}