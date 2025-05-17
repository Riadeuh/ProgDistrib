package com.example.cars;

public class Car {
    private String plateNumber;
    private String brand;
    private double price;
    private boolean isrented;

    public Car(){
        this.plateNumber = "";
        this.brand = "";
        this.price = 0.0;
        this.isrented = false;
    }

    public Car(String plateNumber, String brand, double price, boolean isrented){
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.price = price;
        this.isrented = isrented;
    }

    public double getPrice() {
        return this.price;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getPlateNumber() {
        return this.plateNumber;
    }

    public boolean getIsRented(){
        return this.isrented;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setIsrented(boolean isrented) {
        this.isrented = isrented;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "plateNumber='" + plateNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", isrented=" + isrented +
                '}';
    }
}
