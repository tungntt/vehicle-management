package vn.tungnt.interview.service;

import vn.tungnt.interview.domain.entity.DriverEntity;
import vn.tungnt.interview.service.dto.driver.DriverDTO;
import vn.tungnt.interview.service.dto.payment.PaymentDTO;
import vn.tungnt.interview.service.dto.TransferringVehicleRequestDTO;

public interface DriverService extends BaseService<DriverEntity, DriverDTO> {

    DriverDTO removeVehicle(final long vehicleId);

    PaymentDTO requestToTransferVehicle(final TransferringVehicleRequestDTO requestDTO);
}
