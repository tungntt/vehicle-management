package vn.tungnt.interview.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.tungnt.interview.service.VehicleService;
import vn.tungnt.interview.service.dto.vehicle.VehicleDTO;

@RestController
@RequestMapping("api/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(final VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleDTO createVehicle(@RequestBody VehicleDTO dto) {
        return this.vehicleService.add(dto);
    }
}
