package vn.tungnt.interview.service;

import vn.tungnt.interview.domain.entity.DriverEntity;
import vn.tungnt.interview.domain.entity.VehicleEntity;
import vn.tungnt.interview.service.dto.payment.PaymentDTO;

public interface CheckoutService {

    PaymentDTO checkout(final DriverEntity owner, final DriverEntity customer, final VehicleEntity transferredVehicle);
}
