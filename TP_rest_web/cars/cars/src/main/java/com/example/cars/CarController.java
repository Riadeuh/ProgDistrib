package com.example.cars;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final ListCars listCars;

    public CarController(){
        this.listCars = new ListCars();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Car> listOfCars(){
        return this.listCars.getAllcars();
    }

    @GetMapping("{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Car aCar(@PathVariable("plateNumber") String plateNumber) throws Exception{
        Car car = this.listCars.getCarByPlate(plateNumber);
        if(car == null) {
            throw new Exception("La voiture n'éxiste pas");
        }
        return car;
    }

    @PutMapping("/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void rentOrGetBack(@PathVariable("plateNumber") String plateNumber,
                              @RequestParam(value = "rent", required = true) boolean rent) throws Exception {
        Car car = this.listCars.getCarByPlate(plateNumber);
        if (car == null) {
            throw new Exception("Voiture non trouvée !");
        }

        if (rent) {
            if (this.listCars.rentCar(plateNumber)) {
                throw new Exception("La voiture est déjà louée !");
            }
        } else {
            if (this.listCars.returnCar(plateNumber)) {
                throw new Exception("La voiture n'est pas actuellement louée !");
            }
        }
    }


}
