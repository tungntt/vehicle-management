package vn.tungnt.interview.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.tungnt.interview.service.DriverService;
import vn.tungnt.interview.service.dto.DriverDTO;

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
}
