package vn.tungnt.interview.service;

import vn.tungnt.interview.domain.entity.DriverEntity;
import vn.tungnt.interview.service.dto.DriverDTO;
import vn.tungnt.interview.service.dto.VehicleDTO;

public interface DriverService extends BaseService<DriverEntity, DriverDTO> {

    DriverDTO removeVehicle(final long driverId, final long vehicleId);

    VehicleDTO transferVehicle(final long ownerId, final long customerId, final long vehicleId);
}
