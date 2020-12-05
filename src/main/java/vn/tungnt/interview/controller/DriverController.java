package vn.tungnt.interview.controller;

import org.springframework.web.bind.annotation.*;
import vn.tungnt.interview.service.DriverService;
import vn.tungnt.interview.service.dto.DriverDTO;
import vn.tungnt.interview.service.dto.PaymentDTO;
import vn.tungnt.interview.service.dto.TransferringVehicleRequestDTO;

@RestController
@RequestMapping("api/driver")
public class DriverController {

    private final DriverService driverService;

    public DriverController(final DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/create")
    public DriverDTO createDriver(@RequestBody DriverDTO dto) {
        return this.driverService.add(dto);
    }

    @PutMapping("/transferring-vehicle")
    public PaymentDTO checkoutTransferredVehicle(@RequestBody TransferringVehicleRequestDTO requestDTO) {
        return this.driverService.requestToTransferVehicle(requestDTO);
    }
}
