package vn.tungnt.interview.service;

import vn.tungnt.interview.domain.entity.DriverEntity;
import vn.tungnt.interview.service.dto.DriverDTO;
import vn.tungnt.interview.service.dto.PaymentDTO;
import vn.tungnt.interview.service.dto.TransferringVehicleRequestDTO;

public interface DriverService extends BaseService<DriverEntity, DriverDTO> {

    DriverDTO removeVehicle(final long driverId, final long vehicleId);

    PaymentDTO requestToTransferVehicle(final TransferringVehicleRequestDTO requestDTO);
}
