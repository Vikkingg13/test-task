package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final ExternalApproveService approveService;
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(ExternalApproveService approveService, CarRepository carRepository) {
        this.approveService = approveService;
        this.carRepository = carRepository;
    }

    @Override
    public boolean approve(CarDto carDto) {
        return approveService.approve(new CarAdapter(carDto)) == 0;
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Optional<Car> load(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car fromDto(CarDto carDto) {
        return new Car(
                carDto.getId(),
                carDto.getBrand(),
                carDto.getModel(),
                carDto.getPower(),
                carDto.getYear(),
                carDto.getEvaluations()
        );
    }

    @Override
    public CarDto toDTO(Car car) {
        return new CarDto(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getPower(),
                car.getYear(),
                car.getEvaluations()
        );
    }

    @Override
    public Long getId(Car car) {
        return car.getId();
    }}
