package vn.tungnt.interview.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.tungnt.interview.service.VehicleService;
import vn.tungnt.interview.service.dto.VehicleDTO;

@RestController
@RequestMapping("api/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(final VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/create")
    public VehicleDTO createVehicle(@RequestBody VehicleDTO dto) {
        return this.vehicleService.add(dto);
    }
}
