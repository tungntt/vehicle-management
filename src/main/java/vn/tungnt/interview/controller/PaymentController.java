package vn.tungnt.interview.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.tungnt.interview.service.DriverService;
import vn.tungnt.interview.service.dto.payment.PaymentDTO;
import vn.tungnt.interview.service.dto.TransferringVehicleRequestDTO;

@RestController
@RequestMapping("api/payment")
public class PaymentController {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentController.class);

    private final DriverService driverService;

    public PaymentController(final DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/transferring-vehicle")
    public PaymentDTO checkoutTransferredVehicle(@RequestBody TransferringVehicleRequestDTO requestDTO) {
        LOG.info("Begin to make a transferring vehicle order");
        return this.driverService.requestToTransferVehicle(requestDTO);
    }
}
