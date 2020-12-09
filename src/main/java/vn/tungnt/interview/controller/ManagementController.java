package vn.tungnt.interview.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.tungnt.interview.security.AuthoritiesConstants;
import vn.tungnt.interview.service.DriverService;
import vn.tungnt.interview.service.PaymentManagementService;
import vn.tungnt.interview.service.VehicleService;
import vn.tungnt.interview.service.dto.driver.DriverDTO;
import vn.tungnt.interview.service.dto.payment.PaymentDTO;
import vn.tungnt.interview.service.dto.vehicle.VehicleDTO;

import java.util.List;

@RestController
@RequestMapping("api/management")
public class ManagementController {

    private static final Logger LOG = LoggerFactory.getLogger(ManagementController.class);

    private final DriverService driverService;

    private final VehicleService vehicleService;

    private final PaymentManagementService paymentService;

    public ManagementController(final DriverService driverService,
                                final VehicleService vehicleService,
                                final PaymentManagementService paymentService) {
        this.driverService = driverService;
        this.vehicleService = vehicleService;
        this.paymentService = paymentService;
    }

    @GetMapping("/drivers")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    private List<DriverDTO> getAllDrivers() {
        LOG.info("Admin: Get All Drivers");
        return this.driverService.readAll();
    }

    @GetMapping("/vehicles")
    private List<VehicleDTO> getAllVehicles() {
        LOG.info("Admin: Get All Drivers");
        return this.vehicleService.readAll();
    }

    @GetMapping("/payments")
    private List<PaymentDTO> getAllPayments() {
        LOG.info("Admin: Get All Drivers");
        return this.paymentService.readAll();
    }
}
