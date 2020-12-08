package vn.tungnt.interview.service;

import vn.tungnt.interview.service.dto.payment.CommitTransferringVehicleRequestDTO;
import vn.tungnt.interview.service.dto.payment.PaymentDTO;

public interface PaymentService {

    PaymentDTO payTransferringBill(final CommitTransferringVehicleRequestDTO request);

}
