package vn.tungnt.interview.service;

import vn.tungnt.interview.domain.entity.DriverEntity;
import vn.tungnt.interview.domain.entity.VehicleEntity;
import vn.tungnt.interview.service.dto.PaymentDTO;

public interface PaymentService {

    PaymentDTO checkout(final DriverEntity owner, final DriverEntity customer, final VehicleEntity transferredVehicle);
}
