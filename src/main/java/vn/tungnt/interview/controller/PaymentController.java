package vn.tungnt.interview.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import vn.tungnt.interview.service.DriverService;
import vn.tungnt.interview.service.PaymentService;
import vn.tungnt.interview.service.dto.payment.CommitTransferringVehicleRequestDTO;
import vn.tungnt.interview.service.dto.payment.PaymentDTO;
import vn.tungnt.interview.service.dto.TransferringVehicleRequestDTO;

@RestController
@RequestMapping("api/payment")
public class PaymentController {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentController.class);

    private final DriverService driverService;

    private final PaymentService paymentService;

    public PaymentController(final DriverService driverService, final PaymentService paymentService) {
        this.driverService = driverService;
        this.paymentService = paymentService;
    }

    @PostMapping("/transferring-vehicle")
    public PaymentDTO checkoutTransferredVehicle(@RequestBody TransferringVehicleRequestDTO requestDTO) {
        LOG.info("Begin to make a transferring vehicle order");
        return this.driverService.requestToTransferVehicle(requestDTO);
    }

    @PutMapping("/transferring-vehicle")
    public PaymentDTO commitTransferredVehicle(@RequestBody CommitTransferringVehicleRequestDTO request) {
        LOG.info("Begin to payment a transferring vehicle order");
        return paymentService.payTransferringBill(request);
    }
}
