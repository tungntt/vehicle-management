package vn.tungnt.interview.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import vn.tungnt.interview.service.DriverService;
import vn.tungnt.interview.service.dto.driver.DriverDTO;
import vn.tungnt.interview.service.dto.driver.UpdatedDriverRequestDTO;

@RestController
@RequestMapping("api/driver")
public class DriverController {

    private static final Logger LOG = LoggerFactory.getLogger(DriverController.class);

    private final DriverService driverService;

    public DriverController(final DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public DriverDTO createDriver(@RequestBody DriverDTO dto) {
        LOG.info("Begin to create a driver");
        return this.driverService.add(dto);
    }

    @PutMapping
    public void updateDriver(@RequestBody UpdatedDriverRequestDTO request) {
        LOG.info("Begin to update driver information");
        this.driverService.edit(request);
    }

    @DeleteMapping("/{driverId}")
    public DriverDTO deleteDriver(@PathVariable("driverId") final long driverId) {
        LOG.info("Begin to remove a driver");
        return this.driverService.remove(driverId);
    }
}
